package com.ecommerce.adapters.repository.springdata.mappers;

import com.ecommerce.domain.model.user.Civility;
import com.ecommerce.adapters.repository.springdata.entity.CivilityEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CivilityMapper {


    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    Civility toCivility(CivilityEntity civilityEntity);

}
