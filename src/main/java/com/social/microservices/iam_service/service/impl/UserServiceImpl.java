package com.social.microservices.iam_service.service.impl;

import org.springframework.stereotype.Service;

import com.social.microservices.iam_service.mapper.UserMapper;
import com.social.microservices.iam_service.model.constants.ApiErrorMessage;
import com.social.microservices.iam_service.model.dto.user.UserDTO;
import com.social.microservices.iam_service.model.entity.User;
import com.social.microservices.iam_service.model.exception.NotFoundException;
import com.social.microservices.iam_service.model.response.IamResponse;
import com.social.microservices.iam_service.repositories.UserRepository;
import com.social.microservices.iam_service.service.UserService;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public IamResponse<UserDTO> getById(@NotNull Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(ApiErrorMessage.USER_NOT_FOUND_BY_ID.getMessage(userId)));

        UserDTO userDTO = userMapper.toDto(user);
        return IamResponse.createSuccessful(userDTO);
    }
}
