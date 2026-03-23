package com.paulonepotti.ecommerce.order_microservice.application.port.out;

import com.paulonepotti.ecommerce.order_microservice.domain.model.Order;

public interface OrderRepositoryPort {
    Order save(Order order);
}
