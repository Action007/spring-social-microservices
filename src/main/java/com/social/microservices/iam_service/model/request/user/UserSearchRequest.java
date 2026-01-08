package com.social.microservices.iam_service.model.request.user;

import java.io.Serializable;

import com.social.microservices.iam_service.model.enums.UserSortField;

import lombok.Data;

@Data
public class UserSearchRequest implements Serializable {

    private String username;
    private String email;

    private Boolean deleted;
    private String keyword;
    private UserSortField sortField;

}
