package com.social.microservices.iam_service.service.impl;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.social.microservices.iam_service.model.constants.ApiErrorMessage;
import com.social.microservices.iam_service.model.entity.RefreshToken;
import com.social.microservices.iam_service.model.entity.User;
import com.social.microservices.iam_service.model.exception.NotFoundException;
import com.social.microservices.iam_service.repository.RefreshTokenRepository;
import com.social.microservices.iam_service.service.RefreshTokenService;
import com.social.microservices.iam_service.utils.ApiUtils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class RefreshTokenImpl implements RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;

    @Override
    public RefreshToken generateOrUpdateRefreshToken(User user) {
        return refreshTokenRepository.findByUserId(user.getId())
                .map((refreshToken) -> {
                    refreshToken.setCreated(LocalDateTime.now());
                    refreshToken.setToken(ApiUtils.generateUuidWithoutDash());
                    return refreshTokenRepository.save(refreshToken);
                })
                .orElseGet(() -> {
                    RefreshToken newToken = new RefreshToken();
                    newToken.setUser(user);
                    newToken.setCreated(LocalDateTime.now());
                    newToken.setToken(ApiUtils.generateUuidWithoutDash());
                    return refreshTokenRepository.save(newToken);
                });
    }

    @Override
    public RefreshToken validateAndRefreshToken(String requestRefreshToken) {
        RefreshToken refreshToken = refreshTokenRepository.findByToken(requestRefreshToken)
                .orElseThrow(() -> new NotFoundException(ApiErrorMessage.NOT_FOUND_REFRESH_TOKEN.getMessage()));

        refreshToken.setCreated(LocalDateTime.now());
        refreshToken.setToken(ApiUtils.generateUuidWithoutDash());
        return refreshTokenRepository.save(refreshToken);
    }

}
