package com.paulonepotti.ecommerce.order_microservice.domain.model;

import java.math.BigDecimal;

import com.paulonepotti.ecommerce.order_microservice.domain.valueobject.ProductSnapshot;

public class OrderItem {

    private Long id;
    private ProductSnapshot product; 
    private Integer quantity;
    private BigDecimal unitPrice; 

    public OrderItem(ProductSnapshot product, Integer quantity) {

        if (quantity <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor que cero");
        }

        if (product == null) {
            throw new IllegalArgumentException("El producto no puede ser nulo");
        } 

        this.product = product;
        this.quantity = quantity;
        this.unitPrice = product.getPrice(); 

    }

    public Long getId() {
        return id;
    }

    public ProductSnapshot getProduct() {
        return product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    
    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getSubtotal() {
        return this.unitPrice.multiply(BigDecimal.valueOf(this.quantity));
    }

    public void setProductSnapshot(ProductSnapshot snapshot) {
        this.product = snapshot;
    }
}