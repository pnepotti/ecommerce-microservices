package com.paulonepotti.ecommerce.order_microservice.domain.model;

import java.util.List;

public class Order {
    private Long id;
    private String userEmail;
    private List<OrderItem> items;
    private Double totalAmount;

    public Order(Long id, String userEmail, List<OrderItem> items) {
        this.id = id;
        this.userEmail = userEmail;
        this.items = items;
        this.totalAmount = calculateTotal();
    }

    public Order(Long id, String userEmail, List<OrderItem> items, Double totalAmount) {
        this.id = id;
        this.userEmail = userEmail;
        this.items = items;
        this.totalAmount = totalAmount;
    }

    private Double calculateTotal() {
        return items.stream()
                .mapToDouble(OrderItem::getSubtotal)
                .sum();
    }

    public Long getId() {
        return id;
    }
    public String getUserEmail() {
        return userEmail;
    }
    public List<OrderItem> getItems() {
        return items;
    }
    public Double getTotalAmount() {
        return totalAmount;
    }
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
    public void setItems(List<OrderItem> items) {
        this.items = items;
    }
    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

}