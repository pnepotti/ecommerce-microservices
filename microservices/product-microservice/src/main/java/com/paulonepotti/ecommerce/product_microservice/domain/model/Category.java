package com.paulonepotti.ecommerce.product_microservice.domain.model;

import com.paulonepotti.ecommerce.product_microservice.domain.exception.InvalidCategoryNameException;

public class Category {

    private Long id;
    private String name;
    private String description;

    public Category() {
    }

    public Category(Long id, String name, String description) {
        validateName(name);
        this.id = id;
        this.name = name;
        this.description = description;
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

    public void updateFrom(Category category) {
        validateName(category.getName());
        this.name = category.getName();
        this.description = category.getDescription();
    }

    public void validateName(String name) {
        if (name == null || name.isBlank()) {
            throw new InvalidCategoryNameException("Category name is required");
        }
    }
}
