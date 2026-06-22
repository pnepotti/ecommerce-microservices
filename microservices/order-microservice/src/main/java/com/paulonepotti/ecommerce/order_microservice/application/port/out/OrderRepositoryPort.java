package com.paulonepotti.ecommerce.order_microservice.application.port.out;

import com.paulonepotti.ecommerce.order_microservice.domain.model.Order;
import com.paulonepotti.ecommerce.order_microservice.domain.model.PageResponse;

import java.util.Optional;

public interface OrderRepositoryPort {
    Order save(Order order);
    Optional<Order> findById(Long id);
    PageResponse<Order> findAll(int page, int size);
    void deleteById(Long id);
    PageResponse<Order> findByCustomerId(Long customerId, int page, int size);
}
