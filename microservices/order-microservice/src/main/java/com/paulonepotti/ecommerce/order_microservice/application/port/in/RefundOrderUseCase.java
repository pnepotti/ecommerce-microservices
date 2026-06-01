package com.paulonepotti.ecommerce.order_microservice.application.port.in;

public interface RefundOrderUseCase {
    void refundOrder(Long orderId);
}
