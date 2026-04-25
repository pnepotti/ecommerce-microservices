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
    public CategoryService categoryService(CategoryRepositoryPort port) {
        return new CategoryService(port);
    }
    
    @Bean
    public CreateCategoryUseCase createCategoryUseCase(CategoryRepositoryPort port) {
        return new CategoryService(port);
    }

    @Bean
    public GetCategoryUseCase getCategoryUseCase(CategoryRepositoryPort port) {
        return new CategoryService(port);
    }

    @Bean
    public GetAllCategoriesUseCase getAllCategoriesUseCase(CategoryRepositoryPort port) {
        return new CategoryService(port);
    }

    @Bean
    public UpdateCategoryUseCase updateCategoryUseCase(CategoryRepositoryPort port) {
        return new CategoryService(port);
    }   

    @Bean 
    public DeleteCategoryUseCase deleteCategoryUseCase(CategoryRepositoryPort port) {
        return new CategoryService(port);
    }

}
