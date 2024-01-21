package com.ecommerce.adapters.repository.springdata.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "gender_category")
public class GenderCategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @Column(name = "gender_category_type")
    private String name;

    @OneToMany(mappedBy = "genderCategoryEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ProductEntity> productEntitySet = new HashSet<>();

    @OneToMany(mappedBy = "genderCategoryEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<BrandCategoryImageEntity> brandCategoryImageEntitySet = new HashSet<>();

    @ManyToMany(mappedBy = "genderCategoryEntitySet")
    private Set<ApparelCategoryEntity> apparelCategoryEntitySet = new HashSet<>();

    @ManyToMany(mappedBy = "genderCategoryEntitySet")
    private Set<BrandCategoryEntity> brandCategoryEntitySet = new HashSet<>();


}
