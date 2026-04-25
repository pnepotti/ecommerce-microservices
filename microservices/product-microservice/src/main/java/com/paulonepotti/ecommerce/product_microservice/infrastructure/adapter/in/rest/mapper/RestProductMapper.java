package com.paulonepotti.ecommerce.product_microservice.infrastructure.adapter.in.rest.mapper;

import org.springframework.stereotype.Component;

import com.paulonepotti.ecommerce.product_microservice.domain.model.Product;
import com.paulonepotti.ecommerce.product_microservice.infrastructure.adapter.in.rest.dto.ProductRequest;
import com.paulonepotti.ecommerce.product_microservice.infrastructure.adapter.in.rest.dto.ProductResponse;

@Component
public class RestProductMapper {
    public Product toDomain(ProductRequest request) {
        return new Product(
            null,  // ID será generado por BD
            request.name(),
            request.description(),
            request.price(),
            request.categoryId()
        );
    }
    
    public ProductResponse toResponse(Product product) {
        return new ProductResponse(
            product.getId(),
            product.getName(),
            product.getDescription(),
            product.getPrice(),
            product.getCategoryId()
        );
    }
}
