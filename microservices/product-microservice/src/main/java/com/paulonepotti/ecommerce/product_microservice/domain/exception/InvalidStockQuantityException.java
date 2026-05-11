package com.paulonepotti.ecommerce.product_microservice.domain.exception;

public class InvalidStockQuantityException extends RuntimeException {
    
    public InvalidStockQuantityException(String message) {
        super(message);
    }

}
