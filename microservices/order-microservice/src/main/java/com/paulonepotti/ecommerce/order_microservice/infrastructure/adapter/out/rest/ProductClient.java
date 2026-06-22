package com.paulonepotti.ecommerce.order_microservice.infrastructure.adapter.out.rest;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.paulonepotti.ecommerce.order_microservice.infrastructure.adapter.out.rest.dto.ProductDTO;

@FeignClient(name = "product-microservice") 
public interface ProductClient {

    @GetMapping("/api/v1/products/{id}")
    ProductDTO getProductById(@PathVariable("id") Long id);

    @PatchMapping("/api/v1/products/{id}/stock/decrease")
    void decreaseStock(@PathVariable("id") Long id, @RequestParam("quantity") int quantity);

    @PatchMapping("/api/v1/products/{id}/stock/increase")
    void increaseStock(@PathVariable("id") Long id, @RequestParam("quantity") int quantity);

}