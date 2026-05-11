package com.paulonepotti.ecommerce.order_microservice.infrastructure.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.paulonepotti.ecommerce.order_microservice.infrastructure.adapter.out.persistence.entity.OrderEntity;

@Repository
public interface SpringDataOrderRepository extends JpaRepository<OrderEntity, Long> {

}
