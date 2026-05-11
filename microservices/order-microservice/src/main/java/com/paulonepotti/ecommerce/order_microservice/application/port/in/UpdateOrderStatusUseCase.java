package com.paulonepotti.ecommerce.order_microservice.application.port.in;

public interface UpdateOrderStatusUseCase {
    void updateOrderStatus(Long orderId, String newStatus);
}
