package com.restapiusingspring.restdemo.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity(name = "address")
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name="address_id")
    private String addressId;

    private String city;

    @Column(name="postal_code")
    private String postalCode;

    private String country;

    @NotNull
    @Pattern(regexp = "^permanent$|^correspondence$", message = "Allowed input for Address Type: permanent or correspondence")
    private String addressType;


    public Address(int addressid, String city, String postalCode, String country, String addressId, String addressType) {
        super();
        this.city = city;
        this.postalCode = postalCode;
        this.country = country;
        this.addressId = addressId;
        this.addressType = addressType;
    }

    @Override
    public String toString() {
        return "Address{" +
                ", city='" + city + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", country='" + country + '\'' +
                ", addressId='" + addressId + '\'' +
                ", addressType='" + addressType + '\'' +
                '}';
    }

    public Address() {
        super();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public String getAddressType() {
        return addressType;
    }

    public void setAddressType(String addressType) {
        this.addressType = addressType;
    }



}
