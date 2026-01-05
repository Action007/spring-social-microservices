package com.social.microservices.iam_service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.social.microservices.iam_service.model.entities.Post;

public interface PostRepository extends JpaRepository<Post, Integer> {

}
