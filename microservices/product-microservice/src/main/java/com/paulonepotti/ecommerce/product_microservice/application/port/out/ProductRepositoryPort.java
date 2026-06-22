package com.paulonepotti.ecommerce.product_microservice.application.port.out;

import java.util.Optional;

import com.paulonepotti.ecommerce.product_microservice.domain.model.PageResponse;
import com.paulonepotti.ecommerce.product_microservice.domain.model.Product;

public interface ProductRepositoryPort {
    
    Product save(Product product);
    Optional<Product> findById(Long id);
    PageResponse<Product> findAll(String name, Long categoryId, int page, int size);
    void deleteById(Long id);
    Product update(Product product);
    Product decreaseStock(Long id, Integer quantity);
    Product increaseStock(Long id, Integer quantity);
}
