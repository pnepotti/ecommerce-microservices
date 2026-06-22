package com.paulonepotti.ecommerce.order_microservice.application.port.in;

import com.paulonepotti.ecommerce.order_microservice.domain.model.Order;

public interface CancelOrderUseCase {
    Order cancelOrder(Long orderId, String reason);
}
