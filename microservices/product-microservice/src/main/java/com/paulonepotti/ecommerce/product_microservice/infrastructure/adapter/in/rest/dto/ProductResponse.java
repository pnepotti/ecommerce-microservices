package com.paulonepotti.ecommerce.product_microservice.infrastructure.adapter.in.rest.dto;

import java.math.BigDecimal;

public record ProductResponse(
    Long id,
    String name,
    String description,
    BigDecimal price,
    Long categoryId
) {

}
