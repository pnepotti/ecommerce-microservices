package com.paulonepotti.ecommerce.product_microservice.application.service;

import com.paulonepotti.ecommerce.product_microservice.application.port.in.CreateProductUseCase;
import com.paulonepotti.ecommerce.product_microservice.application.port.out.ProductRepositoryPort;
import com.paulonepotti.ecommerce.product_microservice.domain.model.Product;

public class ProductService implements CreateProductUseCase {

    private final ProductRepositoryPort productRepositoryPort;

    public ProductService(ProductRepositoryPort productRepositoryPort) {
        this.productRepositoryPort = productRepositoryPort;
    }

    @Override
    public Product createProduct(Product product) {
        // Aquí irían validaciones de negocio antes de guardar
        if (product.getPrice() <= 0) {
            throw new IllegalArgumentException("El precio debe ser mayor a 0");
        }
        
        // Usamos el puerto de salida para guardar, sin saber qué DB hay detrás
        return productRepositoryPort.save(product);
    }
}