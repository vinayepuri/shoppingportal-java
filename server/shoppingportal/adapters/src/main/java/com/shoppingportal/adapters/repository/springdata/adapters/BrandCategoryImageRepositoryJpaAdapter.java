package com.ecommerce.adapters.repository.springdata.adapters;


import com.ecommerce.domain.exception.BrandCategoryImageNotFound;
import com.ecommerce.domain.model.category.BrandCategoryImage;
import com.ecommerce.adapters.repository.springdata.entity.BrandCategoryImageEntity;
import com.ecommerce.adapters.repository.springdata.mappers.BrandCategoryImageMapper;
import com.ecommerce.domain.util.message.ErrorMessages;
import com.ecommerce.domain.port.adapters.repositories.BrandCategoryImageRepositoryPort;
import com.ecommerce.adapters.repository.springdata.repository.BrandCategoryImageJpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public class BrandCategoryImageRepositoryJpaAdapter implements BrandCategoryImageRepositoryPort {

    private final BrandCategoryImageJpaRepository brandCategoryImageJpaRepository;
    private final BrandCategoryImageMapper brandCategoryImageMapper;

    public BrandCategoryImageRepositoryJpaAdapter(BrandCategoryImageJpaRepository brandCategoryImageJpaRepository, BrandCategoryImageMapper brandCategoryImageMapper) {
        this.brandCategoryImageJpaRepository = brandCategoryImageJpaRepository;
        this.brandCategoryImageMapper = brandCategoryImageMapper;
    }

    @Override
    public BrandCategoryImage findByBrandCategoryIdAndGenderCategoryId(int brandId, int gender) {
        BrandCategoryImageEntity brandCategoryImageEntity = brandCategoryImageJpaRepository.findByBrandCategoryEntityIdAndGenderCategoryEntityId(brandId, gender)
                .orElseThrow(() -> new BrandCategoryImageNotFound(ErrorMessages.BRAND_CATEGORY_IMAGE_NOT_FOUND,brandId));
        return brandCategoryImageMapper.toBrandCategoryImage(brandCategoryImageEntity);
    }
}
