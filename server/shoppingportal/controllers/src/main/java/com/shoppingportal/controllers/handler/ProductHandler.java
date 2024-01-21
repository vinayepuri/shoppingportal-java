package com.ecommerce.controllers.handler;

import com.ecommerce.adapters.repository.springdata.adapters.ApparelCategoryRepositoryJpaAdapter;
import com.ecommerce.adapters.repository.springdata.adapters.BrandCategoryRepositoryJpaAdapter;
import com.ecommerce.adapters.repository.springdata.adapters.GenderCategoryRepositoryJpaAdapter;
import com.ecommerce.adapters.repository.springdata.adapters.ProductRepositoryJpaAdapter;
import com.ecommerce.domain.service.ProductService;
import org.springframework.stereotype.Service;

@Service
public class ProductHandler extends ProductService {


    public ProductHandler(ProductRepositoryJpaAdapter productRepository, GenderCategoryRepositoryJpaAdapter genderCategoryRepositoryPort, ApparelCategoryRepositoryJpaAdapter apparelCategoryRepositoryPort, BrandCategoryRepositoryJpaAdapter brandCategoryRepositoryPort) {
        super(productRepository, genderCategoryRepositoryPort, apparelCategoryRepositoryPort, brandCategoryRepositoryPort);
    }
}
