package com.paulonepotti.ecommerce.order_microservice.infrastructure.adapter.in.rest.mapper;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Component;

import com.paulonepotti.ecommerce.order_microservice.domain.model.Order;
import com.paulonepotti.ecommerce.order_microservice.domain.model.OrderItem;
import com.paulonepotti.ecommerce.order_microservice.domain.model.PageResponse;
import com.paulonepotti.ecommerce.order_microservice.domain.valueobject.ProductSnapshot;
import com.paulonepotti.ecommerce.order_microservice.infrastructure.adapter.in.rest.dto.OrderItemRequest;
import com.paulonepotti.ecommerce.order_microservice.infrastructure.adapter.in.rest.dto.OrderItemResponse;
import com.paulonepotti.ecommerce.order_microservice.infrastructure.adapter.in.rest.dto.OrderRequest;
import com.paulonepotti.ecommerce.order_microservice.infrastructure.adapter.in.rest.dto.OrderResponse;

@Component
public class OrderMapper {

    public Order toDomain(OrderRequest request) {
        List<OrderItem> items = request.items().stream()
            .map(this::toOrderItem)
            .toList();
            
        return new Order(
            request.customerId(),
            items
        );
    }

    private OrderItem toOrderItem(OrderItemRequest request) {
        ProductSnapshot product = new ProductSnapshot(
            request.productId(),
            "TEMP",
            BigDecimal.ZERO,
            null
        );

        return new OrderItem(product, request.quantity());
    }

    public OrderResponse toResponse(Order order) {
        List<OrderItemResponse> items = order.getItems().stream()
            .map(this::toOrderItemResponse)
            .toList();

        return new OrderResponse(
            order.getId(),
            order.getCustomerId(),
            order.getStatus(),
            items,
            order.getTotalAmount()
        );
    }

    private OrderItemResponse toOrderItemResponse(OrderItem item) {
        ProductSnapshot product = item.getProduct();

        return new OrderItemResponse(
            item.getId(),
            product.getProductId(),
            item.getQuantity(),
            product.getName(),
            item.getUnitPrice().doubleValue()
        );
    }

    public PageResponse<OrderResponse> toResponsePage(PageResponse<Order> page) {
    return new PageResponse<>(
        page.content().stream().map(this::toResponse).toList(),
        page.pageNumber(),
        page.pageSize(),
        page.totalElements(),
        page.totalPages(),
        page.last()
    );
}
}
