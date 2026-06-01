package com.paulonepotti.ecommerce.order_microservice.application.port.in;

public interface ConfirmOrderUseCase {
    void confirmOrder(Long orderId);
}
