package com.paulonepotti.ecommerce.product_microservice.application.port.in;

import java.util.List;

import com.paulonepotti.ecommerce.product_microservice.domain.model.Product;

public interface GetAllProductsUseCase {
    List<Product> getAllProducts(String name, Long categoryId);
}
