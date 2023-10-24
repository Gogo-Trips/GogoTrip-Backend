package com.example.gogotrips.comment.domain.service;

import com.example.gogotrips.comment.domain.entity.Comment;
import com.example.gogotrips.comment.domain.persistence.CommentRepository;
import com.example.gogotrips.comment.mappers.CommentMapper;
import com.example.gogotrips.comment.resource.CommentResource;
import com.example.gogotrips.comment.resource.CommentResponseResource;
import com.example.gogotrips.shared.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentServicelmpl implements CommentService {
    private final CommentRepository commentRepository;

    private final CommentMapper commentMapper;

    @Override
    public List<CommentResponseResource> getAllComments() {
        List<Comment> comments = commentRepository.findAll();
        return commentMapper.entityListToResponseResourceList(comments);
    }

    @Override
    public CommentResponseResource getCommentById(Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment not found with id: " + commentId));
        return commentMapper.entityToResponseResource(comment);
    }

    @Override
    public CommentResponseResource createComment(CommentResource commentResource) {
        Comment comment = commentMapper.resourceToEntity(commentResource);
        comment = commentRepository.save(comment);

        return commentMapper.entityToResponseResource(comment);
    }

    @Override
    public CommentResponseResource updateComment(Long commentId, CommentResource commentResource) {
        Optional<Comment> optionalComment = commentRepository.findById(commentId);

        if (optionalComment.isPresent()) {
            Comment comment = optionalComment.get();

            comment.setContenido(commentResource.getContenido());
            comment.setCalificacion(commentResource.getCalificacion());

            comment = commentRepository.save(comment);
            return commentMapper.entityToResponseResource(comment);
        } else {
            throw new ResourceNotFoundException("Comment not found with id: " + commentId);
        }
    }

    @Override
    public void deleteComment(Long commentId) {
        if (!commentRepository.existsById(commentId)) {
            throw new ResourceNotFoundException("Comment not found with id: " + commentId);
        }

        commentRepository.deleteById(commentId);
    }

}
