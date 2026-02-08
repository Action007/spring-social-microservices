package com.social.microservices.iam_service.model.request.user;

import java.io.Serializable;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LoginRequest implements Serializable {

    @Email
    @NotNull
    private String email;

    @NotEmpty
    private String password;
}
