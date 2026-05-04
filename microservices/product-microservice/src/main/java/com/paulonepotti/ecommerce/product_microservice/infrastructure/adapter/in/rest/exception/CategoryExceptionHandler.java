package com.paulonepotti.ecommerce.product_microservice.infrastructure.adapter.in.rest.exception;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.paulonepotti.ecommerce.product_microservice.domain.exception.InvalidCategoryNameException;
import com.paulonepotti.ecommerce.product_microservice.infrastructure.adapter.in.rest.controller.CategoryController;
import com.paulonepotti.ecommerce.product_microservice.domain.exception.CategoryNotFoundException;
import com.paulonepotti.microservices.common_exceptions.ErrorResponse;

@RestControllerAdvice(assignableTypes = CategoryController.class)
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CategoryExceptionHandler {

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCategoryNotFound(
            CategoryNotFoundException ex) {
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(ErrorResponse.of("CATEGORY_NOT_FOUND", ex.getMessage()));
    }

    @ExceptionHandler(InvalidCategoryNameException.class)
    public ResponseEntity<ErrorResponse> handleInvalidCategoryName(
            InvalidCategoryNameException ex) {
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(ErrorResponse.of("INVALID_CATEGORY_NAME", ex.getMessage()));
    }

}