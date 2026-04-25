package com.paulonepotti.ecommerce.product_microservice.application.port.in;

import java.util.List;

import com.paulonepotti.ecommerce.product_microservice.domain.model.Category;

public interface GetAllCategoriesUseCase {
    List<Category> getAllCategories();
}
