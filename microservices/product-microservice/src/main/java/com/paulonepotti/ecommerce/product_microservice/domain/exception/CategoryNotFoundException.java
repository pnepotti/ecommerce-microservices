package com.paulonepotti.ecommerce.product_microservice.domain.exception;

public class CategoryNotFoundException extends RuntimeException {
    public CategoryNotFoundException(Long categoryId) {
        super("Categoría no encontrada con id: " + categoryId);
    }
}
