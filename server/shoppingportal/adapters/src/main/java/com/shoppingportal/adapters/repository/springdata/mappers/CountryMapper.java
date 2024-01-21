package com.ecommerce.adapters.repository.springdata.mappers;

import com.ecommerce.domain.model.country.Country;
import com.ecommerce.adapters.repository.springdata.entity.CountryEntity;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface CountryMapper {

    Country toCountry(CountryEntity countryEntity);

}
