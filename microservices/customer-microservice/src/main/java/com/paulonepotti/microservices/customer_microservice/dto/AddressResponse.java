package com.paulonepotti.microservices.customer_microservice.dto;

public record AddressResponse(
    Long id,
    String street,
    String number,
    String city,
    String province,
    String postalCode
) {

}
