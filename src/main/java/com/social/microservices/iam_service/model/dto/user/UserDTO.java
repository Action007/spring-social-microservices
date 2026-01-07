package com.social.microservices.iam_service.model.dto.user;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.social.microservices.iam_service.model.enums.RegistrationStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO implements Serializable {

    private Integer id;
    private String username;
    private String email;
    private LocalDateTime created;
    private LocalDateTime lastLogin;
    private RegistrationStatus registrationStatus;
}
