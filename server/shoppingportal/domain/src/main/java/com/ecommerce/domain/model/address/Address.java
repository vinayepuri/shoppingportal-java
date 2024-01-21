package com.ecommerce.domain.model.address;

import com.ecommerce.domain.model.user.Civility;
import com.ecommerce.domain.model.user.User;
import com.ecommerce.domain.model.order.Order;
import com.ecommerce.domain.model.country.Country;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    private Long id;

    private Civility civility;

    private String firstName;

    private String lastName;

    private String street;

    private String addressComplement;

    private String city;

    private int postCode;

    private Country country;

    private String phoneNumber;

    private User user;

    private Set<Order> orders = new HashSet<>();

    public Address(Long id, Civility civility, String firstName, String lastName, String street, Country country) {
        this.id = id;
        this.civility = civility;
        this.firstName = firstName;
        this.lastName = lastName;
        this.street = street;
        this.country = country;
    }
}