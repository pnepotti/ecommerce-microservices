package com.paulonepotti.ecommerce.product_microservice.domain.exception;

public class InvalidProductStockException extends RuntimeException {
    
    public InvalidProductStockException(String message) {
        super(message);
    }

}
