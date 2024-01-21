package com.ecommerce.domain.port.adapters.repositories;


import com.ecommerce.domain.model.category.BrandCategoryImage;

public interface BrandCategoryImageRepositoryPort {

    BrandCategoryImage findByBrandCategoryIdAndGenderCategoryId(int brandId, int gender);
}
