package com.social.microservices.iam_service.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.social.microservices.iam_service.model.constants.ApiLogMessage;
import com.social.microservices.iam_service.model.dto.user.UserDTO;
import com.social.microservices.iam_service.model.response.IamResponse;
import com.social.microservices.iam_service.service.UserService;
import com.social.microservices.iam_service.utils.ApiUtils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Slf4j
@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping("/id")
    public ResponseEntity<IamResponse<UserDTO>> getUserById(@PathVariable(name = "id") Integer userId) {
        log.trace(ApiLogMessage.NAME_OF_CURRENT_METHOD.getValue(), ApiUtils.getMethodName());
    }

}
