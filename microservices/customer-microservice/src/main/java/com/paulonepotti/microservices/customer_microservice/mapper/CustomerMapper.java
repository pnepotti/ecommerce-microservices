package com.paulonepotti.microservices.customer_microservice.mapper;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.paulonepotti.microservices.customer_microservice.dto.CustomerRequest;
import com.paulonepotti.microservices.customer_microservice.dto.CustomerResponse;
import com.paulonepotti.microservices.customer_microservice.model.Customer;

@Component
public class CustomerMapper {


    public Customer toEntity(CustomerRequest customerRequest) {
        if (customerRequest == null) {
            return null;
        }
        Customer customer = Customer.builder()
                .firstName(customerRequest.firstName())
                .lastName(customerRequest.lastName())
                .email(customerRequest.email())
                .active(true) 
                .createdAt(LocalDateTime.now())
                .build();
        return customer;
    }

    public void updateEntity(CustomerRequest request, Customer customer) {
        if (request == null || customer == null) return;

        customer.setFirstName(request.firstName());
        customer.setLastName(request.lastName());
        customer.setEmail(request.email());
    }

    public CustomerResponse toResponse(Customer customer) {
        if (customer == null) return null;

        return new CustomerResponse(
                customer.getId(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getEmail(),
                customer.isActive(),
                customer.getCreatedAt()
        );
    }

    public List<CustomerResponse> toResponseList(List<Customer> customers) {
        if (customers == null) {
            return Collections.emptyList();
        }
        return customers.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

}
