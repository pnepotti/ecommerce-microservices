package com.paulonepotti.ecommerce.product_microservice.infrastructure.adapter.out.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.paulonepotti.ecommerce.product_microservice.infrastructure.adapter.out.persistence.entity.ProductEntity;

@Repository
public interface SpringDataProductRepository extends JpaRepository<ProductEntity, Long> {

    List<ProductEntity> findByName(String name);
    List<ProductEntity> findByCategoryId(Long categoryId);
}
