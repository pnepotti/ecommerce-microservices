package com.paulonepotti.ecommerce.product_microservice.application.port.in;

import com.paulonepotti.ecommerce.product_microservice.domain.model.Product;

public interface UpdateProductUseCase {    
    Product updateProduct(Long id, Product product);
}
