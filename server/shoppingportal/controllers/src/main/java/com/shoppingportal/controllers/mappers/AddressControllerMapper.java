package com.ecommerce.controllers.mappers;


import com.ecommerce.controllers.dto.address.AddressDto;
import com.ecommerce.domain.model.address.Address;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {CountryControllerMapper.class, CivilityControllerMapper.class, OrderControllerMapper.class})
public interface AddressControllerMapper {

    @Mapping(source = "country", target = "countryDto")
    @Mapping(source = "civility", target = "civilityDto")
    AddressDto toAddressDto(Address address);

    @Mapping(ignore = true, target = "country")
    @Mapping(ignore = true, target = "civility")
    @Mapping(ignore = true, target = "user")
    @Mapping(ignore = true, target = "orders")
    Address toAddress(AddressDto addressDto);
}
