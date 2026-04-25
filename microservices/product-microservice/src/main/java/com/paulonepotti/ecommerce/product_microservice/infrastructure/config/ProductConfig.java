package com.paulonepotti.ecommerce.product_microservice.infrastructure.config;

import com.paulonepotti.ecommerce.product_microservice.application.port.in.CreateProductUseCase;
import com.paulonepotti.ecommerce.product_microservice.application.port.in.DeleteProductUseCase;
import com.paulonepotti.ecommerce.product_microservice.application.port.in.GetAllProductsUseCase;
import com.paulonepotti.ecommerce.product_microservice.application.port.in.GetProductUseCase;
import com.paulonepotti.ecommerce.product_microservice.application.port.in.UpdateProductUseCase;
import com.paulonepotti.ecommerce.product_microservice.application.port.out.ProductRepositoryPort;
import com.paulonepotti.ecommerce.product_microservice.application.service.ProductService;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductConfig {

    @Bean
    public ProductService productService(ProductRepositoryPort port) {
        return new ProductService(port);
    }
    
    @Bean
    public CreateProductUseCase createProductUseCase(ProductRepositoryPort port) {
        return new ProductService(port);
    }

    @Bean
    public GetProductUseCase getProductUseCase(ProductRepositoryPort port) {
        return new ProductService(port);
    }

    @Bean
    public GetAllProductsUseCase getAllProductsUseCase(ProductRepositoryPort port) {
        return new ProductService(port);
    }

    @Bean
    public UpdateProductUseCase updateProductUseCase(ProductRepositoryPort port) {
        return new ProductService(port);
    }

    @Bean
    public DeleteProductUseCase deleteProductUseCase(ProductRepositoryPort port) {
        return new ProductService(port);
    }


    
}
