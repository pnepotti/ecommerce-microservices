package com.paulonepotti.ecommerce.order_microservice.infrastructure.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import com.paulonepotti.ecommerce.order_microservice.infrastructure.adapter.out.persistence.entity.OrderEntity;

@Repository
public interface SpringDataOrderRepository extends JpaRepository<OrderEntity, Long> {

    Page<OrderEntity> findByCustomerId(Long customerId, Pageable pageable);
    Page<OrderEntity> findAll(Pageable pageable);

}
