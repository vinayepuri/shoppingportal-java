package com.ecommerce.domain.service;


import com.ecommerce.domain.exception.BrandCategoriesNotFound;
import com.ecommerce.domain.model.category.BrandCategory;
import com.ecommerce.domain.util.message.ErrorMessages;
import com.ecommerce.domain.model.category.BrandCategoriesDomainResponse;
import com.ecommerce.domain.port.adapters.repositories.BrandCategoryImageRepositoryPort;
import com.ecommerce.domain.port.adapters.repositories.BrandCategoryRepositoryPort;
import com.ecommerce.domain.port.adapters.repositories.GenderCategoryRepositoryPort;
import com.ecommerce.domain.port.drivers.BrandCategoryDriverPort;

import java.util.List;
import java.util.stream.Collectors;


public class BrandCategoryService implements BrandCategoryDriverPort {

    protected BrandCategoryRepositoryPort brandCategoryRepository;
    protected BrandCategoryImageRepositoryPort brandCategoryImageRepository;
    protected GenderCategoryRepositoryPort genderCategoryRepository;

    public BrandCategoryService(BrandCategoryRepositoryPort brandCategoryRepository, BrandCategoryImageRepositoryPort brandCategoryImageRepository, GenderCategoryRepositoryPort genderCategoryRepository) {
        this.brandCategoryRepository = brandCategoryRepository;
        this.brandCategoryImageRepository = brandCategoryImageRepository;
        this.genderCategoryRepository = genderCategoryRepository;
    }

    @Override
    public BrandCategoriesDomainResponse getBrandCategoriesByGenderIdAndApparelCategoryId(int genderId, int apparelCategoryId) {

        String genderName = getGenderName(genderId);
        List<BrandCategory> brandCategories = getBrandCategories(genderId, apparelCategoryId);
        checkBrandCategoriesNotEmpty(brandCategories);
        List<BrandCategory> updatedBrandCategory = setImageUrlToBrandCategories(genderId, brandCategories);

        return new BrandCategoriesDomainResponse(genderName, updatedBrandCategory);
    }

    private String getGenderName(int genderId) {
        return genderCategoryRepository.findById(genderId).getName();
    }

    private List<BrandCategory> getBrandCategories(int genderId, int apparelCategoryId) {
        if(apparelCategoryId == 0)
            return brandCategoryRepository.findByGenderCategoryIdAndProductsIsNotNull(genderId);
        return brandCategoryRepository.findByGenderCategoryIdAndApparelCategoryIdAndProductsIsNotNull(genderId, apparelCategoryId);
    }

    private void checkBrandCategoriesNotEmpty(List<BrandCategory> brandCategories) {
        if (brandCategories.isEmpty()){
            throw new BrandCategoriesNotFound(ErrorMessages.BRAND_CATEGORIES_NOT_FOUND);
        }
    }

    private List<BrandCategory> setImageUrlToBrandCategories(int genderId, List<BrandCategory> brandCategories) {
        return brandCategories.stream()
                .map(brandCategory -> setImageUrlToBrandCategory(genderId, brandCategory))
                .collect(Collectors.toList());
    }

    private BrandCategory setImageUrlToBrandCategory(int genderId, BrandCategory brandCategory) {
        String imageUrl = getImageUrl(genderId, brandCategory);
        brandCategory.setImageUrl(imageUrl);
        return brandCategory;
    }

    private String getImageUrl(int genderId, BrandCategory brandCategory) {
        return brandCategoryImageRepository.findByBrandCategoryIdAndGenderCategoryId(brandCategory.getId(), genderId).getImage_url();
    }


}
