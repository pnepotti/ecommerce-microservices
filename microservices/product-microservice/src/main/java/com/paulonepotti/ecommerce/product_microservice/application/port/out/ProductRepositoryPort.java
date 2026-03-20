package com.paulonepotti.ecommerce.product_microservice.application.port.out;

import java.util.Optional;

import com.paulonepotti.ecommerce.product_microservice.domain.model.Product;

public interface ProductRepositoryPort {
    Product save(Product product);
    Optional<Product> findById(Long id);
    void deleteById(Long id);
    Product update(Product product);
}
