package com.paulonepotti.ecommerce.order_microservice.application.port.in;

public interface DeliverOrderUseCase {
    void deliverOrder(Long orderId);
}
