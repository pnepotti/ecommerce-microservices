package com.paulonepotti.ecommerce.product_microservice.infrastructure.config;

import com.paulonepotti.ecommerce.product_microservice.application.port.out.ProductRepositoryPort;
import com.paulonepotti.ecommerce.product_microservice.application.service.ProductService;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductConfig {

    @Bean
    public ProductService productService(ProductRepositoryPort productRepositoryPort) {
        // Le pasamos el adaptador de persistencia al servicio de dominio
        return new ProductService(productRepositoryPort);
    }
    
}
