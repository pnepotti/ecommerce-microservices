package com.paulonepotti.ecommerce.order_microservice.infrastructure.adapter.out.persistence;

import com.paulonepotti.ecommerce.order_microservice.application.port.out.OrderRepositoryPort;
import com.paulonepotti.ecommerce.order_microservice.domain.model.Order;
import com.paulonepotti.ecommerce.order_microservice.domain.model.PageResponse;
import com.paulonepotti.ecommerce.order_microservice.infrastructure.adapter.out.persistence.entity.OrderEntity;
import com.paulonepotti.ecommerce.order_microservice.infrastructure.adapter.out.persistence.mapper.OrderMapper;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class OrderPersistenceAdapter implements OrderRepositoryPort {

    private final SpringDataOrderRepository repository;
    private final OrderMapper mapper;

    @Override
    @Transactional
    public Order save(Order order) {
        OrderEntity entity = mapper.toEntity(order);
        
        // Ensure bidirectional relationship is set for JPA cascading
        if (entity.getItems() != null) {
            entity.getItems().forEach(item -> item.setOrder(entity));
        }
        
        OrderEntity savedEntity = repository.save(entity);
        return mapper.toDomain(savedEntity);
    }

    @Override
    @Transactional
    public Optional<Order> findById(Long id) {
        return repository.findById(id).map(mapper::toDomain);
    }

    @Override
    @Transactional
    public PageResponse<Order> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
        Page<OrderEntity> pageResult = repository.findAll(pageable);

        return new PageResponse<>(
            pageResult.getContent().stream().map(mapper::toDomain).toList(),
            pageResult.getNumber(),
            pageResult.getSize(),
            pageResult.getTotalElements(),
            pageResult.getTotalPages(),
            pageResult.isLast()
        );
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional
    public PageResponse<Order> findByCustomerId(Long customerId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
        Page<OrderEntity> pageResult = repository.findByCustomerId(customerId, pageable);

        return new PageResponse<>(
            pageResult.getContent().stream().map(mapper::toDomain).toList(),
            pageResult.getNumber(),
            pageResult.getSize(),
            pageResult.getTotalElements(),
            pageResult.getTotalPages(),
            pageResult.isLast()
        );    
    }


}
