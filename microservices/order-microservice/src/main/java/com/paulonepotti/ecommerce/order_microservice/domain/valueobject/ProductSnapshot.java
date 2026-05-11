package com.paulonepotti.ecommerce.order_microservice.domain.valueobject;

import java.math.BigDecimal;
import java.util.Objects;

import com.paulonepotti.ecommerce.order_microservice.domain.exception.DomainValidationException;

public class ProductSnapshot {

    private final Long productId;
    private final String name;
    private final BigDecimal price;
    private final String imageUrl;

    public ProductSnapshot(Long productId, String name, BigDecimal price, String imageUrl) {

        if (productId == null) {
            throw new DomainValidationException("El ID del producto no puede ser nulo");
        }
        if (name == null || name.isBlank()) {
            throw new DomainValidationException("El nombre del producto no puede ser nulo o vacío");
        }
        if (price == null || price.compareTo(BigDecimal.ZERO) < 0) {
            throw new DomainValidationException("El precio del producto no puede ser nulo o negativo");
        }

        this.productId = productId;
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    public Long getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductSnapshot that)) return false;
        return productId.equals(that.productId)
            && price.compareTo(that.price) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, price);
    }
}
