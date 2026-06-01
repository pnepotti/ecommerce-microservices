package com.paulonepotti.ecommerce.order_microservice.application.port.in;

public interface CancelOrderUseCase {
    void cancelOrder(Long orderId, String reason);
}
