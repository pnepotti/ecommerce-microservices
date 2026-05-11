package com.paulonepotti.ecommerce.product_microservice.domain.model;

import java.math.BigDecimal;

import com.paulonepotti.ecommerce.product_microservice.domain.exception.InsufficientStockException;
import com.paulonepotti.ecommerce.product_microservice.domain.exception.InvalidProductNameException;
import com.paulonepotti.ecommerce.product_microservice.domain.exception.InvalidProductPriceException;
import com.paulonepotti.ecommerce.product_microservice.domain.exception.InvalidStockQuantityException;

public class Product {

    private Long id;
    private String name;
    private String description;
    private String imageUrl;
    private Integer stock;
    private BigDecimal price;
    private Long categoryId;

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

    private void validateStock(Integer stock) {
        if (stock == null || stock <= 0) {
            throw new InvalidStockQuantityException("El stock no puede ser nulo o negativo");
        }
    }

    public Product() {
    }

    public Product(Long id, String name, String description, String imageUrl, Integer stock, BigDecimal price, Long categoryId) {
        validateName(name);
        validatePrice(price);
        validateStock(stock);
        this.id = id;
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.stock = stock;
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
    
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        validateStock(stock);
        this.stock = stock;
    }

    public void decreaseStock(Integer quantity) {
        if (quantity == null || quantity <= 0) {
            throw new InvalidStockQuantityException("La cantidad a disminuir debe ser mayor que cero");
        }
        if (this.stock < quantity) {
            throw new InsufficientStockException("No hay suficiente stock para disminuir");
        }
        this.stock -= quantity;
    }

    public void increaseStock(Integer quantity) {
        if (quantity == null || quantity <= 0) {
            throw new InvalidStockQuantityException("La cantidad a aumentar debe ser mayor que cero");
        }
        this.stock += quantity;
    }

    public boolean hasEnoughStock(Integer quantity) {
        return this.stock >= quantity;
    }

    public void updateFrom(Product product) {
        validateName(product.getName());
        validatePrice(product.getPrice());
        validateStock(product.getStock());
        this.name = product.getName();
        this.description = product.getDescription();
        this.imageUrl = product.getImageUrl();
        this.stock = product.getStock();
        this.price = product.getPrice();
        this.categoryId = product.getCategoryId();
    }

}
