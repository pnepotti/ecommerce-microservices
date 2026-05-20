package com.paulonepotti.microservices.customer_microservice.mapper;

import org.springframework.stereotype.Component;

import com.paulonepotti.microservices.customer_microservice.dto.CustomerRequest;
import com.paulonepotti.microservices.customer_microservice.dto.CustomerResponse;
import com.paulonepotti.microservices.customer_microservice.model.Customer;

@Component
public class CustomerMapper {


    public Customer toEntity(CustomerRequest customerRequest) {
        if (customerRequest == null) return null;
        return Customer.builder()
                .firstName(customerRequest.firstName())
                .lastName(customerRequest.lastName())
                .email(customerRequest.email())
                .active(true)
                .build();
    }

    public void updateEntity(CustomerRequest customerRequest, Customer customer) {
        if (customerRequest == null || customer == null) return;
        customer.setFirstName(customerRequest.firstName());
        customer.setLastName(customerRequest.lastName());
        customer.setEmail(customerRequest.email());
    }

    public CustomerResponse toResponse(Customer customer) {
        if (customer == null) return null;
        return new CustomerResponse(
                customer.getId(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getEmail(),
                customer.isActive(),
                customer.getCreatedAt(),
                customer.getUpdatedAt()
        );
    }

}
