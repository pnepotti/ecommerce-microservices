package com.paulonepotti.microservices.customer_microservice.exception;

public class CustomerEmailAlreadyExistsException extends BusinessRuleException {

    public CustomerEmailAlreadyExistsException(String email) {
        super("Ya existe un cliente registrado con el email: " + email);
    }

}
