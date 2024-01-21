package com.ecommerce.adapters.repository.springdata.mappers;

import com.ecommerce.domain.model.user.User;
import com.ecommerce.adapters.repository.springdata.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {CivilityMapper.class})
public interface UserMapper {

    @Mapping(source = "civilityEntity", target = "civility")
    User toUser(UserEntity user);

    @Mapping(ignore = true, target = "civilityEntity")
    @Mapping(ignore = true, target = "cartEntity")
    @Mapping(ignore = true, target = "orderEntityList")
    @Mapping(ignore = true, target = "addressEntityList")
    UserEntity toUserEntity(User user);
}
