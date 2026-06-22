package com.paulonepotti.ecommerce.order_microservice.infrastructure.adapter.out.rest;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.paulonepotti.ecommerce.order_microservice.infrastructure.adapter.out.rest.dto.CustomerDTO;

@FeignClient(name = "customer-microservice")
public interface CustomerClient {

    @GetMapping("/api/v1/customers/{id}")
    CustomerDTO getCustomerById(@PathVariable Long id);

}
