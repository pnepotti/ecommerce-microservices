package com.paulonepotti.microservices.customer_microservice.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.paulonepotti.microservices.customer_microservice.service.AddressService;
import com.paulonepotti.microservices.customer_microservice.service.CustomerService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.paulonepotti.microservices.customer_microservice.dto.AddressRequest;
import com.paulonepotti.microservices.customer_microservice.dto.AddressResponse;
import com.paulonepotti.microservices.customer_microservice.dto.CustomerRequest;
import com.paulonepotti.microservices.customer_microservice.dto.CustomerResponse;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
@Tag(name = "Customers", description = "Endpoints para gestionar clientes y sus direcciones")
public class CustomerController {

    private final CustomerService customerService;
    private final AddressService addressService;


    @GetMapping
    public ResponseEntity<Page<CustomerResponse>> getAllCustomers(
            @PageableDefault(size = 10) Pageable pageable) {
        Page<CustomerResponse> customers = customerService.getAllCustomers(pageable);
        return ResponseEntity.ok(customers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponse> getCustomerById(@PathVariable Long id) {
        return ResponseEntity.ok(customerService.getCustomerById(id));
    }

    @PostMapping
    public ResponseEntity<CustomerResponse> createCustomer(
            @Valid @RequestBody CustomerRequest request) {
        CustomerResponse createdCustomer = customerService.createCustomer(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCustomer);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerResponse> updateCustomer(
            @PathVariable Long id, 
            @Valid @RequestBody CustomerRequest customerRequest) {
        CustomerResponse updatedCustomer = customerService.updateCustomer(id, customerRequest);
        return ResponseEntity.ok(updatedCustomer);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/addresses")
    public ResponseEntity<AddressResponse> addAddress(
            @PathVariable(value = "id") Long customerId,
            @Valid @RequestBody AddressRequest request) {
        AddressResponse address = addressService.addAddress(customerId, request);
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(address);
    }

    @GetMapping("/{id}/addresses")
    public ResponseEntity<List<AddressResponse>> getAddresses(
            @PathVariable(value = "id") Long customerId) {
        return ResponseEntity.ok(addressService.getAddresses(customerId));
    }

    @GetMapping("/{id}/addresses/{addressId}")
    public ResponseEntity<AddressResponse> getAddress(
            @PathVariable(value = "id") Long customerId,
            @PathVariable Long addressId) {
        return ResponseEntity.ok(addressService.getAddress(customerId, addressId));
    }    
    @DeleteMapping("/{id}/addresses/{addressId}")
    public ResponseEntity<Void> deleteAddress(
            @PathVariable(value = "id") Long customerId,
            @PathVariable Long addressId) {
        addressService.deleteAddress(customerId, addressId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/addresses/{addressId}")
    public ResponseEntity<AddressResponse> updateAddress(
            @PathVariable(value = "id") Long customerId,
            @PathVariable Long addressId,
            @RequestBody @Valid AddressRequest request) {
        return ResponseEntity.ok(addressService.updateAddress(customerId, addressId, request));
    }

}
