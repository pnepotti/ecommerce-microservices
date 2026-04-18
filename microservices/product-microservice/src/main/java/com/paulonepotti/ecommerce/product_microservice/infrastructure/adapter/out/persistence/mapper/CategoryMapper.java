package com.paulonepotti.ecommerce.product_microservice.infrastructure.adapter.out.persistence.mapper;

import com.paulonepotti.ecommerce.product_microservice.domain.model.Category;
import com.paulonepotti.ecommerce.product_microservice.infrastructure.adapter.out.persistence.CategoryEntity;

public class CategoryMapper {

    public CategoryEntity toEntity(Category category) {
        if (category == null) {
            return null;
        }
        CategoryEntity entity = new CategoryEntity();
        entity.setId(category.getId());
        entity.setName(category.getName());
        entity.setDescription(category.getDescription());
        return entity;
    }

    public Category toDomain(CategoryEntity entity) {
        if (entity == null) {
            return null;
        }
        return new Category(
            entity.getId(),
            entity.getName(),
            entity.getDescription()
        );
    }

    public void updateEntityFromDomain(Category category, CategoryEntity entity) {
        if (category == null || entity == null) {
            return;
        }
        entity.setName(category.getName());
        entity.setDescription(category.getDescription());
    }

}
