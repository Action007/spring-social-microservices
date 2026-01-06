package com.social.microservices.iam_service.service;

import java.time.LocalDateTime;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.social.microservices.iam_service.mapper.PostMapper;
import com.social.microservices.iam_service.model.constants.ApiErrorMessage;
import com.social.microservices.iam_service.model.dto.post.PostDTO;
import com.social.microservices.iam_service.model.dto.post.PostSearchDTO;
import com.social.microservices.iam_service.model.entity.Post;
import com.social.microservices.iam_service.model.exception.DataExistException;
import com.social.microservices.iam_service.model.exception.NotFoundException;
import com.social.microservices.iam_service.model.request.post.NewPostRequest;
import com.social.microservices.iam_service.model.request.post.UpdatePostRequest;
import com.social.microservices.iam_service.model.response.IamResponse;
import com.social.microservices.iam_service.model.response.PaginationResponse;
import com.social.microservices.iam_service.model.response.PaginationResponse.Pagination;
import com.social.microservices.iam_service.repositories.PostRepository;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final PostMapper postMapper;

    @Override
    public IamResponse<PostDTO> getById(@NotNull Integer postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new NotFoundException(ApiErrorMessage.POST_NOT_FOUND_BY_ID.getMessage(postId)));

        PostDTO postDTO = postMapper.toPostDTO(post);
        return IamResponse.createSuccessful(postDTO);
    }

    @Override
    public IamResponse<PostDTO> createPost(@NotNull NewPostRequest postRequest) {
        if (postRepository.existsByTitle(postRequest.getTitle())) {
            throw new DataExistException(ApiErrorMessage.POST_ALREADY_EXISTS.getMessage(postRequest.getTitle()));
        }

        Post post = postMapper.createPost(postRequest);
        Post savedPost = postRepository.save(post);
        PostDTO postDTO = postMapper.toPostDTO(savedPost);

        return IamResponse.createSuccessful(postDTO);
    }

    @Override
    public IamResponse<PostDTO> updatePost(@NotNull Integer postId, @NotNull UpdatePostRequest request) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new NotFoundException(ApiErrorMessage.POST_NOT_FOUND_BY_ID.getMessage(postId)));

        postMapper.updatePost(post, request);
        post.setUpdated(LocalDateTime.now());
        post = postRepository.save(post);

        PostDTO postDTO = postMapper.toPostDTO(post);
        return IamResponse.createSuccessful(postDTO);
    }

    @Override
    public void softDeletePost(@NotNull Integer postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new NotFoundException(ApiErrorMessage.POST_NOT_FOUND_BY_ID.getMessage(postId)));

        post.setDeleted(true);
        postRepository.save(post);
    }

    @Override
    public IamResponse<PaginationResponse<PostSearchDTO>> findAllPosts(Pageable pageable) {
        Page<PostSearchDTO> posts = postRepository.findAll(pageable)
                .map(postMapper::toPostSearchDTO);

        PaginationResponse<PostSearchDTO> paginationResponse = new PaginationResponse<>(
                posts.getContent(),
                new PaginationResponse.Pagination(
                        posts.getTotalElements(),
                        pageable.getDefaultPageSize(),
                        posts.getNumber() + 1,
                        posts.getTotalPages()));

        return IamResponse.createSuccessful(paginationResponse);
    }
}
