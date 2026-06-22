package com.paulonepotti.ecommerce.order_microservice.application.port.out;

import com.paulonepotti.ecommerce.order_microservice.domain.valueobject.ProductSnapshot;

public interface ProductPort {
    ProductSnapshot getProductById(Long productId);
    void decreaseStock(Long productId, int quantity);
    void increaseStock(Long productId, int quantity);
}
