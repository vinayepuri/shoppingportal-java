package com.ecommerce.controllers.mappers;

import com.ecommerce.controllers.dto.profile.ProfileDto;
import com.ecommerce.domain.model.user.Profile;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProfileControllerMapper {

    @Mapping(source ="info", target="infoDto")
    @Mapping(source ="email", target="emailDto")
    ProfileDto toProfile(Profile profile);
}
