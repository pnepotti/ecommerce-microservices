package com.paulonepotti.ecommerce.order_microservice.infrastructure.adapter.out.rest.mapper;

import org.springframework.stereotype.Component;
import com.paulonepotti.ecommerce.order_microservice.domain.model.OrderItem;
import com.paulonepotti.ecommerce.order_microservice.infrastructure.adapter.out.rest.dto.ProductDTO;
import com.paulonepotti.ecommerce.order_microservice.domain.valueobject.ProductSnapshot;

@Component
public class ProductMapper {

    public OrderItem toOrderItem(ProductDTO dto, Integer quantity) {
        
        ProductSnapshot snapshot = new ProductSnapshot(
            dto.id(),
            dto.name(),
            dto.price(),
            dto.imageUrl()
        );

        return new OrderItem(snapshot, quantity);
    }
}