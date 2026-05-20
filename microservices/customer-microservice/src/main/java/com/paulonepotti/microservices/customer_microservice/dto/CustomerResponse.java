package com.paulonepotti.microservices.customer_microservice.dto;

import java.time.LocalDateTime;

public record CustomerResponse(
    Long id,
    String firstName,
    String lastName,
    String email,
    boolean active,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {

}
