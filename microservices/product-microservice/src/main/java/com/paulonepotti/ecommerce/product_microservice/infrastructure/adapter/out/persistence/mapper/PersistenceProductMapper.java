package com.paulonepotti.ecommerce.product_microservice.infrastructure.adapter.out.persistence.mapper;

import org.springframework.stereotype.Component;

import com.paulonepotti.ecommerce.product_microservice.domain.model.Product;
import com.paulonepotti.ecommerce.product_microservice.infrastructure.adapter.out.persistence.entity.CategoryEntity;
import com.paulonepotti.ecommerce.product_microservice.infrastructure.adapter.out.persistence.entity.ProductEntity;

@Component
public class PersistenceProductMapper {
    
    public ProductEntity toEntity(Product product) {
        ProductEntity entity = new ProductEntity();
        entity.setId(product.getId());
        entity.setName(product.getName());
        entity.setDescription(product.getDescription());
        entity.setPrice(product.getPrice());
        if (product.getCategoryId() != null) {
            CategoryEntity category = new CategoryEntity();
            category.setId(product.getCategoryId());
            entity.setCategory(category);
        }
        return entity;
    }

    public Product toDomain(ProductEntity entity) {
        return new Product( 
            entity.getId(),
            entity.getName(),
            entity.getDescription(),
            entity.getPrice(),
            entity.getCategory() != null ? entity.getCategory().getId() : null
        );
    }

    public void updateEntityFromDomain(Product product, ProductEntity entity) {

        entity.setName(product.getName());
        entity.setDescription(product.getDescription());
        entity.setPrice(product.getPrice());

        Long categoryId = product.getCategoryId();

        if (categoryId != null) {
            if (entity.getCategory() == null || !entity.getCategory().getId().equals(categoryId)) {
                CategoryEntity category = new CategoryEntity();
                category.setId(categoryId);
                entity.setCategory(category);
            }
        } else { 
            entity.setCategory(null);
        }
    }
}