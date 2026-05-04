package com.paulonepotti.ecommerce.product_microservice.application.port.out;

import java.util.List;
import java.util.Optional;

import com.paulonepotti.ecommerce.product_microservice.domain.model.Category;

public interface CategoryRepositoryPort {

    Category save(Category category);
    Optional<Category> findById(Long id);
    List<Category> findAll();
    void deleteById(Long id);    
    Category update(Category category);
    boolean existsById(Long id);
}
