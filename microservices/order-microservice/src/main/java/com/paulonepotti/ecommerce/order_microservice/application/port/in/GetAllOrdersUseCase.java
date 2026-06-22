package com.paulonepotti.ecommerce.order_microservice.application.port.in;

import com.paulonepotti.ecommerce.order_microservice.domain.model.Order;
import com.paulonepotti.ecommerce.order_microservice.domain.model.PageResponse;

public interface GetAllOrdersUseCase {
    PageResponse<Order> getAllOrders(int page, int size);
}
