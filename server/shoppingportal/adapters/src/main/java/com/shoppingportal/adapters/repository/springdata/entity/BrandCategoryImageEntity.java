package com.ecommerce.adapters.repository.springdata.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "brand_image")
public class BrandCategoryImageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @Column(name="image_url")
    private String image_url;

    @JoinColumn(name = "brand_category_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private BrandCategoryEntity brandCategoryEntity;

    @JoinColumn(name = "gender_category_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private GenderCategoryEntity genderCategoryEntity;

}
