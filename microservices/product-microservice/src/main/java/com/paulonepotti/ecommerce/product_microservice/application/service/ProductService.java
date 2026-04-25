package com.paulonepotti.ecommerce.product_microservice.application.service;

import java.util.List;

import com.paulonepotti.ecommerce.product_microservice.application.port.in.CreateProductUseCase;
import com.paulonepotti.ecommerce.product_microservice.application.port.in.DeleteProductUseCase;
import com.paulonepotti.ecommerce.product_microservice.application.port.in.GetAllProductsUseCase;
import com.paulonepotti.ecommerce.product_microservice.application.port.in.GetProductUseCase;
import com.paulonepotti.ecommerce.product_microservice.application.port.in.UpdateProductUseCase;
import com.paulonepotti.ecommerce.product_microservice.application.port.out.ProductRepositoryPort;
import com.paulonepotti.ecommerce.product_microservice.domain.exception.ProductNotFoundException;
import com.paulonepotti.ecommerce.product_microservice.domain.model.Product;

public class ProductService implements 
    CreateProductUseCase, 
    GetProductUseCase,
    GetAllProductsUseCase,
    UpdateProductUseCase,
    DeleteProductUseCase {

    private final ProductRepositoryPort productRepositoryPort;    

    public ProductService(ProductRepositoryPort productRepositoryPort) {
        this.productRepositoryPort = productRepositoryPort;
    }

    @Override
    public Product createProduct(Product product) {
        return productRepositoryPort.save(product);
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        Product existingProduct = productRepositoryPort.findById(id)
            .orElseThrow(() -> new ProductNotFoundException(id));
        
        existingProduct.updateFrom(product);
        
        return productRepositoryPort.save(existingProduct); 
    }

    @Override
    public void deleteProduct(Long id) {
        if (!productRepositoryPort.findById(id).isPresent()) {
            throw new ProductNotFoundException(id);
        }
        productRepositoryPort.deleteById(id);
    }

    @Override
    public List<Product> getAllProducts(String name, Long categoryId) {
        if (name != null && !name.isEmpty()) {
            return productRepositoryPort.findByName(name);
        } else if (categoryId != null) {
            return productRepositoryPort.findByCategory(categoryId);
        } else {
            return productRepositoryPort.findAll();
        }
    }

    @Override
    public Product getProduct(Long id) {
        return productRepositoryPort.findById(id)
            .orElseThrow(() -> new ProductNotFoundException(id));
    }

}