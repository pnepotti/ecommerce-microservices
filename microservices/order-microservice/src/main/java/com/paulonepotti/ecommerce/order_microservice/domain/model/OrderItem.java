package com.paulonepotti.ecommerce.order_microservice.domain.model;

import java.math.BigDecimal;

import com.paulonepotti.ecommerce.order_microservice.domain.exception.DomainValidationException;
import com.paulonepotti.ecommerce.order_microservice.domain.valueobject.ProductSnapshot;

public class OrderItem {

    private Long id;
    private ProductSnapshot product; 
    private Integer quantity;
    private BigDecimal unitPrice; 

    public OrderItem(ProductSnapshot product, Integer quantity) {
        validate(product, quantity);
        this.product = product;
        this.quantity = quantity;
        this.unitPrice = product.getPrice(); 

    }

    private void validate(ProductSnapshot product, Integer quantity) {
        if (product == null) {
            throw new DomainValidationException("El producto no puede ser nulo");
        }
        if (quantity == null || quantity <= 0) {
            throw new DomainValidationException("La cantidad debe ser mayor que cero");
        }
    }

    public void updateProductSnapshot(ProductSnapshot snapshot) {
        if (snapshot == null) 
            throw new DomainValidationException("El snapshot no puede ser nulo");
        if (snapshot.getPrice() == null || snapshot.getPrice().compareTo(BigDecimal.ZERO) <= 0) 
            throw new DomainValidationException("El precio del producto debe ser mayor que cero");
        this.product = snapshot;
        this.unitPrice = snapshot.getPrice(); 
    }
   
    public BigDecimal getSubtotal() {
        if (unitPrice == null) return BigDecimal.ZERO;
        return this.unitPrice.multiply(BigDecimal.valueOf(this.quantity));
    }

    public Long getId() { return id; }
    public ProductSnapshot getProduct() { return product; }
    public Integer getQuantity() { return quantity; }   
    public BigDecimal getUnitPrice() {return unitPrice; }

}