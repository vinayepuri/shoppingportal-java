package com.ecommerce.controllers.handler;

import com.ecommerce.adapters.repository.springdata.adapters.ApparelCategoryRepositoryJpaAdapter;
import com.ecommerce.adapters.repository.springdata.adapters.GenderCategoryRepositoryJpaAdapter;
import com.ecommerce.domain.service.ApparelCategoryService;
import org.springframework.stereotype.Service;

@Service
public class ApparelCategoryHandler extends ApparelCategoryService {

    public ApparelCategoryHandler(ApparelCategoryRepositoryJpaAdapter apparelCategoryRepository, GenderCategoryRepositoryJpaAdapter genderCategoryRepository) {
        super(apparelCategoryRepository, genderCategoryRepository);
    }
}
