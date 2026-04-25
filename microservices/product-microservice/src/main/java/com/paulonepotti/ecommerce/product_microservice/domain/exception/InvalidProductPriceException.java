package com.paulonepotti.ecommerce.product_microservice.domain.exception;

public class InvalidProductPriceException extends RuntimeException {
    public InvalidProductPriceException(String message) {
        super(message);
    }
}