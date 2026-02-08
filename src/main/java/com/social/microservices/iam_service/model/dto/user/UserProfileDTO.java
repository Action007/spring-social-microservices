package com.social.microservices.iam_service.model.dto.user;

import java.time.LocalDateTime;
import java.util.List;

import com.social.microservices.iam_service.model.dto.role.RoleDTO;
import com.social.microservices.iam_service.model.enums.RegistrationStatus;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserProfileDTO {

    private Integer id;
    private String username;
    private String email;

    private RegistrationStatus registrationStatus;
    private LocalDateTime lastLogin;

    private String token;
    private List<RoleDTO> roles;
}