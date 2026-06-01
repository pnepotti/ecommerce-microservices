package com.paulonepotti.ecommerce.order_microservice.application.port.in;

public interface ShipOrderUseCase {
    void shipOrder(Long orderId);
}
