package com.paulonepotti.ecommerce.order_microservice.domain.exception;

public class DomainValidationException extends RuntimeException {
    
    public DomainValidationException(String message) {
        super(message);
    }

}
