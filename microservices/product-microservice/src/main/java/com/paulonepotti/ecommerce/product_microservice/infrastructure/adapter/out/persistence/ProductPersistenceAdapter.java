package com.paulonepotti.ecommerce.product_microservice.infrastructure.adapter.out.persistence;

import com.paulonepotti.ecommerce.product_microservice.application.port.out.ProductRepositoryPort;
import com.paulonepotti.ecommerce.product_microservice.domain.model.Product;
import com.paulonepotti.ecommerce.product_microservice.infrastructure.adapter.out.persistence.mapper.ProductMapper;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProductPersistenceAdapter implements ProductRepositoryPort {

    private final SpringDataProductRepository repository;
    private final ProductMapper mapper; 

    @Override
    public Product save(Product product) {
        ProductEntity entity = mapper.toEntity(product);
        ProductEntity savedEntity = repository.save(entity);
        return mapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Product> findById(Long id) {
        return repository.findById(id).map(mapper::toDomain);
    }   

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Product update(Product product) {
        ProductEntity entity = repository.findById(product.getId())
        .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        mapper.updateEntityFromDomain(product, entity);

        return mapper.toDomain(repository.save(entity));
}
}   