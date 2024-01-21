package com.ecommerce.domain.model.category;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BrandCategoryImage {

    private int id;

    private String image_url;

    private BrandCategory brandCategory;

    private GenderCategory genderCategory;


}
