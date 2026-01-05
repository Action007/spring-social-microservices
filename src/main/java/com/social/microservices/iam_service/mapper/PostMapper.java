package com.social.microservices.iam_service.mapper;

import org.hibernate.type.descriptor.DateTimeUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.social.microservices.iam_service.model.dto.post.PostDTO;
import com.social.microservices.iam_service.model.entities.Post;
import com.social.microservices.iam_service.model.request.post.PostRequest;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, imports = {
        DateTimeUtils.class, Object.class })
public interface PostMapper {
    @Mapping(source = "id", target = "id")
    @Mapping(source = "title", target = "title")
    @Mapping(source = "content", target = "content")
    @Mapping(source = "likes", target = "likes")
    @Mapping(source = "created", target = "created", dateFormat = "yyyy-MM--dd'T'HH:mm:ss")
    PostDTO toPostDTO(Post post);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "created", ignore = true)
    Post createPost(PostRequest postRequest);
}
