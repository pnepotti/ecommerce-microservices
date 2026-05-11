package com.paulonepotti.ecommerce.order_microservice.domain.exception;

public class OrderNotFoundException extends RuntimeException {

    public OrderNotFoundException(Long orderId) {
        super("Orden no encontrada con ID: " + orderId);
    }

}
