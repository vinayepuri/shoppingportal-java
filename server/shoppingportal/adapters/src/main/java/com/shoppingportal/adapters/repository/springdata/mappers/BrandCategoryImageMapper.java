package com.ecommerce.adapters.repository.springdata.mappers;

import com.ecommerce.domain.model.category.BrandCategoryImage;
import com.ecommerce.adapters.repository.springdata.entity.BrandCategoryImageEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {BrandCategoryMapper.class, GenderCategoryMapper.class})
public interface BrandCategoryImageMapper {

    @Mapping(source = "brandCategoryEntity", target = "brandCategory")
    @Mapping(source = "genderCategoryEntity", target = "genderCategory")
    BrandCategoryImage toBrandCategoryImage(BrandCategoryImageEntity brandCategoryImageEntity);
}
