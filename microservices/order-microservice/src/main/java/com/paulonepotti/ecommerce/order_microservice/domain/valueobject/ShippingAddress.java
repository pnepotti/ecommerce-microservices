package com.paulonepotti.ecommerce.order_microservice.domain.valueobject;

import java.util.Objects;

public class ShippingAddress {

    private final String street;
    private final Integer number;
    private final String city;
    private final String province;
    private final String postalCode;

    public ShippingAddress(String street, Integer number, String city, String province, String postalCode, String country) {
        
        if (street == null || street.isBlank()) throw new IllegalArgumentException("La calle es requerida");
        if (number == null || number <= 0) throw new IllegalArgumentException("El número es requerido y debe ser positivo");
        if (city == null || city.isBlank()) throw new IllegalArgumentException("La ciudad es requerida");
        if (province == null || province.isBlank()) throw new IllegalArgumentException("La provincia es requerida");
        if (postalCode == null || postalCode.isBlank()) throw new IllegalArgumentException("El código postal es requerido");

        this.street = street;
        this.number = number;
        this.city = city;
        this.province = province;
        this.postalCode = postalCode;
    }

    public String getStreet() {
        return street;
    }

    public Integer getNumber() {
        return number;
    }

    public String getCity() {
        return city;
    }

    public String getProvince() {
        return province;
    }

    public String getPostalCode() {
        return postalCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ShippingAddress that)) return false;
        return street.equals(that.street)
            && number.equals(that.number)
            && city.equals(that.city)
            && postalCode.equals(that.postalCode)
            && province.equals(that.province);
    }

    @Override
    public int hashCode() {
        return Objects.hash(street, number, city, province, postalCode);
    }

    @Override
    public String toString() {
        return street + ", " + number + ", " + city + " (" + province + "), " + postalCode;
    }
}
