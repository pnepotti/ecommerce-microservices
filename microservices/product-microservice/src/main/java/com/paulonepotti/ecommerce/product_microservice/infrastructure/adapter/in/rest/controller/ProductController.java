package com.paulonepotti.ecommerce.product_microservice.infrastructure.adapter.in.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.paulonepotti.ecommerce.product_microservice.application.port.in.CreateProductUseCase;
import com.paulonepotti.ecommerce.product_microservice.application.port.in.DeleteProductUseCase;
import com.paulonepotti.ecommerce.product_microservice.application.port.in.GetAllProductsUseCase;
import com.paulonepotti.ecommerce.product_microservice.application.port.in.GetProductUseCase;
import com.paulonepotti.ecommerce.product_microservice.application.port.in.UpdateProductUseCase;
import com.paulonepotti.ecommerce.product_microservice.domain.model.Product;
import com.paulonepotti.ecommerce.product_microservice.infrastructure.adapter.in.rest.dto.ProductRequest;
import com.paulonepotti.ecommerce.product_microservice.infrastructure.adapter.in.rest.dto.ProductResponse;
import com.paulonepotti.ecommerce.product_microservice.infrastructure.adapter.in.rest.mapper.RestProductMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final CreateProductUseCase createProductUseCase;
    private final GetProductUseCase getProductUseCase;
    private final GetAllProductsUseCase getAllProductsUseCase;
    private final UpdateProductUseCase updateProductUseCase;
    private final DeleteProductUseCase deleteProductUseCase;
    private final RestProductMapper productMapper;

    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@Valid @RequestBody ProductRequest request) {
        Product product = productMapper.toDomain(request);
        Product createdProduct = createProductUseCase.createProduct(product);
        ProductResponse response = productMapper.toResponse(createdProduct);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

    @GetMapping
    public List<ProductResponse> getProducts(
        @RequestParam(required = false) String name, 
        @RequestParam(required = false) Long categoryId) {
            
        List<Product> products = getAllProductsUseCase.getAllProducts(name, categoryId);
        return products.stream()
                .map(productMapper::toResponse)
                .toList();

    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProduct(@PathVariable Long id) {
        Product product = getProductUseCase.getProduct(id);
        ProductResponse response = productMapper.toResponse(product);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        deleteProductUseCase.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable Long id, @Valid @RequestBody ProductRequest request) {
        Product product = productMapper.toDomain(request);
        Product updatedProduct = updateProductUseCase.updateProduct(id, product);
        ProductResponse response = productMapper.toResponse(updatedProduct);
        return ResponseEntity.ok(response);
    }
    
    
}
