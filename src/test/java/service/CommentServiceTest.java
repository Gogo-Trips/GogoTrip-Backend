package service;

import com.example.gogotrips.comment.domain.entity.Comment;
import com.example.gogotrips.comment.domain.persistence.CommentRepository;
import com.example.gogotrips.comment.domain.service.CommentServicelmpl;
import com.example.gogotrips.comment.mappers.CommentMapper;
import com.example.gogotrips.comment.resource.CommentResource;
import com.example.gogotrips.comment.resource.CommentResponseResource;
import com.example.gogotrips.shared.exception.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

public class CommentServiceTest {
    @InjectMocks
    private CommentServicelmpl commentServicelmpl;

    @Mock
    private CommentRepository commentRepository;

    @Mock
    private CommentMapper commentMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllComments() {
        // Arrange
        List<Comment> comments = new ArrayList<>();
        List<CommentResponseResource> commentResponseResources = new ArrayList<>();

        when(commentRepository.findAll()).thenReturn(comments);
        when(commentMapper.entityListToResponseResourceList(comments)).thenReturn(commentResponseResources);

        // Act
        List<CommentResponseResource> result = commentServicelmpl.getAllComments();

        // Assert
        assertEquals(commentResponseResources, result);
    }

    @Test
    public void testGetCommentById() {
        // Arrange
        Long commentId = 1L;
        Comment comment = new Comment();
        CommentResponseResource responseDto = new CommentResponseResource();

        when(commentRepository.findById(commentId)).thenReturn(Optional.of(comment));
        when(commentMapper.entityToResponseResource(comment)).thenReturn(responseDto);

        // Act
        CommentResponseResource result = commentServicelmpl.getCommentById(commentId);

        // Assert
        assertEquals(responseDto, result);
    }

    @Test
    public void testGetCommentByIdNotFound() {
        // Arrange
        Long nonExistentCommentId = 999L;

        when(commentRepository.findById(nonExistentCommentId)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> {
            commentServicelmpl.getCommentById(nonExistentCommentId);
        });
    }

    @Test
    public void testCreateComment() {
        // Arrange
        CommentResource commentResource = new CommentResource();
        commentResource.setContenido("Que bonito Lugar");
        commentResource.setCalificacion(5);

        Comment comment = new Comment();
        comment.setContenido(comment.getContenido());
        comment.setCalificacion(comment.getCalificacion());

        CommentResponseResource commentResponseResource = new CommentResponseResource();
        commentResponseResource.setId(1L);
        commentResponseResource.setContenido(commentResponseResource.getContenido());
        commentResponseResource.setCalificacion(commentResponseResource.getCalificacion());

        when(commentMapper.resourceToEntity(commentResource)).thenReturn(comment);
        when(commentRepository.save(comment)).thenReturn(comment);
        when(commentMapper.entityToResponseResource(comment)).thenReturn(commentResponseResource);

        // Act
        CommentResponseResource result = commentServicelmpl.createComment(commentResource);

        // Assert
        assertEquals(commentResponseResource, result);
    }


    @Test
    public void testUpdateComment() {//Edit
        // Arrange
        Long commentId = 1L;
        CommentResource commentIdDto = new CommentResource();
        Comment comment = new Comment();
        CommentResponseResource responseDto = new CommentResponseResource();

        comment.setContenido("No me gusta");
        comment.setCalificacion(10);

        // Configura el comportamiento esperado de los mocks
        when(commentRepository.findById(commentId)).thenReturn(Optional.of(comment));
        when(commentRepository.save(any())).thenReturn(comment); // Cambia esto si es necesario
        when(commentMapper.entityToResponseResource(comment)).thenReturn(responseDto);

        // Act
        CommentResponseResource result = commentServicelmpl.updateComment(commentId, commentIdDto);

        // Assert
        assertNotNull(result); // Asegúrate de que result no sea nulo
        assertEquals(responseDto, result); // Asegúrate de que result sea igual a responseDto
    }

    @Test
    public void testUpdateCommentNotFound() {
        // Arrange
        Long nonExistentCommentId = 999L;
        CommentResource commentDto = new CommentResource();
        commentDto.setContenido("UpdatedContent");
        commentDto.setCalificacion(0);

        when(commentRepository.findById(nonExistentCommentId)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> {
            commentServicelmpl.updateComment(nonExistentCommentId, commentDto);
        });
    }

    @Test
    public void testDeleteComment() {
        // Arrange
        Long commentId = 1L;

        when(commentRepository.existsById(commentId)).thenReturn(true);

        // Act
        commentServicelmpl.deleteComment(commentId);

        // Assert
        verify(commentRepository, times(1)).deleteById(commentId);
    }

    @Test
    public void testDeleteCommentNotFound() {
        // Arrange
        Long nonExistentCommentId = 999L;

        when(commentRepository.existsById(nonExistentCommentId)).thenReturn(false);

        // Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> {
            commentServicelmpl.deleteComment(nonExistentCommentId);
        });
    }
}
