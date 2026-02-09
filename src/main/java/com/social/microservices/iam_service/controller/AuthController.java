package com.social.microservices.iam_service.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.social.microservices.iam_service.model.constants.ApiLogMessage;
import com.social.microservices.iam_service.model.dto.user.UserProfileDTO;
import com.social.microservices.iam_service.model.request.user.LoginRequest;
import com.social.microservices.iam_service.model.response.IamResponse;
import com.social.microservices.iam_service.service.AuthService;
import com.social.microservices.iam_service.utils.ApiUtils;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("${end.points.auth}")
public class AuthController {

    private final AuthService authService;

    @PostMapping("${end.points.login}")
    public ResponseEntity<?> postMethodName(@RequestBody @Valid LoginRequest request, HttpServletResponse response) {
        log.trace(ApiLogMessage.NAME_OF_CURRENT_METHOD.getValue(), ApiUtils.getMethodName());

        IamResponse<UserProfileDTO> result = authService.login(request);
        Cookie authorizationCookie = ApiUtils.createAuthCookie(result.getPayload().getToken());
        response.addCookie(authorizationCookie);

        return ResponseEntity.ok(result);
    }

}
