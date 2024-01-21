package com.ecommerce.adapters.repository.springdata.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table (name ="product")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "unit_price")
    private BigDecimal unitPrice;

    @Column(name="image_url")
    private String imageUrl;

    @Column(name="active")
    private Boolean active;

    @Column(name="new")
    private Boolean newProduct;

    @Column(name = "units_in_stock")
    private int unitsInStocks;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gender_category_id")
    private GenderCategoryEntity genderCategoryEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_category_id")
    private BrandCategoryEntity brandCategoryEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "apparel_category_id")
    private ApparelCategoryEntity apparelCategoryEntity;



}
