package com.paulonepotti.ecommerce.product_microservice.infrastructure.adapter.in.rest.mapper;

import org.springframework.stereotype.Component;

import com.paulonepotti.ecommerce.product_microservice.domain.model.Category;
import com.paulonepotti.ecommerce.product_microservice.infrastructure.adapter.in.rest.dto.CategoryRequest;
import com.paulonepotti.ecommerce.product_microservice.infrastructure.adapter.in.rest.dto.CategoryResponse;

@Component
public class RestCategoryMapper {
    public Category toDomain(CategoryRequest request) {
        return new Category(
            null,
            request.name(),
            request.description()
        );
    }

    public CategoryResponse toResponse(Category category) {
        return new CategoryResponse(
            category.getId(),
            category.getName(),
            category.getDescription()
        );
    }
}
