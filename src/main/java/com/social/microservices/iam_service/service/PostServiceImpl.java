package com.social.microservices.iam_service.service;

import org.springframework.stereotype.Service;

import com.social.microservices.iam_service.mapper.PostMapper;
import com.social.microservices.iam_service.model.constants.ApiErrorMessage;
import com.social.microservices.iam_service.model.dto.post.PostDTO;
import com.social.microservices.iam_service.model.entities.Post;
import com.social.microservices.iam_service.model.exception.NotFoundException;
import com.social.microservices.iam_service.model.request.post.PostRequest;
import com.social.microservices.iam_service.model.response.IamResponse;
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
    public IamResponse<PostDTO> createPost(@NotNull PostRequest postRequest) {
        Post post = postMapper.createPost(postRequest);
        Post savedPost = postRepository.save(post);
        PostDTO postDTO = postMapper.toPostDTO(savedPost);

        return IamResponse.createSuccessful(postDTO);
    }
}
