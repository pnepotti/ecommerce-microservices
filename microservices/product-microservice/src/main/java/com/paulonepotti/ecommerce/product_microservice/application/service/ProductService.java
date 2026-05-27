package com.paulonepotti.ecommerce.product_microservice.application.service;

import java.util.List;

import com.paulonepotti.ecommerce.product_microservice.application.port.in.CreateProductUseCase;
import com.paulonepotti.ecommerce.product_microservice.application.port.in.DeleteProductUseCase;
import com.paulonepotti.ecommerce.product_microservice.application.port.in.GetAllProductsUseCase;
import com.paulonepotti.ecommerce.product_microservice.application.port.in.GetProductUseCase;
import com.paulonepotti.ecommerce.product_microservice.application.port.in.UpdateProductUseCase;
import com.paulonepotti.ecommerce.product_microservice.application.port.out.CategoryRepositoryPort;
import com.paulonepotti.ecommerce.product_microservice.application.port.out.ProductRepositoryPort;
import com.paulonepotti.ecommerce.product_microservice.domain.exception.CategoryNotFoundException;
import com.paulonepotti.ecommerce.product_microservice.domain.exception.ProductNotFoundException;
import com.paulonepotti.ecommerce.product_microservice.domain.model.PageResponse;
import com.paulonepotti.ecommerce.product_microservice.domain.model.Product;

public class ProductService implements 
    CreateProductUseCase, 
    GetProductUseCase,
    GetAllProductsUseCase,
    UpdateProductUseCase,
    DeleteProductUseCase {

    private final ProductRepositoryPort productRepositoryPort;  
    private final CategoryRepositoryPort categoryRepositoryPort;     

    public ProductService(ProductRepositoryPort productRepositoryPort, CategoryRepositoryPort categoryRepositoryPort) {
        this.productRepositoryPort = productRepositoryPort;
        this.categoryRepositoryPort = categoryRepositoryPort;
    }

    @Override
    public Product createProduct(Product product) {
        if (product.getCategoryId() != null && !categoryRepositoryPort.existsById(product.getCategoryId())) {
            throw new CategoryNotFoundException(product.getCategoryId());
        }
        return productRepositoryPort.save(product);
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        if (product.getCategoryId() != null && !categoryRepositoryPort.existsById(product.getCategoryId())) {
            throw new CategoryNotFoundException(product.getCategoryId());
        }
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
    public PageResponse<Product> getAllProducts(String name, Long categoryId, int page, int size) {
        if (categoryId != null && !categoryRepositoryPort.existsById(categoryId)) {
            throw new CategoryNotFoundException(categoryId);
        }
        return productRepositoryPort.findAll(name, categoryId, page, size);
    }

    @Override
    public Product getProduct(Long id) {
        return productRepositoryPort.findById(id)
            .orElseThrow(() -> new ProductNotFoundException(id));
    }

}