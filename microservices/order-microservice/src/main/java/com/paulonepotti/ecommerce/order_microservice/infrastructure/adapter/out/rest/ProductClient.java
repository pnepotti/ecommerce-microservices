package com.paulonepotti.ecommerce.order_microservice.infrastructure.adapter.out.rest;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.paulonepotti.ecommerce.order_microservice.infrastructure.adapter.out.rest.dto.ProductDTO;

// El nombre debe coincidir con spring.application.name del servicio de productos
@FeignClient(name = "product-microservice") 
public interface ProductClient {

    @GetMapping("/api/v1/products/{id}")
    ProductDTO getProductById(@PathVariable("id") Long id);
}