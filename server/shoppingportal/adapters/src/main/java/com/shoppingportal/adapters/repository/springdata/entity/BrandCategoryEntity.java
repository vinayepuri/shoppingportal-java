package com.ecommerce.adapters.repository.springdata.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "brand_category")
public class BrandCategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @Column(name = "brand_category_type")
    private String name;

    @Column(columnDefinition = "TEXT", length = 2048)
    private String description;

    @OneToMany(mappedBy = "brandCategoryEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ProductEntity> productEntitySet = new HashSet<>();

    @OneToMany(mappedBy = "brandCategoryEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<BrandCategoryImageEntity> brandCategoryImageEntitySet = new HashSet<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "brand_category_gender", joinColumns = @JoinColumn(name = "brand_category_id"), inverseJoinColumns = @JoinColumn(name = "gender_category_id"))
    private Set<GenderCategoryEntity> genderCategoryEntitySet = new HashSet<>();

    @ManyToMany(mappedBy = "brandCategorySet")
    private Set<ApparelCategoryEntity> apparelCategoryEntitySet = new HashSet<>();

    public void add(ProductEntity productEntity){
        if(productEntity != null) {
            if(productEntitySet == null) {
                productEntitySet = new HashSet<>();
            }

            productEntitySet.add(productEntity);
            productEntity.setBrandCategoryEntity(this);
        }
    }
    public void add(BrandCategoryImageEntity brandCategoryImageEntity){
        if(brandCategoryImageEntity != null) {
            if(brandCategoryImageEntitySet == null) {
                brandCategoryImageEntitySet = new HashSet<>();
            }

            brandCategoryImageEntitySet.add(brandCategoryImageEntity);
            brandCategoryImageEntity.setBrandCategoryEntity(this);
        }
    }


}
