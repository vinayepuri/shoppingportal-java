package com.ecommerce.controllers.mappers;

import com.ecommerce.controllers.dto.profile.CivilityDto;
import com.ecommerce.domain.model.user.Civility;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CivilityControllerMapper {

    CivilityDto toCivilityDto(Civility civility);
    Civility toCivility(CivilityDto civilityDto);
}
