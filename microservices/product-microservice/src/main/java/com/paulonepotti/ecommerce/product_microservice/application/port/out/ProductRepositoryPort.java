package com.paulonepotti.ecommerce.product_microservice.application.port.out;

import java.util.Optional;
import java.util.List;

import com.paulonepotti.ecommerce.product_microservice.domain.model.Product;

public interface ProductRepositoryPort {
    
    Product save(Product product);
    Optional<Product> findById(Long id);
    List<Product> findAll();
    List<Product> findByName(String name);
    List<Product> findByCategory(Long categoryId);
    void deleteById(Long id);
    Product update(Product product);
}
