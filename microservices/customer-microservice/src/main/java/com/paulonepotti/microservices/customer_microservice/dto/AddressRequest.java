package com.paulonepotti.microservices.customer_microservice.dto;

import jakarta.validation.constraints.NotBlank;

public record AddressRequest(
    @NotBlank(message = "La calle es obligatoria")
    String street,
    @NotBlank(message = "El número es obligatorio")
    String number,
    @NotBlank(message = "La ciudad es obligatoria")
    String city,
    String province,
    String postalCode
) {

}
