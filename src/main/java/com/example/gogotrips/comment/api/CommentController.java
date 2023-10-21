package com.example.gogotrips.comment.api;

import com.example.gogotrips.comment.domain.service.CommentService;
import com.example.gogotrips.comment.resource.CommentResource;
import com.example.gogotrips.comment.resource.CommentResponseResource;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<CommentResponseResource> createComment(@Valid @RequestBody CommentResource commentResource) {
        CommentResponseResource commentResponseResource = commentService.createComment(commentResource);
        return new ResponseEntity<>(commentResponseResource, HttpStatus.CREATED);
    }

    @GetMapping("/{commentId}")
    public ResponseEntity<CommentResponseResource> getCommentById(@PathVariable Long commentId) {
        CommentResponseResource commentResponseResource = commentService.getCommentById(commentId);
        return new ResponseEntity<>(commentResponseResource, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<CommentResponseResource>> getAllComments() {
        List<CommentResponseResource> commentResponseResources = commentService.getAllComments();
        return new ResponseEntity<>(commentResponseResources, HttpStatus.OK);
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<CommentResponseResource> updateComment(
            @PathVariable Long commentId,
            @Valid @RequestBody CommentResource commentResource) {
        CommentResponseResource commentResponseResource = commentService.updateComment(commentId, commentResource);
        return new ResponseEntity<>(commentResponseResource, HttpStatus.OK);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
