package com.paulonepotti.ecommerce.product_microservice.infrastructure.adapter.out.persistence.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.paulonepotti.ecommerce.product_microservice.infrastructure.adapter.out.persistence.entity.ProductEntity;

@Repository
public interface SpringDataProductRepository extends JpaRepository<ProductEntity, Long> {

       @Query("SELECT p FROM ProductEntity p WHERE " +
           "(:name IS NULL OR LOWER(p.name) LIKE LOWER(CAST(:name AS string))) AND " +
           "(:categoryId IS NULL OR p.category.id = :categoryId)")
       Page<ProductEntity> findAllWithFilters(
            @Param("name") String name, 
            @Param("categoryId") Long categoryId, 
            Pageable pageable);
       @Modifying(clearAutomatically = true) 
       @Query("UPDATE ProductEntity p SET p.stock = p.stock - :quantity " +
            "WHERE p.id = :id AND p.stock >= :quantity")
       int decreaseStock(@Param("id") Long id, @Param("quantity") Integer quantity);

       @Modifying(clearAutomatically = true) 
       @Query("UPDATE ProductEntity p SET p.stock = p.stock + :quantity " +
            "WHERE p.id = :id")
       int increaseStock(@Param("id") Long id, @Param("quantity") Integer quantity);
}

