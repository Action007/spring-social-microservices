package com.social.microservices.iam_service.model.dto.user;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class UserSearchDTO implements Serializable {

    private Integer id;
    private String username;
    private String email;
    private LocalDateTime created;
    private Boolean isDeleted;

}
