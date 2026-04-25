package com.paulonepotti.ecommerce.product_microservice.application.port.in;

import com.paulonepotti.ecommerce.product_microservice.domain.model.Category;

public interface GetCategoryUseCase {
    Category getCategoryById(Long id);
}
