package com.paulonepotti.ecommerce.order_microservice.application.port.out;

import com.paulonepotti.ecommerce.order_microservice.domain.valueobject.ProductSnapshot;

public interface ProductPort {

    ProductSnapshot getProductById(Long productId);
}
