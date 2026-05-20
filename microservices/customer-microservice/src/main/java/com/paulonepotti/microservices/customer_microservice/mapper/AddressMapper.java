package com.paulonepotti.microservices.customer_microservice.mapper;

import org.springframework.stereotype.Component;

import com.paulonepotti.microservices.customer_microservice.dto.AddressRequest;
import com.paulonepotti.microservices.customer_microservice.dto.AddressResponse;
import com.paulonepotti.microservices.customer_microservice.model.Address;

@Component
public class AddressMapper {

    public AddressResponse toAddressResponse(Address address) {
        if (address == null) return null;
        return new AddressResponse(
                address.getId(),
                address.getStreet(),
                address.getNumber(),
                address.getCity(),
                address.getProvince(),
                address.getPostalCode()
        );
    }

    public Address toEntity(AddressRequest request) {
        if (request == null) return null;
        return Address.builder()
                .street(request.street())
                .number(request.number())
                .city(request.city())
                .province(request.province())
                .postalCode(request.postalCode())
                .build();
    }

    public void updateEntity(AddressRequest request, Address address) {
        if (request == null || address == null) return;
        address.setStreet(request.street());
        address.setNumber(request.number());
        address.setCity(request.city());
        address.setProvince(request.province());
        address.setPostalCode(request.postalCode());
    }
}

