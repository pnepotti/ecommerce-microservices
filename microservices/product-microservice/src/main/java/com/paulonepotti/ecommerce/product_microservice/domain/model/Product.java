package com.paulonepotti.ecommerce.product_microservice.domain.model;

import java.math.BigDecimal;

import com.paulonepotti.ecommerce.product_microservice.domain.exception.InvalidProductNameException;
import com.paulonepotti.ecommerce.product_microservice.domain.exception.InvalidProductPriceException;

public class Product {

    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Long categoryId;

    public Product() {
    }

    public Product(Long id, String name, String description, BigDecimal price, Long categoryId) {
        validateName(name);
        validatePrice(price);
        this.id = id;
        this.name = name;
        this.description = description;        
        this.price = price;
        this.categoryId = categoryId;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        validateName(name);
        this.name = name;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }
    public void setPrice(BigDecimal price) {
        validatePrice(price);
        this.price = price;
    }
    public Long getCategoryId() {
        return categoryId;
    }
    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public void updateFrom(Product product) {
        validateName(product.getName());
        validatePrice(product.getPrice());
        this.name = product.getName();
        this.description = product.getDescription();
        this.price = product.getPrice();
        this.categoryId = product.getCategoryId();
    }

    private void validateName(String name) {
        if (name == null || name.isBlank()) {
            throw new InvalidProductNameException("Product name is required");
        }
    }

    private void validatePrice(BigDecimal price) {
        if (price == null || price.compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidProductPriceException("Price must be a positive number");
        }
    }

}
