package com.paulonepotti.ecommerce.order_microservice.infrastructure.adapter.in.rest.dto;

public record OrderItemRequest(
    Long productId,
    Integer quantity
) {

}
