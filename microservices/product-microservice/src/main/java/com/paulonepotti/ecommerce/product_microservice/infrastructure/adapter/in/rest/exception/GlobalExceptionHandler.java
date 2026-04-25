package com.paulonepotti.ecommerce.product_microservice.infrastructure.adapter.in.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.paulonepotti.ecommerce.product_microservice.domain.exception.InvalidProductPriceException;
import com.paulonepotti.ecommerce.product_microservice.domain.exception.ProductNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleProductNotFound(
        ProductNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(new ErrorResponse("PRODUCT_NOT_FOUND", ex.getMessage()));
    }
    
    @ExceptionHandler(InvalidProductPriceException.class)
    public ResponseEntity<ErrorResponse> handleInvalidPrice(
        InvalidProductPriceException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(new ErrorResponse("INVALID_PRICE", ex.getMessage()));
    }

}
