package com.paulonepotti.microservices.customer_microservice.exception;

public class CustomerNotFoundException extends RuntimeException {

    public CustomerNotFoundException(Long customerId) {
        super("Cliente no encontrado con id: " + customerId);
    }
    
}
