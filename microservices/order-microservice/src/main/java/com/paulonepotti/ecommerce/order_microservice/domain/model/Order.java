package com.paulonepotti.ecommerce.order_microservice.domain.model;

import java.math.BigDecimal;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

import com.paulonepotti.ecommerce.order_microservice.domain.exception.DomainValidationException;
import com.paulonepotti.ecommerce.order_microservice.domain.exception.InvalidOrderStateException;

public class Order {

    private Long id;
    private Long customerId;
    private OrderStatus status;
    private List<OrderItem> items;
    private BigDecimal totalAmount;

    public Order(Long customerId, List<OrderItem> items) {
        if (customerId == null) throw new DomainValidationException("CustomerId requerido");
        if (items == null || items.isEmpty()) throw new DomainValidationException("Items requeridos");

        this.customerId  = customerId;
        this.items = new ArrayList<>(items);
        this.status = OrderStatus.PENDING; // siempre arranca en PENDING

        validateItems(this.items); // validación de negocio: no permitir items con cantidad <= 0
        this.totalAmount = calculateTotalAmount();
    }

    private void validateItems(List<OrderItem> items) {
        items.forEach(item -> {
            if (item == null) {
                throw new DomainValidationException("Los items no pueden ser nulos");
            }
            if (item.getQuantity() <= 0) {
                throw new DomainValidationException("La cantidad de cada item debe ser mayor que cero");
            }
            if (item.getProduct() == null) {
                throw new DomainValidationException("Cada item debe tener un producto asociado");
            }
        });
    }

    private Order() {}

    public static Order reconstitute(Long id, Long customerId, List<OrderItem> items,
                                     OrderStatus status, BigDecimal totalAmount) {
        Order order = new Order();
        order.id = id;
        order.customerId = customerId;
        order.items = items;
        order.status = status;        
        order.totalAmount = totalAmount; 
        return order;
    }

    public void confirm() {
        transitionTo(OrderStatus.CONFIRMED);
    }

    public void markAsPaid() {
        transitionTo(OrderStatus.PAID);
    }

    public void startPreparing() {
        transitionTo(OrderStatus.PREPARING);
    }

    public void ship() {
        transitionTo(OrderStatus.SHIPPED);
    }

    public void deliver() {
        transitionTo(OrderStatus.DELIVERED);
    }

    public void cancel(String reason) {
        if (reason == null || reason.isBlank()) throw new DomainValidationException("Razón de cancelación requerida");
        transitionTo(OrderStatus.CANCELLED);
    }

    public void refund() {
        transitionTo(OrderStatus.REFUNDED);
    }

    // ——— lógica de transición centralizada ———

    private void transitionTo(OrderStatus next) {
        if (!this.status.canTransitionTo(next)) {
            throw new InvalidOrderStateException(
                "No se puede pasar de " + this.status + " a " + next
            );
        }
        this.status = next;
    }

    // getters
    public Long getId() { return id; }
    public Long getCustomerId() { return customerId; }
    public OrderStatus getStatus() { return status; }
    public List<OrderItem> getItems() { return Collections.unmodifiableList(items); }
    public BigDecimal getTotalAmount() { return totalAmount; }

    private BigDecimal calculateTotalAmount() {
        return items.stream()
            .map(OrderItem::getSubtotal)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
    
    public void recalculateTotal() {
        this.totalAmount = calculateTotalAmount();
    }

}