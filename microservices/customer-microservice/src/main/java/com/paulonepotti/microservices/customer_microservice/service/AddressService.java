package com.paulonepotti.microservices.customer_microservice.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paulonepotti.microservices.customer_microservice.dto.AddressRequest;
import com.paulonepotti.microservices.customer_microservice.dto.AddressResponse;
import com.paulonepotti.microservices.customer_microservice.exception.AddressNotFoundException;
import com.paulonepotti.microservices.customer_microservice.exception.CustomerNotFoundException;
import com.paulonepotti.microservices.customer_microservice.mapper.AddressMapper;
import com.paulonepotti.microservices.customer_microservice.model.Address;
import com.paulonepotti.microservices.customer_microservice.model.Customer;
import com.paulonepotti.microservices.customer_microservice.repository.AddressRepository;
import com.paulonepotti.microservices.customer_microservice.repository.CustomerRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final CustomerRepository customerRepository;
    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;

    @Transactional
    public AddressResponse addAddress(Long customerId, AddressRequest request) {
        Customer customer = findCustomerOrThrow(customerId);
        Address address = addressMapper.toEntity(request);
        customer.addAddress(address);
        Address savedAddress = addressRepository.saveAndFlush(address);
        return addressMapper.toAddressResponse(savedAddress);
    }

    @Transactional(readOnly = true)
    public List<AddressResponse> getAddresses(Long customerId) {
        findCustomerOrThrow(customerId);
        return addressRepository.findByCustomerId(customerId)
                .stream()
                .map(addressMapper::toAddressResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public AddressResponse getAddress(Long customerId, Long addressId) {
        findCustomerOrThrow(customerId);
        Address address = addressRepository.findByIdAndCustomerId(addressId, customerId)
                .orElseThrow(() -> new AddressNotFoundException(addressId));
        return addressMapper.toAddressResponse(address);
    }

    @Transactional
    public void deleteAddress(Long customerId, Long addressId) {
        Customer customer = findCustomerOrThrow(customerId);
        Address address = addressRepository.findByIdAndCustomerId(addressId, customerId)
                .orElseThrow(() -> new AddressNotFoundException(addressId));
        customer.removeAddress(address);
        customerRepository.save(customer);
    }

    @Transactional
    public AddressResponse updateAddress(Long customerId, Long addressId, AddressRequest request) {
        findCustomerOrThrow(customerId);
        Address address = addressRepository.findByIdAndCustomerId(addressId, customerId)
                .orElseThrow(() -> new AddressNotFoundException(addressId));
        addressMapper.updateEntity(request, address);
        addressRepository.save(address);
        return addressMapper.toAddressResponse(address);
    }

    private Customer findCustomerOrThrow(Long customerId) {
        return customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException(customerId));
    }
}
