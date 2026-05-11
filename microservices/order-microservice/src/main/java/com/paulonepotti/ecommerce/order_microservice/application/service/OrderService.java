package com.paulonepotti.ecommerce.order_microservice.application.service;

import com.paulonepotti.ecommerce.order_microservice.domain.model.Order;
import com.paulonepotti.ecommerce.order_microservice.domain.exception.DomainValidationException;
import com.paulonepotti.ecommerce.order_microservice.domain.exception.OrderNotFoundException;
import com.paulonepotti.ecommerce.order_microservice.application.port.in.CancelOrderUseCase;
import com.paulonepotti.ecommerce.order_microservice.application.port.in.CreateOrderUseCase;
import com.paulonepotti.ecommerce.order_microservice.application.port.in.GetOrderUseCase;
import com.paulonepotti.ecommerce.order_microservice.application.port.in.UpdateOrderStatusUseCase;
import com.paulonepotti.ecommerce.order_microservice.application.port.out.OrderRepositoryPort;
import com.paulonepotti.ecommerce.order_microservice.application.port.out.ProductPort;

public class OrderService implements
        CreateOrderUseCase,
        GetOrderUseCase,
        CancelOrderUseCase,
        UpdateOrderStatusUseCase {

    private final OrderRepositoryPort orderRepositoryPort;
    private final ProductPort productPort; // ← puerto, no Feign directo

    public OrderService(OrderRepositoryPort orderRepositoryPort, ProductPort productPort) {
        this.orderRepositoryPort = orderRepositoryPort;
        this.productPort         = productPort;
    }

    @Override
    public Order createOrder(Order orderRequest) {
        // 1. Enriquecer cada ítem con el snapshot del producto actual
        orderRequest.getItems().forEach(item -> {
            // Llamada al puerto — no sabe si es Feign, REST o un mock
            var snapshot = productPort.getProductById(
                item.getProduct().getProductId()
            );

            // Congela nombre y precio al momento de la compra
            item.setProductSnapshot(snapshot);

            // Validar stock — descomentiá cuando tengas InventoryPort
            /*
            if (snapshot.getStock() < item.getQuantity()) {
                throw new DomainValidationException(
                    "Stock insuficiente para: " + snapshot.getName()
                );
            }
            */
        });

        // 2. Recalcula el total con los precios congelados
        orderRequest.recalculateTotal();

        // 3. Persistir
        return orderRepositoryPort.save(orderRequest);
    }

    @Override
    public Order getOrderById(Long orderId) {
        return orderRepositoryPort.findById(orderId)
            .orElseThrow(() -> new OrderNotFoundException(orderId));
    }

    @Override
    public void cancelOrder(Long orderId) {
        Order order = orderRepositoryPort.findById(orderId)
            .orElseThrow(() -> new OrderNotFoundException(orderId));

        order.cancel("Cancelado por el cliente"); // ← método de dominio con validación de estado
        orderRepositoryPort.save(order);
    }

    @Override
    public void updateOrderStatus(Long orderId, String newStatus) {
        Order order = orderRepositoryPort.findById(orderId)
            .orElseThrow(() -> new OrderNotFoundException(orderId));

        // El dominio valida que la transición sea legal — si no, lanza InvalidOrderStateException
        switch (newStatus.toUpperCase()) {
            case "CONFIRMED"  -> order.confirm();
            case "PAID"       -> order.markAsPaid();
            case "PREPARING"  -> order.startPreparing();
            case "SHIPPED"    -> order.ship();
            case "DELIVERED"  -> order.deliver();
            case "CANCELLED"  -> order.cancel("Actualización manual de estado");
            case "REFUNDED"   -> order.refund();
            default -> throw new DomainValidationException("Estado inválido: " + newStatus);
        }

        orderRepositoryPort.save(order);
    }
}