package com.social.microservices.iam_service.service.impl;

import com.social.microservices.iam_service.mapper.PostMapper;
import com.social.microservices.iam_service.model.constants.ApiErrorMessage;
import com.social.microservices.iam_service.model.dto.post.PostDTO;
import com.social.microservices.iam_service.model.dto.post.PostSearchDTO;
import com.social.microservices.iam_service.model.entity.Post;
import com.social.microservices.iam_service.model.exception.DataExistException;
import com.social.microservices.iam_service.model.exception.NotFoundException;
import com.social.microservices.iam_service.model.request.post.NewPostRequest;
import com.social.microservices.iam_service.model.request.post.PostSearchRequest;
import com.social.microservices.iam_service.model.request.post.UpdatePostRequest;
import com.social.microservices.iam_service.model.response.IamResponse;
import com.social.microservices.iam_service.model.response.PaginationResponse;
import com.social.microservices.iam_service.model.response.PaginationResponse.Pagination;
import com.social.microservices.iam_service.repositories.PostRepository;
import com.social.microservices.iam_service.repositories.criteria.PostSearchCriteria;
import com.social.microservices.iam_service.service.PostService;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final PostMapper postMapper;

    @Override
    public IamResponse<PostDTO> getById(@NotNull Integer postId) {
        Post post = postRepository.findByIdAndDeletedFalse(postId)
                .orElseThrow(() -> new NotFoundException(ApiErrorMessage.POST_NOT_FOUND_BY_ID.getMessage(postId)));

        PostDTO postDto = postMapper.toPostDTO(post);

        return IamResponse.createSuccessful(postDto);
    }

    @Override
    public IamResponse<PostDTO> createPost(@NotNull NewPostRequest request) {
        if (postRepository.existsByTitle(request.getTitle())) {
            throw new DataExistException(ApiErrorMessage.POST_ALREADY_EXISTS.getMessage(request.getTitle()));
        }

        Post post = postMapper.createPost(request);
        Post savedPost = postRepository.save(post);
        PostDTO postDto = postMapper.toPostDTO(savedPost);

        return IamResponse.createSuccessful(postDto);
    }

    @Override
    public IamResponse<PostDTO> updatePost(@NotNull Integer postId, @NotNull UpdatePostRequest request) {
        Post post = postRepository.findByIdAndDeletedFalse(postId)
                .orElseThrow(() -> new NotFoundException(ApiErrorMessage.POST_NOT_FOUND_BY_ID.getMessage(postId)));

        postMapper.updatePost(post, request);
        post.setUpdated(LocalDateTime.now());
        post = postRepository.save(post);

        PostDTO postDto = postMapper.toPostDTO(post);
        return IamResponse.createSuccessful(postDto);

    }

    @Override
    public void softDeletePost(Integer postId) {
        Post post = postRepository.findByIdAndDeletedFalse(postId)
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
                        pageable.getPageSize(),
                        posts.getNumber() + 1,
                        posts.getTotalPages()));

        return IamResponse.createSuccessful(paginationResponse);
    }

    @Override
    public IamResponse<PaginationResponse<PostSearchDTO>> searchPosts(@NotNull PostSearchRequest request,
            Pageable pageable) {
        Specification<Post> specification = new PostSearchCriteria(request);
        Page<PostSearchDTO> posts = postRepository.findAll(specification, pageable)
                .map(postMapper::toPostSearchDTO);

        PaginationResponse<PostSearchDTO> response = PaginationResponse.<PostSearchDTO>builder()
                .content(posts.getContent())
                .pagination(PaginationResponse.Pagination.builder()
                        .total(posts.getTotalElements())
                        .limit(pageable.getPageSize())
                        .page(posts.getNumber() + 1)
                        .pages(posts.getTotalPages())
                        .build())
                .build();

        return IamResponse.createSuccessful(response);
    }
}
