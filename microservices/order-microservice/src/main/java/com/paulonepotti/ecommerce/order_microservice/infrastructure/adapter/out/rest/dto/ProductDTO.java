package com.paulonepotti.ecommerce.order_microservice.infrastructure.adapter.out.rest.dto;

import java.math.BigDecimal;

public record ProductDTO(
    Long id, 
    String name, 
    BigDecimal price, 
    String imageUrl
) {
    
}

