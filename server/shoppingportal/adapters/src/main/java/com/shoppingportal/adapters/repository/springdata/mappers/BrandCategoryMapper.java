package com.ecommerce.adapters.repository.springdata.mappers;

import com.ecommerce.domain.model.category.BrandCategory;
import com.ecommerce.adapters.repository.springdata.entity.BrandCategoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BrandCategoryMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(ignore = true, target = "imageUrl")
    BrandCategory toBrandCategory(BrandCategoryEntity brandCategoryEntity);

}
