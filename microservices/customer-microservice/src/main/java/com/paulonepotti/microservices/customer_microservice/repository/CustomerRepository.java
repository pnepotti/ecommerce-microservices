package com.paulonepotti.microservices.customer_microservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.paulonepotti.microservices.customer_microservice.model.Customer;

import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
