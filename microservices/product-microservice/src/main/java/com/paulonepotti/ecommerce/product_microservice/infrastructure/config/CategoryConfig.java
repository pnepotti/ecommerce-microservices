package com.paulonepotti.ecommerce.product_microservice.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.paulonepotti.ecommerce.product_microservice.application.port.in.CreateCategoryUseCase;
import com.paulonepotti.ecommerce.product_microservice.application.port.in.DeleteCategoryUseCase;
import com.paulonepotti.ecommerce.product_microservice.application.port.in.GetAllCategoriesUseCase;
import com.paulonepotti.ecommerce.product_microservice.application.port.in.GetCategoryUseCase;
import com.paulonepotti.ecommerce.product_microservice.application.port.in.UpdateCategoryUseCase;
import com.paulonepotti.ecommerce.product_microservice.application.port.out.CategoryRepositoryPort;
import com.paulonepotti.ecommerce.product_microservice.application.service.CategoryService;

@Configuration
public class CategoryConfig {

    @Bean
    public CategoryService categoryService(CategoryRepositoryPort categoryPort) {
        return new CategoryService(categoryPort);
    }
    
    @Bean
    public CreateCategoryUseCase createCategoryUseCase(CategoryService categoryService) {
        return categoryService;
    }

    @Bean
    public GetCategoryUseCase getCategoryUseCase(CategoryService categoryService) {
        return categoryService;
    }

    @Bean
    public GetAllCategoriesUseCase getAllCategoriesUseCase(CategoryService categoryService) {
        return categoryService;
    }

    @Bean
    public UpdateCategoryUseCase updateCategoryUseCase(CategoryService categoryService) {
        return categoryService;
    }   

    @Bean 
    public DeleteCategoryUseCase deleteCategoryUseCase(CategoryService categoryService) {
        return categoryService;
    }

}
