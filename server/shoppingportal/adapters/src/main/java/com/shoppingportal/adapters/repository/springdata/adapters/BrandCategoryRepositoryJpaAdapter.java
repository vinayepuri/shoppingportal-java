package com.ecommerce.adapters.repository.springdata.adapters;

import com.ecommerce.adapters.repository.springdata.entity.BrandCategoryEntity;
import com.ecommerce.adapters.repository.springdata.mappers.BrandCategoryMapper;
import com.ecommerce.adapters.repository.springdata.repository.BrandCategoryJpaRepository;
import com.ecommerce.domain.exception.BrandCategoriesNotFound;
import com.ecommerce.domain.model.category.BrandCategory;
import com.ecommerce.domain.port.adapters.repositories.BrandCategoryRepositoryPort;
import com.ecommerce.domain.util.message.ErrorMessages;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class BrandCategoryRepositoryJpaAdapter implements BrandCategoryRepositoryPort {

    private final BrandCategoryJpaRepository brandCategoryJpaRepository;
    private final BrandCategoryMapper brandCategoryMapper;

    public BrandCategoryRepositoryJpaAdapter(BrandCategoryJpaRepository brandCategoryJpaRepository, BrandCategoryMapper brandCategoryMapper) {
        this.brandCategoryJpaRepository = brandCategoryJpaRepository;
        this.brandCategoryMapper = brandCategoryMapper;
    }

    @Override
    public List<BrandCategory> findByGenderCategoryIdAndProductsIsNotNull(int genderId) {
        List<BrandCategoryEntity> brandCategoryEntityList = brandCategoryJpaRepository.findByGenderCategoryIdAndProductsIsNotNull(genderId);
        if(brandCategoryEntityList.isEmpty())
            throw new BrandCategoriesNotFound(ErrorMessages.BRAND_CATEGORIES_NOT_FOUND);
        return brandCategoryEntityList.stream()
                .map(brandCategoryMapper::toBrandCategory)
                .collect(Collectors.toList());
    }

    @Override
    public List<BrandCategory> findByGenderCategoryIdAndApparelCategoryIdAndProductsIsNotNull(int genderId, int apparelCategoryId) {
        List<BrandCategoryEntity> brandCategoryEntityList = brandCategoryJpaRepository.findByGenderCategoryIdAndApparelCategoryIdAndProductsIsNotNull(genderId, apparelCategoryId);
        return brandCategoryEntityList.stream()
                .map(brandCategoryMapper::toBrandCategory)
                .collect(Collectors.toList());
    }

    @Override
    public List<BrandCategory> findByIdIn(List<Integer> categoryIds) {
        List<BrandCategoryEntity> apparelCategoryEntityList = brandCategoryJpaRepository.findByIdIn(categoryIds);
        return apparelCategoryEntityList.stream()
                .map(brandCategoryMapper::toBrandCategory)
                .collect(Collectors.toList());
    }
}
