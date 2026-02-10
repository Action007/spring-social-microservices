package com.social.microservices.iam_service.service;

import com.social.microservices.iam_service.model.dto.user.UserProfileDTO;
import com.social.microservices.iam_service.model.request.user.LoginRequest;
import com.social.microservices.iam_service.model.request.user.RegistrationUserRequest;
import com.social.microservices.iam_service.model.response.IamResponse;

public interface AuthService {

    IamResponse<UserProfileDTO> login(LoginRequest request);

    IamResponse<UserProfileDTO> refreshAccessToken(String refreshToken);

    IamResponse<UserProfileDTO> registerUser(RegistrationUserRequest request);
}
