package com.ecommerce.adapters.repository.springdata.adapters;

import com.ecommerce.adapters.repository.springdata.mappers.ApparelCategoryMapper;
import com.ecommerce.domain.exception.ApparelCategoriesNotFound;
import com.ecommerce.domain.model.category.ApparelCategory;
import com.ecommerce.domain.port.adapters.repositories.ApparelCategoryRepositoryPort;
import com.ecommerce.adapters.repository.springdata.entity.ApparelCategoryEntity;
import com.ecommerce.adapters.repository.springdata.repository.ApparelCategoryJpaRepository;
import com.ecommerce.domain.util.message.ErrorMessages;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ApparelCategoryRepositoryJpaAdapter implements ApparelCategoryRepositoryPort {

    private final ApparelCategoryJpaRepository apparelCategoryJpaRepository;
    private final ApparelCategoryMapper apparelCategoryMapper;

    public ApparelCategoryRepositoryJpaAdapter(ApparelCategoryJpaRepository apparelCategoryJpaRepository, ApparelCategoryMapper apparelCategoryMapper) {
        this.apparelCategoryJpaRepository = apparelCategoryJpaRepository;
        this.apparelCategoryMapper = apparelCategoryMapper;
    }


    @Override
    public List<ApparelCategory> findByIdIn(List<Integer> categoryIds) {
        List<ApparelCategoryEntity> apparelCategoryEntityList = apparelCategoryJpaRepository.findByIdIn(categoryIds);
        if(apparelCategoryEntityList.isEmpty())
            throw new ApparelCategoriesNotFound(ErrorMessages.APPAREL_CATEGORIES_NOT_FOUND);
        return apparelCategoryEntityList.stream()
                .map(apparelCategoryMapper::toApparelCategory)
                .collect(Collectors.toList());
    }

    @Override
    public List<ApparelCategory> findByGenderCategoryIdAndProductsIsNotNull(int genderId) {
        List<ApparelCategoryEntity> apparelCategoryEntityList = apparelCategoryJpaRepository.findByGenderCategoryIdAndProductsIsNotNull(genderId);
        return apparelCategoryEntityList.stream()
                .map(apparelCategoryMapper::toApparelCategory)
                .collect(Collectors.toList());
    }

    @Override
    public List<ApparelCategory> findByBrandCategoryIdAndGenderCategoryIdAndProductsIsNotNull(int brandId, int genderId) {
        List<ApparelCategoryEntity> apparelCategoryEntityList = apparelCategoryJpaRepository.findByBrandCategoryIdAndGenderCategoryIdAndProductsIsNotNull(brandId, genderId);
        return apparelCategoryEntityList.stream()
                .map(apparelCategoryMapper::toApparelCategory)
                .collect(Collectors.toList());
    }
}