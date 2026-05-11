package com.paulonepotti.ecommerce.order_microservice.infrastructure.adapter.out.rest;

import org.springframework.stereotype.Component;

import com.paulonepotti.ecommerce.order_microservice.application.port.out.ProductPort;
import com.paulonepotti.ecommerce.order_microservice.domain.valueobject.ProductSnapshot;
import com.paulonepotti.ecommerce.order_microservice.infrastructure.adapter.out.rest.dto.ProductDTO;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ProductRestAdapter implements ProductPort {

    private final ProductClient productClient;

    @Override
    public ProductSnapshot getProductById(Long productId) {
        ProductDTO dto = productClient.getProductById(productId);
        return new ProductSnapshot(dto.id(), dto.name(), dto.price(), dto.imageUrl());
    }  

}
