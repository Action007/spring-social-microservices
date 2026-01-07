package com.social.microservices.iam_service.model.request.post;

import java.io.Serializable;

import com.social.microservices.iam_service.model.enums.PostSortField;

import lombok.Data;

@Data
public class PostSearchRequest implements Serializable {

    private String title;
    private String content;
    private Integer likes;

    private Boolean deleted;
    private String keyword;
    private PostSortField sortField;

}
