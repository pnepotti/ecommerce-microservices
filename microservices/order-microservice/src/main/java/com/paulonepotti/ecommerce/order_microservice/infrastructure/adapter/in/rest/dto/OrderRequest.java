package com.paulonepotti.ecommerce.order_microservice.infrastructure.adapter.in.rest.dto;

import java.math.BigDecimal;
import java.util.List;

import com.paulonepotti.ecommerce.order_microservice.domain.model.OrderStatus;

public record OrderRequest(
    Long customerId,
    OrderStatus status,
    List<OrderItemRequest> items,
    BigDecimal totalAmount
) {
}

