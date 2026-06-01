package com.paulonepotti.ecommerce.order_microservice.application.service;

import com.paulonepotti.ecommerce.order_microservice.domain.model.Order;

import com.paulonepotti.ecommerce.order_microservice.domain.exception.OrderNotFoundException;

import java.util.List;
import java.util.function.Consumer;

import com.paulonepotti.ecommerce.order_microservice.application.port.in.CancelOrderUseCase;
import com.paulonepotti.ecommerce.order_microservice.application.port.in.ConfirmOrderUseCase;
import com.paulonepotti.ecommerce.order_microservice.application.port.in.CreateOrderUseCase;
import com.paulonepotti.ecommerce.order_microservice.application.port.in.DeliverOrderUseCase;
import com.paulonepotti.ecommerce.order_microservice.application.port.in.GetAllOrdersUseCase;
import com.paulonepotti.ecommerce.order_microservice.application.port.in.GetCustomerOrdersUseCase;
import com.paulonepotti.ecommerce.order_microservice.application.port.in.GetOrderUseCase;
import com.paulonepotti.ecommerce.order_microservice.application.port.in.ShipOrderUseCase;
import com.paulonepotti.ecommerce.order_microservice.application.port.out.OrderRepositoryPort;
import com.paulonepotti.ecommerce.order_microservice.application.port.out.ProductPort;
import com.paulonepotti.ecommerce.order_microservice.application.port.in.RefundOrderUseCase;

public class OrderService implements
        CreateOrderUseCase,
        GetOrderUseCase,
        CancelOrderUseCase,
        GetCustomerOrdersUseCase,
        GetAllOrdersUseCase,
        ConfirmOrderUseCase,
        ShipOrderUseCase,
        DeliverOrderUseCase,
        RefundOrderUseCase {

    private final OrderRepositoryPort orderRepositoryPort;
    private final ProductPort productPort; // ← puerto, no Feign directo

    public OrderService(OrderRepositoryPort orderRepositoryPort, ProductPort productPort) {
        this.orderRepositoryPort = orderRepositoryPort;
        this.productPort = productPort;
    }

    @Override
    public Order createOrder(Order orderRequest) {
        // 1. Enriquecer items
        orderRequest.getItems().forEach(item -> {
            var snapshot = productPort.getProductById(item.getProduct().getProductId());        
                    // Usamos el nuevo método que sincroniza el precio internamente
            item.updateProductSnapshot(snapshot); 
        });

        // 2. Recalcular total (ahora los items ya tienen precio)
        orderRequest.recalculateTotal();

        // 3. Persistir
        return orderRepositoryPort.save(orderRequest);
    }

    @Override
    public Order getOrderById(Long orderId) {
        return orderRepositoryPort.findById(orderId)
            .orElseThrow(() -> new OrderNotFoundException(orderId));
    }
    // --- Métodos específicos de transición   
    @Override
    public void confirmOrder(Long orderId) {
        updateStatus(orderId, Order::confirm);
    }

    @Override
    public void shipOrder(Long orderId) {
        updateStatus(orderId, Order::ship);
    }

    @Override
    public void deliverOrder(Long orderId) {
        updateStatus(orderId, Order::deliver);
    }

    @Override
    public void cancelOrder(Long orderId, String reason) {
        Order order = findOrderOrThrow(orderId);
        order.cancel(reason);
        orderRepositoryPort.save(order);
    }

    // --- Método privado para evitar repetición de código (DRY) ---
    private void updateStatus(Long orderId, Consumer<Order> action) {
        Order order = findOrderOrThrow(orderId);
        action.accept(order); // Ejecuta la confirmación, envío, etc.
        orderRepositoryPort.save(order);
    }

    private Order findOrderOrThrow(Long orderId) {
        return orderRepositoryPort.findById(orderId)
            .orElseThrow(() -> new OrderNotFoundException(orderId));
    }

    @Override
    public void refundOrder(Long orderId) {
        updateStatus(orderId, Order::refund);
        // Paso Senior Extra: Aquí podrías disparar una llamada al microservicio de pagos
        // paymentPort.refund(order.getPaymentId(), order.getTotalAmount());

    }

    @Override
    public List<Order> getAllOrders() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllOrders'");
    }

    @Override
    public List<Order> getCustomerOrders(Long customerId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCustomerOrders'");
    }

}