package com.social.microservices.iam_service.mapper;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

import org.hibernate.type.descriptor.DateTimeUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.social.microservices.iam_service.model.dto.role.RoleDTO;
import com.social.microservices.iam_service.model.dto.user.UserDTO;
import com.social.microservices.iam_service.model.dto.user.UserProfileDTO;
import com.social.microservices.iam_service.model.dto.user.UserSearchDTO;
import com.social.microservices.iam_service.model.entity.Role;
import com.social.microservices.iam_service.model.entity.User;
import com.social.microservices.iam_service.model.enums.RegistrationStatus;
import com.social.microservices.iam_service.model.request.user.NewUserRequest;
import com.social.microservices.iam_service.model.request.user.UpdateUserRequest;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, imports = {
        RegistrationStatus.class, Objects.class, DateTimeUtils.class })
public interface UserMapper {

    @Mapping(target = "roles", expression = "java(mapRoles(user.getRoles()))")
    UserDTO toDto(User user);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "created", ignore = true)
    @Mapping(target = "registrationStatus", expression = "java(RegistrationStatus.ACTIVE)")
    User createUser(NewUserRequest request);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "created", ignore = true)
    void updateUser(@MappingTarget User user, UpdateUserRequest request);

    @Mapping(source = "deleted", target = "isDeleted")
    @Mapping(target = "roles", expression = "java(mapRoles(user.getRoles()))")
    UserSearchDTO toUserSearchDto(User user);

    @Mapping(target = "roles", expression = "java(mapRoles(user.getRoles()))")
    @Mapping(target = "username", source = "user.username")
    @Mapping(target = "email", source = "user.email")
    @Mapping(target = "token", source = "token")
    @Mapping(target = "refreshToken", source = "refreshToken")
    UserProfileDTO toUserProfileDto(User user, String token, String refreshToken);

    default List<RoleDTO> mapRoles(Collection<Role> roles) {
        return roles.stream()
                .map((role) -> new RoleDTO(role.getId(), role.getName())).toList();
    }
}
