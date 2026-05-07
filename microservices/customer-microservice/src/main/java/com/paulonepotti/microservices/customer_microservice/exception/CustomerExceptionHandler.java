package com.paulonepotti.microservices.customer_microservice.exception;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.paulonepotti.microservices.common_exceptions.ErrorResponse;
import com.paulonepotti.microservices.common_exceptions.GlobalExceptionHandler;
import com.paulonepotti.microservices.customer_microservice.controller.CustomerController;

import org.springframework.http.HttpStatus;

@RestControllerAdvice(assignableTypes = CustomerController.class)
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CustomerExceptionHandler extends GlobalExceptionHandler {

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCustomerNotFoundException(
            CustomerNotFoundException ex) {
        
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(ErrorResponse.of("CUSTOMER_NOT_FOUND", ex.getMessage())); }

}
