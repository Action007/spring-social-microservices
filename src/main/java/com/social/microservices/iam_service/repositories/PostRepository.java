package com.social.microservices.iam_service.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.social.microservices.iam_service.model.entity.Post;

public interface PostRepository extends JpaRepository<Post, Integer> {

    boolean existsByTitle(String title);

    Optional<Post> findByIdAndDeletedFalse(Integer id);
}