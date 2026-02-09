package com.social.microservices.iam_service.service;

import com.social.microservices.iam_service.model.entity.RefreshToken;
import com.social.microservices.iam_service.model.entity.User;

public interface RefreshTokenService {

    RefreshToken generateOrUpdateRefreshToken(User user);

    RefreshToken validateAndRefreshToken(String refreshToken);

}
