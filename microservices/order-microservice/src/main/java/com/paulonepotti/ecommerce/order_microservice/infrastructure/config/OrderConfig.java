package com.paulonepotti.ecommerce.order_microservice.infrastructure.config;

import com.paulonepotti.ecommerce.order_microservice.application.port.out.OrderRepositoryPort;
import com.paulonepotti.ecommerce.order_microservice.application.port.out.ProductPort;
import com.paulonepotti.ecommerce.order_microservice.application.service.OrderService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class OrderConfig {

    @Bean
    public OrderService orderService(OrderRepositoryPort orderRepositoryPort, ProductPort productPort) {
        return new OrderService(orderRepositoryPort, productPort);
    }
}
