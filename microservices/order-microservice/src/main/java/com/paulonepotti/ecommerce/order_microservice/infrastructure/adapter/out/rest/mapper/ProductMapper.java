package com.paulonepotti.ecommerce.order_microservice.infrastructure.adapter.out.rest.mapper;

import org.springframework.stereotype.Component;
import com.paulonepotti.ecommerce.order_microservice.domain.model.OrderItem;
import com.paulonepotti.ecommerce.order_microservice.infrastructure.adapter.out.rest.dto.ProductDTO;

@Component
public class ProductMapper {

    public OrderItem toOrderItem(ProductDTO dto, Integer quantity) {
        return new OrderItem(
            null, // El ID se generará al guardar la orden
            dto.id(),
            quantity,
            dto.price() // Aquí "congelamos" el precio del DTO al dominio
        );
    }
}