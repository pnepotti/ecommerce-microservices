package com.paulonepotti.ecommerce.product_microservice.infrastructure.adapter.out.persistence.mapper;

import org.springframework.stereotype.Component;

import com.paulonepotti.ecommerce.product_microservice.domain.model.Product;
import com.paulonepotti.ecommerce.product_microservice.infrastructure.adapter.out.persistence.ProductEntity;

@Component
public class ProductMapper {
    public ProductEntity toEntity(Product product) {
        ProductEntity entity = new ProductEntity();
        entity.setName(product.getName());
        entity.setDescription(product.getDescription());
        entity.setPrice(product.getPrice());
        entity.setStock(product.getStock());
        return entity;
    }

    public Product toDomain(ProductEntity entity) {
        return new Product(
            entity.getId(),
            entity.getName(),
            entity.getDescription(),
            entity.getPrice(),
            entity.getStock()
        );
    }

    public void updateEntityFromDomain(Product product, ProductEntity entity) {
        entity.setName(product.getName());
        entity.setDescription(product.getDescription());
        entity.setPrice(product.getPrice());
        entity.setStock(product.getStock());
    }
}
