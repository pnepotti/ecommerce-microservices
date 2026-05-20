package com.paulonepotti.microservices.customer_microservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.paulonepotti.microservices.customer_microservice.model.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

    Optional<Address> findByIdAndCustomerId(Long addressId, Long customerId);

    List<Address> findByCustomerId(Long customerId);


}
