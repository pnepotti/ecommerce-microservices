package com.paulonepotti.ecommerce.order_microservice.infrastructure.adapter.in.rest.dto;

public record OrderItemResponse(
    Long id,
    Long productId,
    Integer quantity,
    String productName,
    Double price
) {

}
