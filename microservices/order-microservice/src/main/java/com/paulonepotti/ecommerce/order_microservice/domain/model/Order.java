package com.paulonepotti.ecommerce.order_microservice.domain.model;

import java.math.BigDecimal;
import java.util.List;
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
        if (customerId == null)           throw new DomainValidationException("CustomerId requerido");
        if (items == null || items.isEmpty()) throw new DomainValidationException("Items requeridos");

        this.customerId  = customerId;
        this.items       = items;
        this.status      = OrderStatus.PENDING; // siempre arranca en PENDING
        this.totalAmount = calculateTotal();
    }

    // Constructor privado para reconstitución desde DB
    private Order() {}

    public static Order reconstitute(Long id, Long customerId, List<OrderItem> items,
                                     OrderStatus status, BigDecimal totalAmount) {
        Order order      = new Order();
        order.id         = id;
        order.customerId = customerId;
        order.items      = items;
        order.status     = status;        // ← acá sí asignás el status directamente
        order.totalAmount = totalAmount;  //   porque estás reconstruyendo, no transitando
        return order;
    }

    // ——— métodos de dominio — la única forma de cambiar el status ———

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

    private BigDecimal calculateTotal() {
        return items.stream()
            .map(OrderItem::getSubtotal)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    // getters
    public Long          getId()          { return id; }
    public Long          getCustomerId()  { return customerId; }
    public OrderStatus   getStatus()      { return status; }
    public List<OrderItem> getItems()     { return Collections.unmodifiableList(items); }
    public BigDecimal    getTotalAmount() { return totalAmount; }

    public void setId(Long id) { this.id = id; } 

    public void recalculateTotal() {
        this.totalAmount = items.stream()
            .map(OrderItem::getSubtotal)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}