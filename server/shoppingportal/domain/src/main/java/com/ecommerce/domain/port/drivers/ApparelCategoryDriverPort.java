package com.ecommerce.domain.port.drivers;

import com.ecommerce.domain.model.category.ApparelCategoriesDomainResponse;

public interface ApparelCategoryDriverPort {

    ApparelCategoriesDomainResponse getApparelCategoriesByGenderIdAndBranCategoryId(int genderId, int brandId);
}
