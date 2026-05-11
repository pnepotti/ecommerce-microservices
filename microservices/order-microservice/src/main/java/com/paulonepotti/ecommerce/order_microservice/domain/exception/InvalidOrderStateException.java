package com.paulonepotti.ecommerce.order_microservice.domain.exception;

public class InvalidOrderStateException extends RuntimeException {
    
    public InvalidOrderStateException(String message) {
        super(message);
    }

}
