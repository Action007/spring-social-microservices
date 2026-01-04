package com.social.microservices.iam_service.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {

    private final List<String> posts = new ArrayList<>();

    @Override
    public void createPost(String postContent) {
        posts.add(postContent);
    }
}
