package com.paulonepotti.ecommerce.product_microservice.application.port.in;

import com.paulonepotti.ecommerce.product_microservice.domain.model.PageResponse;
import com.paulonepotti.ecommerce.product_microservice.domain.model.Product;

public interface GetAllProductsUseCase {
    PageResponse<Product> getAllProducts(String name, Long categoryId, int page, int size);
}
