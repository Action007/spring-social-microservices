package com.social.microservices.iam_service.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.social.microservices.iam_service.model.constants.ApiLogMessage;
import com.social.microservices.iam_service.model.dto.post.PostDTO;
import com.social.microservices.iam_service.model.request.post.PostRequest;
import com.social.microservices.iam_service.model.response.IamResponse;
import com.social.microservices.iam_service.service.PostService;
import com.social.microservices.iam_service.utils.ApiUtils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;

    @GetMapping("/{id}")
    public ResponseEntity<IamResponse<PostDTO>> getPostById(@PathVariable(name = "id") Integer postId) {
        log.trace(ApiLogMessage.NAME_OF_CURREN_METHOD.getValue(), ApiUtils.getMethodName());

        IamResponse<PostDTO> response = postService.getById(postId);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/create")
    public ResponseEntity<IamResponse<PostDTO>> createPost(@RequestBody PostRequest postRequest) {
        log.trace(ApiLogMessage.NAME_OF_CURREN_METHOD.getValue(), ApiUtils.getMethodName());

        IamResponse<PostDTO> response = postService.createPost(postRequest);
        return ResponseEntity.ok(response);
    }
}
