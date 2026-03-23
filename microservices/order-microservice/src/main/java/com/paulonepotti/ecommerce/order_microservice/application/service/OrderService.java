package com.paulonepotti.ecommerce.order_microservice.application.service;

import com.paulonepotti.ecommerce.order_microservice.infrastructure.adapter.out.rest.dto.ProductDTO;
import com.paulonepotti.ecommerce.order_microservice.infrastructure.adapter.out.rest.ProductClient;
import com.paulonepotti.ecommerce.order_microservice.domain.model.Order;
import com.paulonepotti.ecommerce.order_microservice.application.port.in.CreateOrderUseCase;
import com.paulonepotti.ecommerce.order_microservice.application.port.out.OrderRepositoryPort;

public class OrderService implements CreateOrderUseCase {

    private final OrderRepositoryPort orderRepositoryPort;
    private final ProductClient productClient; // Nuestro cliente Feign

    public OrderService(OrderRepositoryPort orderRepositoryPort, ProductClient productClient) {
        this.orderRepositoryPort = orderRepositoryPort;
        this.productClient = productClient;
    }

    @Override
    public Order createOrder(Order orderRequest) {
        // 1. Validar y enriquecer cada ítem con datos del Product-Microservice
        orderRequest.getItems().forEach(item -> {
            // Llamada inter-servicio via Feign
            ProductDTO product = productClient.getProductById(item.getProductId());

            // Regla de Negocio: Validar Stock
            if (product.stock() < item.getQuantity()) {
                throw new RuntimeException("Stock insuficiente para: " + product.name());
            }

            // Regla de Negocio: "Congelar" el precio actual del catálogo
            item.setUnitPrice(product.price());
        });

        // 2. Calcular el total de la orden
        Double total = orderRequest.getItems().stream()
                .mapToDouble(item -> item.getUnitPrice() * item.getQuantity())
                .sum();
        
        orderRequest.setTotalAmount(total);

        // 3. Persistir la orden en la base de datos propia
        return orderRepositoryPort.save(orderRequest);
    }
}
