package com.social.microservices.iam_service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.social.microservices.iam_service.model.dto.user.UserDTO;
import com.social.microservices.iam_service.model.entity.User;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {

    @Mapping(source = "last_login", target = "lastLogin")
    UserDTO toDto(User user);
}
