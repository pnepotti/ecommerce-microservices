package com.paulonepotti.ecommerce.product_microservice.infrastructure.adapter.out.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.paulonepotti.ecommerce.product_microservice.infrastructure.adapter.out.persistence.entity.CategoryEntity;

public interface SpringDataCategoryRepository extends JpaRepository<CategoryEntity, Long> {

}
