package com.ecommerce.domain.model.category;

import com.ecommerce.domain.model.product.Product;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class GenderCategory {

    private int id;

    private String name;

    private Set<Product> products = new HashSet<>();

    private Set<BrandCategoryImage> brandCategoryImages = new HashSet<>();

    private Set<ApparelCategory> apparelCategories = new HashSet<>();

    private Set<BrandCategory> brandCategories = new HashSet<>();



}
