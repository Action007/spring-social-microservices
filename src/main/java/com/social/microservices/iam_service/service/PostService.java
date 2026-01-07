package com.social.microservices.iam_service.service;

import org.springframework.data.domain.Pageable;

import com.social.microservices.iam_service.model.dto.post.PostDTO;
import com.social.microservices.iam_service.model.dto.post.PostSearchDTO;
import com.social.microservices.iam_service.model.request.post.NewPostRequest;
import com.social.microservices.iam_service.model.request.post.PostSearchRequest;
import com.social.microservices.iam_service.model.request.post.UpdatePostRequest;
import com.social.microservices.iam_service.model.response.IamResponse;
import com.social.microservices.iam_service.model.response.PaginationResponse;

import jakarta.validation.constraints.NotNull;

public interface PostService {
    IamResponse<PostDTO> getById(@NotNull Integer postId);

    IamResponse<PostDTO> createPost(@NotNull NewPostRequest postRequest);

    IamResponse<PostDTO> updatePost(@NotNull Integer postId, @NotNull UpdatePostRequest updatePostRequest);

    void softDeletePost(@NotNull Integer postId);

    IamResponse<PaginationResponse<PostSearchDTO>> findAllPosts(Pageable pageable);

    IamResponse<PaginationResponse<PostSearchDTO>> searchPosts(@NotNull PostSearchRequest request, Pageable pageable);
}
