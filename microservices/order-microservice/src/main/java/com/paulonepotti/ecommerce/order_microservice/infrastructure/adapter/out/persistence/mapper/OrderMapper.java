package com.paulonepotti.ecommerce.order_microservice.infrastructure.adapter.out.persistence.mapper;

import com.paulonepotti.ecommerce.order_microservice.domain.model.Order;
import com.paulonepotti.ecommerce.order_microservice.infrastructure.adapter.out.persistence.entity.OrderEntity;
import com.paulonepotti.ecommerce.order_microservice.infrastructure.adapter.out.persistence.entity.OrderItemEntity;

import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class OrderMapper {

    public OrderEntity toEntity(Order domain) {
        OrderEntity entity = new OrderEntity();
        entity.setId(domain.getId());
        entity.setStatus(domain.getStatus());
        entity.setTotalAmount(domain.getTotalAmount());

        if (domain.getItems() != null) {
            entity.setItems(domain.getItems().stream()
                .map(item -> {
                    OrderItemEntity itemEntity = new OrderItemEntity();
                    itemEntity.setProductId(item.getProduct().getProductId());
                    itemEntity.setQuantity(item.getQuantity());
                    itemEntity.setUnitPrice(item.getUnitPrice());
                    itemEntity.setOrder(entity); // Seteamos la relación hacia el padre
                    return itemEntity;
                })
                .collect(Collectors.toList()));
        }

        return entity;
    }

    public Order toDomain(OrderEntity entity) {
        return new Order(entity.getId(), null);
    }
}
