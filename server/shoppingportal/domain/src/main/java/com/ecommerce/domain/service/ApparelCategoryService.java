package com.ecommerce.domain.service;

import com.ecommerce.domain.model.category.ApparelCategory;
import com.ecommerce.domain.model.category.GenderCategory;
import com.ecommerce.domain.util.message.ErrorMessages;
import com.ecommerce.domain.exception.ApparelCategoriesNotFound;
import com.ecommerce.domain.model.category.ApparelCategoriesDomainResponse;
import com.ecommerce.domain.port.adapters.repositories.ApparelCategoryRepositoryPort;
import com.ecommerce.domain.port.adapters.repositories.GenderCategoryRepositoryPort;
import com.ecommerce.domain.port.drivers.ApparelCategoryDriverPort;

import java.util.List;

public class ApparelCategoryService implements ApparelCategoryDriverPort {

    protected ApparelCategoryRepositoryPort apparelCategoryRepository;
    protected GenderCategoryRepositoryPort genderCategoryRepository;

    public ApparelCategoryService(ApparelCategoryRepositoryPort apparelCategoryRepository, GenderCategoryRepositoryPort genderCategoryRepository) {
        this.apparelCategoryRepository = apparelCategoryRepository;
        this.genderCategoryRepository = genderCategoryRepository;
    }


    @Override
    public ApparelCategoriesDomainResponse getApparelCategoriesByGenderIdAndBranCategoryId(int genderId, int brandId) {

        GenderCategory gender = checkGender(genderId);

        ApparelCategoriesDomainResponse apparelCategoriesDomainResponse = new ApparelCategoriesDomainResponse();
        apparelCategoriesDomainResponse.setGender(gender.getName());
        apparelCategoriesDomainResponse.setApparelCategories(getApparelCategories(brandId, genderId));

        return apparelCategoriesDomainResponse;

    }

    private GenderCategory checkGender(int genderId) {
        return genderCategoryRepository.findById(genderId);
    }

    private List<ApparelCategory> getApparelCategories(int brand, int gender) {
        List<ApparelCategory> apparelCategories;

        if (brand == 0) {
            apparelCategories = getAllApparelCategoriesGivenAGender(gender);
        } else {
            apparelCategories = getAllApparelCategoriesGivenAGenderAndABrand(brand, gender);
        }

        checkApparelCategoriesIsNotEmpty(apparelCategories);

        return apparelCategories;
    }

    private List<ApparelCategory> getAllApparelCategoriesGivenAGender(int gender) {
        List<ApparelCategory> apparelCategories;
        apparelCategories = apparelCategoryRepository.findByGenderCategoryIdAndProductsIsNotNull(gender);
        return apparelCategories;
    }

    private List<ApparelCategory> getAllApparelCategoriesGivenAGenderAndABrand(int brand, int gender) {
        List<ApparelCategory> apparelCategories;
        apparelCategories = apparelCategoryRepository.findByBrandCategoryIdAndGenderCategoryIdAndProductsIsNotNull(brand, gender);
        return apparelCategories;
    }

    private void checkApparelCategoriesIsNotEmpty(List<ApparelCategory> apparelCategories) {
        if (apparelCategories.isEmpty()) {
            throw new ApparelCategoriesNotFound(ErrorMessages.APPAREL_CATEGORIES_NOT_FOUND);
        }
    }

}


