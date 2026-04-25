package com.paulonepotti.ecommerce.product_microservice.domain.exception;

public class InvalidCategoryException extends RuntimeException {
    public InvalidCategoryException(Long categoryId) {
        super("Categoría inválida: " + categoryId);
    }
}