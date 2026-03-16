package com.paulonepotti.ecommerce.product_microservice.infrastructure.adapter.in.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.paulonepotti.ecommerce.product_microservice.application.port.in.CreateProductUseCase;
import com.paulonepotti.ecommerce.product_microservice.domain.model.Product;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final CreateProductUseCase createProductUseCase;

    public ProductController(CreateProductUseCase createProductUseCase) {
        this.createProductUseCase = createProductUseCase;
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        // Llamamos al caso de uso de nuestra capa de Aplicación
        Product createdProduct = createProductUseCase.createProduct(product);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }
}
