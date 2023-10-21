package com.example.gogotrips.comment.mappers;

import com.example.gogotrips.comment.domain.entity.Comment;
import com.example.gogotrips.comment.resource.CommentResource;
import com.example.gogotrips.comment.resource.CommentResponseResource;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CommentMapper {
    private final ModelMapper modelMapper;

    public CommentMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Comment resourceToEntity(CommentResource commentResource) {
        return modelMapper.map(commentResource, Comment.class);
    }

    public CommentResource entityToResource(Comment comment) {
        return modelMapper.map(comment, CommentResource.class);
    }

    public CommentResponseResource entityToResponseResource(Comment comment) {
        return modelMapper.map(comment, CommentResponseResource.class);
    }

    public List<Comment> resourceListToEntityList(List<CommentResource> commentResources) {
        return commentResources
                .stream()
                .map(this::resourceToEntity)
                .toList();
    }

    public List<CommentResource> entityListToResourceList(List<Comment> comments) {
        return comments
                .stream()
                .map(this::entityToResource)
                .toList();
    }

    public List<CommentResponseResource> entityListToResponseResourceList(List<Comment> comments) {
        return comments
                .stream()
                .map(this::entityToResponseResource)
                .toList();
    }
}
