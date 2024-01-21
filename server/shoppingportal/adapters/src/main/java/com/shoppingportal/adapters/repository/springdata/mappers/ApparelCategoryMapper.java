package com.ecommerce.adapters.repository.springdata.mappers;

import com.ecommerce.domain.model.category.ApparelCategory;
import com.ecommerce.adapters.repository.springdata.entity.ApparelCategoryEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ApparelCategoryMapper {

    ApparelCategory toApparelCategory(ApparelCategoryEntity apparelCategoryEntity);


}
