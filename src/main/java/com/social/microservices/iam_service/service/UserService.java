package com.social.microservices.iam_service.service;

import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.social.microservices.iam_service.model.dto.user.UserDTO;
import com.social.microservices.iam_service.model.dto.user.UserSearchDTO;
import com.social.microservices.iam_service.model.request.user.NewUserRequest;
import com.social.microservices.iam_service.model.request.user.UpdateUserRequest;
import com.social.microservices.iam_service.model.request.user.UserSearchRequest;
import com.social.microservices.iam_service.model.response.IamResponse;
import com.social.microservices.iam_service.model.response.PaginationResponse;

import jakarta.validation.constraints.NotNull;

public interface UserService extends UserDetailsService {

    IamResponse<UserDTO> getById(@NotNull Integer userId);

    IamResponse<UserDTO> createUser(@NotNull NewUserRequest request);

    IamResponse<UserDTO> updateUser(@NotNull Integer postId, @NotNull UpdateUserRequest request);

    void softDeleteUser(Integer userId);

    IamResponse<PaginationResponse<UserSearchDTO>> findAllUsers(Pageable pageable);

    IamResponse<PaginationResponse<UserSearchDTO>> searchUsers(UserSearchRequest request, Pageable pageable);

}
