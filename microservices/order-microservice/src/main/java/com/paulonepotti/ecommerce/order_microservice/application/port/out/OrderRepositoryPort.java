package com.paulonepotti.ecommerce.order_microservice.application.port.out;

import com.paulonepotti.ecommerce.order_microservice.domain.model.Order;

import java.util.List;
import java.util.Optional;

public interface OrderRepositoryPort {
    Order save(Order order);
    Optional<Order> findById(Long id);
    List<Order> findAll();
    void deleteById(Long id);
}
