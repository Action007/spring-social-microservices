package com.social.microservices.iam_service.mapper;

import org.hibernate.type.descriptor.DateTimeUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.social.microservices.iam_service.model.dto.post.PostDTO;
import com.social.microservices.iam_service.model.dto.post.PostSearchDTO;
import com.social.microservices.iam_service.model.entity.Post;
import com.social.microservices.iam_service.model.entity.User;
import com.social.microservices.iam_service.model.request.post.NewPostRequest;
import com.social.microservices.iam_service.model.request.post.UpdatePostRequest;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, imports = {
        DateTimeUtils.class, Object.class })
public interface PostMapper {
    PostDTO toPostDTO(Post post);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "created", ignore = true)
    @Mapping(target = "user", source = "user")
    @Mapping(target = "createdBy", source = "user.username")
    Post createPost(NewPostRequest postRequest, User user);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "created", ignore = true)
    void updatePost(@MappingTarget Post post, UpdatePostRequest request);

    @Mapping(source = "deleted", target = "isDeleted")
    @Mapping(target = "createdBy", source = "user.username")
    PostSearchDTO toPostSearchDTO(Post post);
}
