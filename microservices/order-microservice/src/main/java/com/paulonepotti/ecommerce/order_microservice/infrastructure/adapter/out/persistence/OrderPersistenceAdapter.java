package com.paulonepotti.ecommerce.order_microservice.infrastructure.adapter.out.persistence;
import com.paulonepotti.ecommerce.order_microservice.application.port.out.OrderRepositoryPort;
import com.paulonepotti.ecommerce.order_microservice.domain.model.Order;
import com.paulonepotti.ecommerce.order_microservice.infrastructure.adapter.out.persistence.mapper.OrderMapper;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class OrderPersistenceAdapter implements OrderRepositoryPort {

    private final SpringDataOrderRepository repository;
    private final OrderMapper mapper;

    @Override
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
    public Optional<Order> findById(Long id) {
        return repository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<Order> findAll() {
        return repository.findAll().stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }


}
