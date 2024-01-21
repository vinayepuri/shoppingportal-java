package com.ecommerce.adapters.repository.springdata.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name="address")
public class AddressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "civility_id")
    private CivilityEntity civilityEntity;

    @Column(name="first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    private String street;

    @Column(name = "address_complement")
    private String addressComplement;

    private String city;

    @Column(name = "post_code")
    private int postCode;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private CountryEntity countryEntity;

    @Column(name = "phone_number")
    private String phoneNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    @OneToMany(mappedBy = "addressEntity", cascade = CascadeType.ALL)
    private Set<OrderEntity> orderEntitySet = new HashSet<>();

}
