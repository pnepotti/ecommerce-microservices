package com.paulonepotti.ecommerce.product_microservice.infrastructure.adapter.out.persistence;

import com.paulonepotti.ecommerce.product_microservice.application.port.out.ProductRepositoryPort;
import com.paulonepotti.ecommerce.product_microservice.domain.exception.InsufficientStockException;
import com.paulonepotti.ecommerce.product_microservice.domain.exception.ProductNotFoundException;
import com.paulonepotti.ecommerce.product_microservice.domain.model.PageResponse;
import com.paulonepotti.ecommerce.product_microservice.domain.model.Product;
import com.paulonepotti.ecommerce.product_microservice.infrastructure.adapter.out.persistence.entity.ProductEntity;
import com.paulonepotti.ecommerce.product_microservice.infrastructure.adapter.out.persistence.mapper.PersistenceProductMapper;
import com.paulonepotti.ecommerce.product_microservice.infrastructure.adapter.out.persistence.repository.SpringDataProductRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;
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
    public PageResponse<Product> findAll(String name, Long categoryId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        String namePattern = (name != null) ? "%" + name + "%" : null;

        Page<ProductEntity> entityPage = repository.findAllWithFilters(namePattern, categoryId, pageable);

        return new PageResponse<>(
            entityPage.getContent().stream().map(mapper::toDomain).collect(Collectors.toList()), 
            entityPage.getNumber(), 
            entityPage.getSize(), 
            entityPage.getTotalElements(), 
            entityPage.getTotalPages(), 
            entityPage.isLast());    
        }

    @Override
    public Product decreaseStock(Long id, Integer quantity) {
        int updatedRows = repository.decreaseStock(id, quantity);
        if (updatedRows == 0) {
            // Aquí lanzas tu excepción personalizada de negocio
            throw new InsufficientStockException("No hay stock suficiente o el producto no existe");
        }
        return findById(id).orElseThrow(() -> new ProductNotFoundException(id));
    }

    @Override
    public Product increaseStock(Long id, Integer quantity) {
        int updatedRows = repository.increaseStock(id, quantity);
        if (updatedRows == 0) {
            // Aquí podrías lanzar una excepción si el producto no existe
            throw new ProductNotFoundException(id);
        }
        return findById(id).orElseThrow(() -> new ProductNotFoundException(id));
    }


}   