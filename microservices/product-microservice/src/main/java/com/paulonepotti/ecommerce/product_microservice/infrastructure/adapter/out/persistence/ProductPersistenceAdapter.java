package com.paulonepotti.ecommerce.product_microservice.infrastructure.adapter.out.persistence;

import com.paulonepotti.ecommerce.product_microservice.application.port.out.ProductRepositoryPort;
import com.paulonepotti.ecommerce.product_microservice.domain.exception.ProductNotFoundException;
import com.paulonepotti.ecommerce.product_microservice.domain.model.Product;
import com.paulonepotti.ecommerce.product_microservice.infrastructure.adapter.out.persistence.entity.ProductEntity;
import com.paulonepotti.ecommerce.product_microservice.infrastructure.adapter.out.persistence.mapper.PersistenceProductMapper;
import com.paulonepotti.ecommerce.product_microservice.infrastructure.adapter.out.persistence.repository.SpringDataProductRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProductPersistenceAdapter implements ProductRepositoryPort {

    private final SpringDataProductRepository repository;
    private final PersistenceProductMapper mapper; 

    @Override
    @Transactional
    public Product save(Product product) {
        ProductEntity entity = mapper.toEntity(product);
        ProductEntity savedEntity = repository.save(entity);
        return mapper.toDomain(savedEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Product> findById(Long id) {
        return repository.findById(id).map(mapper::toDomain);
    }   

    @Override
    @Transactional
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional
    public Product update(Product product) {
        ProductEntity entity = repository.findById(product.getId())
        .orElseThrow(() -> new ProductNotFoundException(product.getId()));

        mapper.updateEntityFromDomain(product, entity);

        return mapper.toDomain(repository.save(entity));
}

    @Override
    @Transactional(readOnly = true)
    public List<Product> findAll() {
        List<ProductEntity> entities = repository.findAll();
        return entities.stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> findByName(String name) {
        List<ProductEntity> entities = repository.findByName(name);
        return entities.stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> findByCategory(Long categoryId) {
        List<ProductEntity> entities = repository.findByCategoryId(categoryId);
        return entities.stream()
                .map(mapper::toDomain)
                .toList();
    }
}   