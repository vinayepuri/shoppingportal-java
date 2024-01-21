package com.ecommerce.domain.port.adapters.repositories;

import com.ecommerce.domain.model.category.BrandCategory;

import java.util.List;

public interface BrandCategoryRepositoryPort {

    List<BrandCategory> findByGenderCategoryIdAndProductsIsNotNull(int genderId);

    List<BrandCategory> findByGenderCategoryIdAndApparelCategoryIdAndProductsIsNotNull(int genderId, int apparelCategoryId);

    List<BrandCategory> findByIdIn(List<Integer> categoryIds);
}
