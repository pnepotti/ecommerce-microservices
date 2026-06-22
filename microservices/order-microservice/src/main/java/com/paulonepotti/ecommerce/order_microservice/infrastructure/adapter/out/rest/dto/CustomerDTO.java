package com.paulonepotti.ecommerce.order_microservice.infrastructure.adapter.out.rest.dto;

public record CustomerDTO(
    Long id,
    String firstName,
    String lastName,
    String email,
    boolean active
) {

}
