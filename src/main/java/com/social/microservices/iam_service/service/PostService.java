package com.social.microservices.iam_service.service;

import com.social.microservices.iam_service.model.dto.post.PostDTO;
import com.social.microservices.iam_service.model.request.post.PostRequest;
import com.social.microservices.iam_service.model.response.IamResponse;

import jakarta.validation.constraints.NotNull;

public interface PostService {
    IamResponse<PostDTO> getById(@NotNull Integer postId);

    IamResponse<PostDTO> createPost(@NotNull PostRequest postRequest);
}
