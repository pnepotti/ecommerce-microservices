package com.paulonepotti.ecommerce.product_microservice.application.port.in;

import com.paulonepotti.ecommerce.product_microservice.domain.model.Category;

public interface UpdateCategoryUseCase {
    Category updateCategory(Long id, Category category);
}
