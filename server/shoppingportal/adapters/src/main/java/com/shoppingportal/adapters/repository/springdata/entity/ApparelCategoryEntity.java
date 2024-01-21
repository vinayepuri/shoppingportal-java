package com.ecommerce.adapters.repository.springdata.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name="apparel_category")
public class ApparelCategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @Column(name = "apparel_category_type")
    private String name;

    @OneToMany(mappedBy = "apparelCategoryEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ProductEntity> productEntitySet = new HashSet<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "apparel_category_gender", joinColumns = @JoinColumn(name = "apparel_category_id"), inverseJoinColumns = @JoinColumn(name = "gender_category_id"))
    private Set<GenderCategoryEntity> genderCategoryEntitySet = new HashSet<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "apparel_category_brand", joinColumns = @JoinColumn(name = "apparel_category_id"), inverseJoinColumns = @JoinColumn(name = "brand_category_id"))
    private Set<BrandCategoryEntity> brandCategorySet = new HashSet<>();

    public void addProduct(ProductEntity productEntity) {
        if( productEntity != null) {

            if(productEntitySet == null) {
                productEntitySet = new HashSet<>();
            }

            productEntitySet.add(productEntity);
            productEntity.setApparelCategoryEntity(this);
        }
    }


}

