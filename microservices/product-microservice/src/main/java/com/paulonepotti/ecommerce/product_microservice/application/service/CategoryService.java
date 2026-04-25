package com.paulonepotti.ecommerce.product_microservice.application.service;

import java.util.List;

import com.paulonepotti.ecommerce.product_microservice.application.port.in.CreateCategoryUseCase;
import com.paulonepotti.ecommerce.product_microservice.application.port.in.DeleteCategoryUseCase;
import com.paulonepotti.ecommerce.product_microservice.application.port.in.GetAllCategoriesUseCase;
import com.paulonepotti.ecommerce.product_microservice.application.port.in.GetCategoryUseCase;
import com.paulonepotti.ecommerce.product_microservice.application.port.in.UpdateCategoryUseCase;
import com.paulonepotti.ecommerce.product_microservice.application.port.out.CategoryRepositoryPort;
import com.paulonepotti.ecommerce.product_microservice.domain.exception.CategoryNotFoundException;
import com.paulonepotti.ecommerce.product_microservice.domain.model.Category;


public class CategoryService implements 
    CreateCategoryUseCase, 
    GetCategoryUseCase, 
    GetAllCategoriesUseCase, 
    UpdateCategoryUseCase, 
    DeleteCategoryUseCase{

    private final CategoryRepositoryPort categoryRepositoryPort;

    public CategoryService(CategoryRepositoryPort categoryRepositoryPort) {
        this.categoryRepositoryPort = categoryRepositoryPort;
    }   

    @Override
    public Category createCategory(Category category) {
        return categoryRepositoryPort.save(category);    
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepositoryPort.findAll();
    }

    @Override
    public Category getCategoryById(Long id) {
        return categoryRepositoryPort.findById(id)
            .orElseThrow(() -> new CategoryNotFoundException(id));
    }

    @Override
    public Category updateCategory(Long id, Category category) {
        Category existingCategory = categoryRepositoryPort.findById(id)
            .orElseThrow(() -> new CategoryNotFoundException(id));
        
        existingCategory.updateFrom(category);
        
        return categoryRepositoryPort.save(existingCategory);
    }

    @Override
    public void deleteCategory(Long id) {
        if (!categoryRepositoryPort.findById(id).isPresent()) {
            throw new CategoryNotFoundException(id);
        }
        categoryRepositoryPort.deleteById(id);
    }
    



}
