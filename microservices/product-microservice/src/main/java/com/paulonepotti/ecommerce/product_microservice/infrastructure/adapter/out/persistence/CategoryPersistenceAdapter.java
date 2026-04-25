package com.paulonepotti.ecommerce.product_microservice.infrastructure.adapter.out.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.paulonepotti.ecommerce.product_microservice.application.port.out.CategoryRepositoryPort;
import com.paulonepotti.ecommerce.product_microservice.domain.exception.CategoryNotFoundException;
import com.paulonepotti.ecommerce.product_microservice.domain.model.Category;
import com.paulonepotti.ecommerce.product_microservice.infrastructure.adapter.out.persistence.entity.CategoryEntity;
import com.paulonepotti.ecommerce.product_microservice.infrastructure.adapter.out.persistence.mapper.PersistenceCategoryMapper;
import com.paulonepotti.ecommerce.product_microservice.infrastructure.adapter.out.persistence.repository.SpringDataCategoryRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CategoryPersistenceAdapter implements CategoryRepositoryPort {

    private final SpringDataCategoryRepository repository;
    private final PersistenceCategoryMapper mapper;

    @Override
    @Transactional
    public Category save(Category category) {
        CategoryEntity entity = mapper.toEntity(category);
        CategoryEntity savedEntity = repository.save(entity);
        return mapper.toDomain(savedEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Category> findById(Long id) {
        return repository.findById(id).map(mapper::toDomain);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Category> findAll() {
        return repository.findAll().stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional
    public Category update(Category category) {
        if (category.getId() == null || !repository.existsById(category.getId())) {
            throw new CategoryNotFoundException(category.getId());
        }
        return save(category);
    }
}

