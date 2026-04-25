package com.paulonepotti.ecommerce.product_microservice.infrastructure.adapter.in.rest.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.paulonepotti.ecommerce.product_microservice.application.port.in.CreateCategoryUseCase;
import com.paulonepotti.ecommerce.product_microservice.application.port.in.GetCategoryUseCase;
import com.paulonepotti.ecommerce.product_microservice.application.port.in.GetAllCategoriesUseCase;
import com.paulonepotti.ecommerce.product_microservice.application.port.in.UpdateCategoryUseCase;
import com.paulonepotti.ecommerce.product_microservice.domain.model.Category;
import com.paulonepotti.ecommerce.product_microservice.application.port.in.DeleteCategoryUseCase;
import com.paulonepotti.ecommerce.product_microservice.infrastructure.adapter.in.rest.dto.CategoryRequest;
import com.paulonepotti.ecommerce.product_microservice.infrastructure.adapter.in.rest.dto.CategoryResponse;
import com.paulonepotti.ecommerce.product_microservice.infrastructure.adapter.in.rest.mapper.RestCategoryMapper;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CreateCategoryUseCase createCategoryUseCase;
    private final GetCategoryUseCase getCategoryUseCase;
    private final GetAllCategoriesUseCase getAllCategoriesUseCase;
    private final UpdateCategoryUseCase updateCategoryUseCase;
    private final DeleteCategoryUseCase deleteCategoryUseCase;
    private final RestCategoryMapper categoryMapper;

    @PostMapping
    public ResponseEntity<CategoryResponse> createCategory(@Valid @RequestBody CategoryRequest request) {
    
        Category category = categoryMapper.toDomain(request);
        Category createdCategory = createCategoryUseCase.createCategory(category);
        CategoryResponse entity = categoryMapper.toResponse(createdCategory);
        return ResponseEntity.ok(entity);
    }

    @GetMapping
    public List<CategoryResponse> getCategories() {
        List<Category> categories = getAllCategoriesUseCase.getAllCategories();
        return categories.stream()
                .map(categoryMapper::toResponse)
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> getCategory(@PathVariable Long id) {
        Category category = getCategoryUseCase.getCategoryById(id);
        CategoryResponse response = categoryMapper.toResponse(category);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponse> updateCategory(@PathVariable Long id, @Valid @RequestBody CategoryRequest request) {
        Category category = categoryMapper.toDomain(request);
        Category updatedCategory = updateCategoryUseCase.updateCategory(id, category);
        CategoryResponse response = categoryMapper.toResponse(updatedCategory);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        deleteCategoryUseCase.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }

    
}
