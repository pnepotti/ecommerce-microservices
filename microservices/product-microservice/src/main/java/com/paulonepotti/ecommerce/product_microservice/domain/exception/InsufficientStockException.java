package com.paulonepotti.ecommerce.product_microservice.domain.exception;

public class InsufficientStockException extends RuntimeException {
    
    public InsufficientStockException(String message) {
        super(message);
    }

}
