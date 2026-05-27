package com.paulonepotti.ecommerce.product_microservice.domain.model;

import java.util.List;

public record PageResponse<T>(
    List<T> content,
    int pageNumber,
    int pageSize,
    long totalElements,
    int totalPages,
    boolean last 
) {

}
