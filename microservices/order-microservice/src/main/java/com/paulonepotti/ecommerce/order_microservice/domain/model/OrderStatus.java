package com.paulonepotti.ecommerce.order_microservice.domain.model;

public enum OrderStatus {

    PENDING,      // recién creado, esperando pago
    CONFIRMED,    // pago iniciado
    PAID,         // pago confirmado por payment service
    PREPARING,    // el vendedor lo está preparando
    SHIPPED,      // en camino
    DELIVERED,    // entregado
    CANCELLED,    // cancelado
    REFUNDED;     // reembolsado

    public boolean canTransitionTo(OrderStatus next) {
        return switch (this) {
            case PENDING -> next == CONFIRMED || next == CANCELLED;
            case CONFIRMED -> next == PAID || next == CANCELLED;
            case PAID -> next == PREPARING || next == CANCELLED || next == REFUNDED;
            case PREPARING -> next == SHIPPED || next == CANCELLED;
            case SHIPPED -> next == DELIVERED;
            case CANCELLED, DELIVERED, REFUNDED -> false; // estados terminales
            default -> false;
        };
    }
}
