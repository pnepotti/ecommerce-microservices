package com.paulonepotti.ecommerce.order_microservice.application.port.in;

import java.util.List;

import com.paulonepotti.ecommerce.order_microservice.domain.model.Order;

public interface GetAllOrdersUseCase {
    List<Order> getAllOrders();
}
