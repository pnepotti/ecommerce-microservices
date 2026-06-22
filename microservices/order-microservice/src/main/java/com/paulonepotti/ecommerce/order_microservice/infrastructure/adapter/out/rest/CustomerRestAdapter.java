package com.paulonepotti.ecommerce.order_microservice.infrastructure.adapter.out.rest;

import org.springframework.stereotype.Component;

import com.paulonepotti.ecommerce.order_microservice.application.port.out.CustomerPort;
import com.paulonepotti.ecommerce.order_microservice.infrastructure.adapter.out.rest.dto.CustomerDTO;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CustomerRestAdapter implements CustomerPort {

    private final CustomerClient customerClient;

    @Override
    public boolean existsById(Long customerId) {
        try {
            CustomerDTO customer = customerClient.getCustomerById(customerId);
            return customer != null && customer.active();
        } catch (Exception e) {
            // Si hay un error al llamar al microservicio, se asume que el cliente no existe
            return false;
        }
    }

   

}
