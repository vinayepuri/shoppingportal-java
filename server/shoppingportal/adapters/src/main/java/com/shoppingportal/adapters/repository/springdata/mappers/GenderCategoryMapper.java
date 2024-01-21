package com.ecommerce.adapters.repository.springdata.mappers;

import com.ecommerce.domain.model.category.GenderCategory;
import com.ecommerce.adapters.repository.springdata.entity.GenderCategoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = ProductMapper.class)
public interface GenderCategoryMapper {

    @Mapping(source = "productEntitySet", target = "products")
    GenderCategory toGenderCategory(GenderCategoryEntity genderCategoryEntity);

    List<GenderCategory> toGenderCategories(List<GenderCategoryEntity> genderCategoryEntities);


}
