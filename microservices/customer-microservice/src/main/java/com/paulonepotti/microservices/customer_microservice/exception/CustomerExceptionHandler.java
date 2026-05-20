package com.paulonepotti.microservices.customer_microservice.exception;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.paulonepotti.microservices.common_exceptions.ErrorResponse;
import com.paulonepotti.microservices.common_exceptions.GlobalExceptionHandler;

import org.springframework.http.HttpStatus;

@RestControllerAdvice(basePackages = "com.paulonepotti.microservices.customer_microservice")
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CustomerExceptionHandler extends GlobalExceptionHandler {

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCustomerNotFoundException(
            CustomerNotFoundException ex) {
        
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(ErrorResponse.of("CUSTOMER_NOT_FOUND", ex.getMessage())); }

    @ExceptionHandler(CustomerEmailAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleCustomerEmailAlreadyExists(
            CustomerEmailAlreadyExistsException ex) {
        return ResponseEntity
            .status(HttpStatus.CONFLICT)
            .body(ErrorResponse.of("CUSTOMER_EMAIL_ALREADY_EXISTS", ex.getMessage()));
    }


    @ExceptionHandler(AddressNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleAddressNotFound(AddressNotFoundException ex) {
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(ErrorResponse.of("ADDRESS_NOT_FOUND", ex.getMessage()));
    }

    @ExceptionHandler(AddressLimitExceededException.class)
    public ResponseEntity<ErrorResponse> handleAddressLimitExceeded(AddressLimitExceededException ex) {
        return ResponseEntity
            .status(HttpStatus.CONFLICT)
            .body(ErrorResponse.of("ADDRESS_LIMIT_EXCEEDED", ex.getMessage()));
    }

}
