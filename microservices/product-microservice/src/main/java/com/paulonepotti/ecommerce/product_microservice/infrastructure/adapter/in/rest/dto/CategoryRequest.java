package com.paulonepotti.ecommerce.product_microservice.infrastructure.adapter.in.rest.dto;

import jakarta.validation.constraints.NotBlank;

public record CategoryRequest(
    @NotBlank(message = "Name is required")
    String name,
    String description
) {
}
