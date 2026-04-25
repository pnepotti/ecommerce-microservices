package com.paulonepotti.ecommerce.product_microservice.domain.exception;

public class InvalidCategoryNameException extends RuntimeException {
    public InvalidCategoryNameException(String message) {
        super(message);
    }
}
