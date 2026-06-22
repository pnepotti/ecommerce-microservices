package com.paulonepotti.ecommerce.order_microservice.domain.exception;

public class CustomerOrderNotFoundException extends RuntimeException {

    public CustomerOrderNotFoundException(Long customerId) {
        super("No se encontraron órdenes para el cliente con ID: " + customerId);
    }
}
