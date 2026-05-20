package com.paulonepotti.microservices.customer_microservice.exception;

public class AddressNotFoundException extends BusinessRuleException {

    public AddressNotFoundException(Long addressId) {
        super("No se encontró la dirección con ID: " + addressId);
    }

}
