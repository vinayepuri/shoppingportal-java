package com.ecommerce.domain.port.drivers;

import com.ecommerce.domain.model.category.BrandCategoriesDomainResponse;

public interface BrandCategoryDriverPort {

    BrandCategoriesDomainResponse getBrandCategoriesByGenderIdAndApparelCategoryId(int genderId, int apparelCategoryId);
}
