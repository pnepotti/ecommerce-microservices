package com.paulonepotti.ecommerce.order_microservice.domain.model;

public class OrderItem {
    private Long id;
    private Long productId; // Referencia por ID, no por objeto
    private Integer quantity;
    private Double unitPrice; // Guardamos el precio del momento de la compra

    public OrderItem(Long id, Long productId, Integer quantity, Double unitPrice) {
        this.id = id;
        this.productId = productId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    // Getters, Setters y lógica de subtotal
    public Long getId() {
        return id;
    }
    public Long getProductId() {
        return productId;
    }
    public Integer getQuantity() {
        return quantity;
    }
    public Double getUnitPrice() {
        return unitPrice;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }
    
    public Double getSubtotal() {
        return this.unitPrice * this.quantity;
    }
}