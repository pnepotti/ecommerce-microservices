package com.paulonepotti.ecommerce.product_microservice.infrastructure.adapter.in.rest.dto;

public record CategoryResponse(
    Long id,
    String name,
    String description
) {
}
