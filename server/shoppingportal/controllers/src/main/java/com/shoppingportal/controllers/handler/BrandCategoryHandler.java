package com.ecommerce.controllers.handler;

import com.ecommerce.adapters.repository.springdata.adapters.BrandCategoryImageRepositoryJpaAdapter;
import com.ecommerce.adapters.repository.springdata.adapters.BrandCategoryRepositoryJpaAdapter;
import com.ecommerce.adapters.repository.springdata.adapters.GenderCategoryRepositoryJpaAdapter;
import com.ecommerce.domain.service.BrandCategoryService;
import org.springframework.stereotype.Service;

@Service
public class BrandCategoryHandler extends BrandCategoryService {

    public BrandCategoryHandler(BrandCategoryRepositoryJpaAdapter brandCategoryRepository, BrandCategoryImageRepositoryJpaAdapter brandCategoryImageRepository, GenderCategoryRepositoryJpaAdapter genderCategoryRepository) {
        super(brandCategoryRepository, brandCategoryImageRepository, genderCategoryRepository);
    }
}
