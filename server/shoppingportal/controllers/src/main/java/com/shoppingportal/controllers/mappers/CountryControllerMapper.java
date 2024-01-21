package com.ecommerce.controllers.mappers;

import com.ecommerce.controllers.dto.country.CountryDto;
import com.ecommerce.domain.model.country.Country;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface CountryControllerMapper {

    CountryDto toCountryDto(Country country);

}
