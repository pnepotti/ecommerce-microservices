package com.paulonepotti.ecommerce.order_microservice.application.service;

import com.paulonepotti.ecommerce.order_microservice.domain.model.Order;
import com.paulonepotti.ecommerce.order_microservice.domain.model.OrderItem;
import com.paulonepotti.ecommerce.order_microservice.domain.model.OrderStatus;
import com.paulonepotti.ecommerce.order_microservice.domain.model.PageResponse;
import com.paulonepotti.ecommerce.order_microservice.domain.exception.DomainValidationException;
import com.paulonepotti.ecommerce.order_microservice.domain.exception.OrderNotFoundException;

import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.List;

import com.paulonepotti.ecommerce.order_microservice.application.port.in.CancelOrderUseCase;
import com.paulonepotti.ecommerce.order_microservice.application.port.in.ConfirmOrderUseCase;
import com.paulonepotti.ecommerce.order_microservice.application.port.in.CreateOrderUseCase;
import com.paulonepotti.ecommerce.order_microservice.application.port.in.DeliverOrderUseCase;
import com.paulonepotti.ecommerce.order_microservice.application.port.in.GetAllOrdersUseCase;
import com.paulonepotti.ecommerce.order_microservice.application.port.in.GetCustomerOrdersUseCase;
import com.paulonepotti.ecommerce.order_microservice.application.port.in.GetOrderUseCase;
import com.paulonepotti.ecommerce.order_microservice.application.port.in.ShipOrderUseCase;
import com.paulonepotti.ecommerce.order_microservice.application.port.out.CustomerPort;
import com.paulonepotti.ecommerce.order_microservice.application.port.out.OrderRepositoryPort;
import com.paulonepotti.ecommerce.order_microservice.application.port.out.ProductPort;
import com.paulonepotti.ecommerce.order_microservice.application.port.in.RefundOrderUseCase;

import java.util.logging.Logger;
import java.util.logging.Level;

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
    private final CustomerPort customerPort; // ← puerto, no Feign directo


    public OrderService(OrderRepositoryPort orderRepositoryPort, ProductPort productPort, CustomerPort customerPort) {
        this.orderRepositoryPort = orderRepositoryPort;
        this.productPort = productPort;
        this.customerPort = customerPort;
    }
    private static final Logger logger = Logger.getLogger(OrderService.class.getName());

    @Override
    public Order createOrder(Order orderRequest) {
        if (!customerPort.existsById(orderRequest.getCustomerId())) {
            throw new DomainValidationException("El cliente no existe o no está activo");
        }
        orderRequest.getItems().forEach(item -> {
            var snapshot = productPort.getProductById(item.getProduct().getProductId());        
            item.updateProductSnapshot(snapshot); 
        });
        orderRequest.recalculateTotal();
        Order savedOrder = orderRepositoryPort.save(orderRequest);
        List<OrderItem> itemsProcessed = new ArrayList<>();
           try {
            for (OrderItem item : savedOrder.getItems()) {
                productPort.decreaseStock(item.getProduct().getProductId(), item.getQuantity());
                itemsProcessed.add(item); // Registro para posible compensación
            }
            savedOrder.confirm();
            return orderRepositoryPort.save(savedOrder);
           } catch (Exception e) {

            logger.log(Level.SEVERE, "ERROR CRÍTICO DE CONSISTENCIA en pedido " + savedOrder.getId(), e);
            itemsProcessed.forEach(item -> {
                try {
                    productPort.increaseStock(item.getProduct().getProductId(), item.getQuantity());

                } catch (Exception ex) {
                    logger.log(Level.SEVERE, "¡ALERTA! No se pudo compensar producto " + item.getProduct().getProductId());

                }
           });
            savedOrder.cancel("Fallo en reserva de stock: " + e.getMessage());
            orderRepositoryPort.save(savedOrder);
            throw e; 
        }
    }

    @Override
    public Order getOrderById(Long orderId) {
        return orderRepositoryPort.findById(orderId)
            .orElseThrow(() -> new OrderNotFoundException(orderId));
    }

    // --- Métodos específicos de transición   
    
    @Override
    public Order confirmOrder(Long orderId) {
        return updateStatus(orderId, Order::confirm);
    }

    @Override
    public Order shipOrder(Long orderId) {
        return updateStatus(orderId, Order::ship);
    }

    @Override
    public Order deliverOrder(Long orderId) {
        return updateStatus(orderId, Order::deliver);
    }

    @Override
    public Order cancelOrder(Long orderId, String reason) {
        Order order = findOrderOrThrow(orderId);
        
        // 1. Identificar si el pedido tiene stock reservado que debamos devolver
        // En tu flujo, si el pedido está en CONFIRMED, PAID o PREPARING, el stock ya se restó.
        boolean needsStockReturn = isStockAlreadyReserved(order.getStatus());
   
        // 2. Cambiar el estado en el dominio (valida si la transición es legal)
        order.cancel(reason);
    
        // 3. Si tenía stock, lo devolvemos al microservicio de productos
        if (needsStockReturn) {
            logStockReturnInitiated(order);
            order.getItems().forEach(item -> {
                try {
                    productPort.increaseStock(item.getProduct().getProductId(), item.getQuantity());
                } catch (Exception e) {
                    // ALERTA CRÍTICA: Igual que en la creación, si falla la devolución, 
                    // el stock queda inconsistente y requiere intervención manual.
                    logger.log(Level.SEVERE, "¡ALERTA DE INCONSISTENCIA! No se pudo devolver stock del producto " 
                        + item.getProduct().getProductId() + " en la cancelación del pedido " + orderId);
                }
            });
        }
    
        // 4. Persistir el cambio de estado a CANCELLED
        return orderRepositoryPort.save(order);
    }
    
    // Método auxiliar de negocio
    private boolean isStockAlreadyReserved(OrderStatus status) {
        return status == OrderStatus.CONFIRMED || 
               status == OrderStatus.PAID      || 
               status == OrderStatus.PREPARING ||
               status == OrderStatus.SHIPPED;
    }

    private void logStockReturnInitiated(Order order) {
        // Usamos {0} y {1} para pasar parámetros de forma eficiente en java.util.logging
        logger.log(Level.INFO, "Iniciando proceso de devolución de stock para el pedido ID: {0}. Estado anterior: {1}", 
                   new Object[]{order.getId(), order.getStatus()});
    }
    @Override
    public Order refundOrder(Long orderId) {
        updateStatus(orderId, Order::refund);
        // disparar una llamada al microservicio de pagos
        // paymentPort.refund(order.getPaymentId(), order.getTotalAmount());
        return getOrderById(orderId);
    }

    @Override
    public PageResponse<Order> getAllOrders(int page, int size) {  
        return orderRepositoryPort.findAll(page, size);
    }

    @Override
    public PageResponse<Order> getCustomerOrders(Long customerId, int page, int size) {
        return orderRepositoryPort.findByCustomerId(customerId, page, size);
    }

    private Order updateStatus(Long orderId, Consumer<Order> action) {
        Order order = findOrderOrThrow(orderId);
        action.accept(order); // Ejecuta la confirmación, envío, etc.
        return orderRepositoryPort.save(order);
    }

    private Order findOrderOrThrow(Long orderId) {
        return orderRepositoryPort.findById(orderId)
            .orElseThrow(() -> new OrderNotFoundException(orderId));
    }

}
