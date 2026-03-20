package com.paulonepotti.ecommerce.product_microservice.application.service;

import org.springframework.transaction.annotation.Transactional;

import com.paulonepotti.ecommerce.product_microservice.application.port.in.CreateProductUseCase;
import com.paulonepotti.ecommerce.product_microservice.application.port.out.ProductRepositoryPort;
import com.paulonepotti.ecommerce.product_microservice.domain.model.Product;

public class ProductService implements CreateProductUseCase {

    private final ProductRepositoryPort productRepositoryPort;

    public ProductService(ProductRepositoryPort productRepositoryPort) {
        this.productRepositoryPort = productRepositoryPort;
    }

    @Override
    @Transactional
    public Product createProduct(Product product) {
        if (product.getPrice() <= 0) {
            throw new IllegalArgumentException("El precio debe ser mayor a 0");
        }
        
        return productRepositoryPort.save(product);
    }

    @Override
    @Transactional
    public Product updateProduct(Product product) {
        if (product.getPrice() <= 0) {
            throw new IllegalArgumentException("El precio debe ser mayor a 0");
        }
        
        return productRepositoryPort.update(product);
    }

    @Override
    @Transactional
    public void deleteProduct(Long id) {
        productRepositoryPort.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Product getProductById(Long id) {
        return productRepositoryPort.findById(id).orElseThrow(() -> new RuntimeException("Producto no encontrado"));
    }

}