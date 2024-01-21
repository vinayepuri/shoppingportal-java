package com.ecommerce.adapters.repository.springdata.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "civility")
public class CivilityEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    private String name;

    @OneToMany(mappedBy = "civilityEntity", cascade = CascadeType.ALL)
    private Set<UserEntity> userEntitySet = new HashSet<>();

    public CivilityEntity(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public CivilityEntity() {
    }




}
