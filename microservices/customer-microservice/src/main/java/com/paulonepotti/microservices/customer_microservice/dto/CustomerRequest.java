package com.paulonepotti.microservices.customer_microservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CustomerRequest(
    @NotBlank(message = "El nombre es obligatorio")
    String firstName,
    @NotBlank(message = "El apellido es obligatorio")
    String lastName,
    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El email debe ser válido")
    String email
) {

}
