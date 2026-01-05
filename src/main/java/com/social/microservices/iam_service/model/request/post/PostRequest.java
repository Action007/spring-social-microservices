package com.social.microservices.iam_service.model.request.post;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostRequest implements Serializable {

    private String title;
    private String content;
    private Integer likes;
}
