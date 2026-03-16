package com.paulonepotti.ecommerce.product_microservice.application.port.in;

import com.paulonepotti.ecommerce.product_microservice.domain.model.Product;

public interface CreateProductUseCase {
    Product createProduct(Product product);
}
