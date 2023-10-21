package com.example.gogotrips.comment.domain.service;

import com.example.gogotrips.comment.resource.CommentResource;
import com.example.gogotrips.comment.resource.CommentResponseResource;

import java.util.List;

public interface CommentService {
    List<CommentResponseResource> getAllComments();
    CommentResponseResource getCommentById(Long commentId);
    CommentResponseResource createComment(CommentResource commentResource);
    CommentResponseResource updateComment(Long commentId, CommentResource commentResource);
    void deleteComment(Long commentId);
}
