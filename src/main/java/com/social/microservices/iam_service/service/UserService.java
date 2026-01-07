package com.social.microservices.iam_service.service;

import com.social.microservices.iam_service.model.dto.user.UserDTO;
import com.social.microservices.iam_service.model.response.IamResponse;

import jakarta.validation.constraints.NotNull;

public interface UserService {

    IamResponse<UserDTO> getById(@NotNull Integer userId);
}
