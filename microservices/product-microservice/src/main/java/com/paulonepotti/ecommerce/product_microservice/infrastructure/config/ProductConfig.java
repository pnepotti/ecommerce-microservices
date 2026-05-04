package com.paulonepotti.ecommerce.product_microservice.infrastructure.config;

import com.paulonepotti.ecommerce.product_microservice.application.port.in.CreateProductUseCase;
import com.paulonepotti.ecommerce.product_microservice.application.port.in.DeleteProductUseCase;
import com.paulonepotti.ecommerce.product_microservice.application.port.in.GetAllProductsUseCase;
import com.paulonepotti.ecommerce.product_microservice.application.port.in.GetProductUseCase;
import com.paulonepotti.ecommerce.product_microservice.application.port.in.UpdateProductUseCase;
import com.paulonepotti.ecommerce.product_microservice.application.port.out.CategoryRepositoryPort;
import com.paulonepotti.ecommerce.product_microservice.application.port.out.ProductRepositoryPort;
import com.paulonepotti.ecommerce.product_microservice.application.service.ProductService;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductConfig {

    @Bean
    public ProductService productService(ProductRepositoryPort productPort, CategoryRepositoryPort categoryPort) {
        return new ProductService(productPort, categoryPort);
    }
    
    @Bean
    public CreateProductUseCase createProductUseCase(ProductRepositoryPort productPort, CategoryRepositoryPort categoryPort) {
        return new ProductService(productPort, categoryPort);
    }

    @Bean
    public GetProductUseCase getProductUseCase(ProductRepositoryPort productPort, CategoryRepositoryPort categoryPort) {
        return new ProductService(productPort, categoryPort);
    }

    @Bean
    public GetAllProductsUseCase getAllProductsUseCase(ProductRepositoryPort productPort, CategoryRepositoryPort categoryPort) {
        return new ProductService(productPort, categoryPort);
    }

    @Bean
    public UpdateProductUseCase updateProductUseCase(ProductRepositoryPort productPort, CategoryRepositoryPort categoryPort) {
        return new ProductService(productPort, categoryPort);
    }

    @Bean
    public DeleteProductUseCase deleteProductUseCase(ProductRepositoryPort productPort, CategoryRepositoryPort categoryPort) {
        return new ProductService(productPort, categoryPort);
    }


    
}
