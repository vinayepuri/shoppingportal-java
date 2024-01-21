package com.ecommerce.controllers.mappers;

import com.ecommerce.controllers.dto.category.ApparelCategoriesResponse;
import com.ecommerce.controllers.dto.category.ApparelCategoryDto;
import com.ecommerce.domain.model.category.ApparelCategoriesDomainResponse;
import com.ecommerce.domain.model.category.ApparelCategory;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel = "spring")
public interface ApparelCategoryControllerMapper {


    ApparelCategoryDto toApparelCategoryDto(ApparelCategory apparelCategory);

    ApparelCategoriesResponse toApparelCategoriesResponse(ApparelCategoriesDomainResponse apparelCategoryResponse);

    List<ApparelCategoryDto> toApparelCategoryDtoList(List<ApparelCategory> apparelCategoryList);
}
