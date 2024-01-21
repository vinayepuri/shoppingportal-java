package com.ecommerce.domain.port.adapters.repositories;

import com.ecommerce.domain.model.category.ApparelCategory;

import java.util.List;

public interface ApparelCategoryRepositoryPort {

    List<ApparelCategory> findByIdIn(List<Integer> categoryIds);

    List<ApparelCategory> findByGenderCategoryIdAndProductsIsNotNull(int genderId);
    List<ApparelCategory> findByBrandCategoryIdAndGenderCategoryIdAndProductsIsNotNull(int brandId, int genderId);

}