package com.social.microservices.iam_service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.social.microservices.iam_service.model.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
