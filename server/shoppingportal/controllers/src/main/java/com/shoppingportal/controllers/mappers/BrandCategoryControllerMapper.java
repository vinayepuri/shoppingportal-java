package com.ecommerce.controllers.mappers;

import com.ecommerce.controllers.dto.category.BrandCategoriesResponse;
import com.ecommerce.controllers.dto.category.BrandCategoryDto;
import com.ecommerce.domain.model.category.BrandCategoriesDomainResponse;
import com.ecommerce.domain.model.category.BrandCategory;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BrandCategoryControllerMapper {

    BrandCategoryDto toBrandCategoryDto(BrandCategory brandCategory);

    BrandCategoriesResponse toBrandCategoriesResponse(BrandCategoriesDomainResponse brandCategoriesDomainResponse);

}

