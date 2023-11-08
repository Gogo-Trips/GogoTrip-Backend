package controller;

import com.example.gogotrips.comment.api.CommentController;
import com.example.gogotrips.comment.domain.service.CommentServicelmpl;
import com.example.gogotrips.comment.resource.CommentResource;
import com.example.gogotrips.comment.resource.CommentResponseResource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CommentControllerTest {
    @InjectMocks
    private CommentController commentController;

    @Mock
    private CommentServicelmpl commentService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateComment() throws Exception {
        CommentResource commentResource = new CommentResource();

        CommentResponseResource commentResponseResource = new CommentResponseResource();

        when(commentService.createComment(commentResource)).thenReturn(commentResponseResource);

        ResponseEntity<CommentResponseResource> responseEntity = commentController.createComment(commentResource);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(commentResponseResource, responseEntity.getBody());
    }

    @Test
    public void testGetCommentById() {
        Long commentId = 1L;
        CommentResponseResource commentResponseResource = new CommentResponseResource();

        when(commentService.getCommentById(commentId)).thenReturn(commentResponseResource);

        ResponseEntity<CommentResponseResource> responseEntity = commentController.getCommentById(commentId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(commentResponseResource, responseEntity.getBody());
    }

    @Test
    public void testGetAllComments() {
        List<CommentResponseResource> responseDtoList = new ArrayList<>();

        when(commentService.getAllComments()).thenReturn(responseDtoList);

        ResponseEntity<List<CommentResponseResource>> responseEntity = commentController.getAllComments();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(responseDtoList, responseEntity.getBody());
    }

    @Test
    public void testUpdateComment() {
        Long commentId = 1L;
        CommentResource commentResource = new CommentResource();

        CommentResponseResource responseDto = new CommentResponseResource();

        when(commentService.updateComment(commentId, commentResource)).thenReturn(responseDto);

        ResponseEntity<CommentResponseResource> responseEntity = commentController.updateComment(commentId, commentResource);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(responseDto, responseEntity.getBody());
    }

    @Test
    public void testDeleteComment() {
        Long commentId = 1L;

        ResponseEntity<Void> responseEntity = commentController.deleteComment(commentId);

        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
        verify(commentService, times(1)).deleteComment(commentId);
    }

}
