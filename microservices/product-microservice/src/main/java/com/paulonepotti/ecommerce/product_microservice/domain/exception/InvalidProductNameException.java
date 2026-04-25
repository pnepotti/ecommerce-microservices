package com.paulonepotti.ecommerce.product_microservice.domain.exception;

public class InvalidProductNameException extends RuntimeException {
    public InvalidProductNameException(String message) {
        super(message);
    }
}
