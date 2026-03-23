package com.paulonepotti.ecommerce.order_microservice.infrastructure.config;

import com.paulonepotti.ecommerce.order_microservice.application.port.out.OrderRepositoryPort;
import com.paulonepotti.ecommerce.order_microservice.infrastructure.adapter.out.rest.ProductClient;
import com.paulonepotti.ecommerce.order_microservice.application.service.OrderService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class OrderConfig {

    @Bean
    public OrderService orderService(OrderRepositoryPort orderRepositoryPort, ProductClient productClient) {
        return new OrderService(orderRepositoryPort, productClient);
    }
}
