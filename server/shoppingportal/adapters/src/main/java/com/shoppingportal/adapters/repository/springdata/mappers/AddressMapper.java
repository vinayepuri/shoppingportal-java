package com.ecommerce.adapters.repository.springdata.mappers;

import com.ecommerce.adapters.repository.springdata.entity.AddressEntity;
import com.ecommerce.domain.model.address.Address;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring", uses = {CountryMapper.class, CivilityMapper.class, UserMapper.class})
public interface AddressMapper {

    @Mapping(source = "countryEntity", target = "country")
    @Mapping(source = "civilityEntity", target = "civility")
    @Mapping(source = "userEntity", target = "user")
    Address toAddress(AddressEntity addressEntity);

    @Mapping(ignore = true, target = "userEntity")
    @Mapping(ignore = true, target = "civilityEntity")
    @Mapping(ignore = true, target = "countryEntity")
    @Mapping(ignore = true, target = "orderEntitySet")
    AddressEntity toAddressEntity(Address address);

}
