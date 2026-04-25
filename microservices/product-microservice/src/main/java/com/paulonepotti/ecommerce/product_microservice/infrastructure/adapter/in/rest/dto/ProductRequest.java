package com.paulonepotti.ecommerce.product_microservice.infrastructure.adapter.in.rest.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ProductRequest(
    @NotBlank(message = "Name is required")
    String name,
    String description,
    @NotNull(message = "Price is required")
    @Positive(message = "Price must be a positive number")
    BigDecimal price,
    @NotNull(message = "Category ID is required")
    Long categoryId
) {

}
