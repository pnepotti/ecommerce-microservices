package com.paulonepotti.microservices.customer_microservice.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paulonepotti.microservices.customer_microservice.dto.CustomerRequest;
import com.paulonepotti.microservices.customer_microservice.dto.CustomerResponse;
import com.paulonepotti.microservices.customer_microservice.exception.CustomerEmailAlreadyExistsException;
import com.paulonepotti.microservices.customer_microservice.exception.CustomerNotFoundException;
import com.paulonepotti.microservices.customer_microservice.mapper.CustomerMapper;
import com.paulonepotti.microservices.customer_microservice.model.Customer;
import com.paulonepotti.microservices.customer_microservice.repository.CustomerRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Transactional(readOnly = true)
    public List<CustomerResponse> getAllCustomers() {
        return customerRepository.findAll()
            .stream()
            .map(customerMapper::toResponse)
            .toList();
    }

    @Transactional(readOnly = true)
    public CustomerResponse getCustomerById(Long customerId) {
        Customer customer = findCustomerOrThrow(customerId);
        
        return customerMapper.toResponse(customer);
    }

    @Transactional
    public CustomerResponse createCustomer(CustomerRequest customerRequest) {
        if (customerRepository.existsByEmail(customerRequest.email())) {
            throw new CustomerEmailAlreadyExistsException(customerRequest.email());
        }

        Customer newCustomer = customerMapper.toEntity(customerRequest);
        Customer savedCustomer = customerRepository.save(newCustomer);
        return customerMapper.toResponse(savedCustomer);
    }

    @Transactional
    public CustomerResponse updateCustomer(Long customerId, CustomerRequest customerRequest) {
        Customer customerToUpdate = findCustomerOrThrow(customerId);

        if (customerRepository.existsByEmailAndIdNot(customerRequest.email(), customerId)) {
            throw new CustomerEmailAlreadyExistsException(customerRequest.email());
        }

        customerMapper.updateEntity(customerRequest, customerToUpdate);
        Customer updatedCustomer = customerRepository.save(customerToUpdate);
        return customerMapper.toResponse(updatedCustomer);
    }

    @Transactional
    public void deleteCustomer(Long customerId) {
        Customer customer = findCustomerOrThrow(customerId);

        customerRepository.delete(customer);
    }

    private Customer findCustomerOrThrow(Long customerId) {
        Customer customer = customerRepository.findById(customerId)
            .orElseThrow(() -> new CustomerNotFoundException(customerId));
        return customer;
    }

}
