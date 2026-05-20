package com.paulonepotti.microservices.customer_microservice.exception;

public class AddressLimitExceededException extends BusinessRuleException {

    public AddressLimitExceededException() {
        super("El cliente no puede tener más de 5 direcciones.");
    }
}
