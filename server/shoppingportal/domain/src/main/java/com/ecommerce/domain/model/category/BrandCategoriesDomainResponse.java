package com.ecommerce.domain.model.category;



import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BrandCategoriesDomainResponse {

    private String gender;

    private List<BrandCategory> brandCategories;


    public BrandCategoriesDomainResponse(String gender, List<BrandCategory> brandCategories) {
        this.gender = gender;
        this.brandCategories = brandCategories;
    }
}