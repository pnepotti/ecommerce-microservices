package com.paulonepotti.ecommerce.order_microservice.application.port.out;

public interface CustomerPort {
    boolean existsById(Long customerId);
}
