package com.paulonepotti.ecommerce.product_microservice.infrastructure.adapter.out.persistence;

import com.paulonepotti.ecommerce.product_microservice.application.port.out.ProductRepositoryPort;
import com.paulonepotti.ecommerce.product_microservice.domain.model.Product;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ProductPersistenceAdapter implements ProductRepositoryPort {

    private final SpringDataProductRepository repository;

    public ProductPersistenceAdapter(SpringDataProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public Product save(Product product) {
        // 1. Convertimos de Dominio a Entidad JPA
        ProductEntity entity = new ProductEntity(
            product.getId(), product.getName(), product.getDescription(), 
            product.getPrice(), product.getStock()
        );

        // 2. Guardamos en la base de datos
        ProductEntity savedEntity = repository.save(entity);

        // 3. Convertimos de Entidad JPA de vuelta a Dominio
        return new Product(
            savedEntity.getId(), savedEntity.getName(), savedEntity.getDescription(), 
            savedEntity.getPrice(), savedEntity.getStock()
        );
    }

    @Override
    public Optional<Product> findById(Long id) {
        return repository.findById(id).map(entity -> new Product(
            entity.getId(), entity.getName(), entity.getDescription(),
            entity.getPrice(), entity.getStock()
        ));
    }
}