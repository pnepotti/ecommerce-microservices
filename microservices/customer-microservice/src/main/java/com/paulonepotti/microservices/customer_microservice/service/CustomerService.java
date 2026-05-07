package com.paulonepotti.microservices.customer_microservice.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paulonepotti.microservices.customer_microservice.dto.CustomerRequest;
import com.paulonepotti.microservices.customer_microservice.dto.CustomerResponse;
import com.paulonepotti.microservices.customer_microservice.exception.CustomerNotFoundException;
import com.paulonepotti.microservices.customer_microservice.mapper.CustomerMapper;
import com.paulonepotti.microservices.customer_microservice.model.Customer;
import com.paulonepotti.microservices.customer_microservice.repository.CustomerRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;


    public List<CustomerResponse> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return customerMapper.toResponseList(customers);
    }

    public CustomerResponse getCustomerById(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(id));
        return customerMapper.toResponse(customer);
    }

    public CustomerResponse createCustomer(CustomerRequest customerRequest) {
        Customer customer = customerMapper.toEntity(customerRequest);
        Customer savedCustomer = customerRepository.save(customer);
        return customerMapper.toResponse(savedCustomer);
    }

    public CustomerResponse updateCustomer(Long id, CustomerRequest customerRequest) {
        Customer existingCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(id));
        
        customerMapper.updateEntity(customerRequest, existingCustomer);
        return customerMapper.toResponse(customerRepository.save(existingCustomer));
    }

    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }


}
