package com.paulonepotti.ecommerce.product_microservice.infrastructure.adapter.in.rest.exception;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.paulonepotti.microservices.common_exceptions.ErrorResponse;
import com.paulonepotti.ecommerce.product_microservice.domain.exception.CategoryNotFoundException;
import com.paulonepotti.ecommerce.product_microservice.domain.exception.InvalidProductPriceException;
import com.paulonepotti.ecommerce.product_microservice.domain.exception.ProductNotFoundException;
import com.paulonepotti.ecommerce.product_microservice.infrastructure.adapter.in.rest.controller.ProductController;

@RestControllerAdvice(assignableTypes = ProductController.class)
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ProductExceptionHandler {
    
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleProductNotFound(
            ProductNotFoundException ex) {
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(ErrorResponse.of("PRODUCT_NOT_FOUND", ex.getMessage())); 
    }

    @ExceptionHandler(InvalidProductPriceException.class)
    public ResponseEntity<ErrorResponse> handleInvalidPrice(
            InvalidProductPriceException ex) {
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(ErrorResponse.of("INVALID_PRICE", ex.getMessage())); 
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCategoryNotFound(
            CategoryNotFoundException ex) {
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(ErrorResponse.of("CATEGORY_NOT_FOUND", ex.getMessage()));
    }

}
