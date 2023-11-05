package controller;

import com.example.gogotrips.comment.api.CommentController;
import com.example.gogotrips.comment.domain.service.CommentServicelmpl;
import com.example.gogotrips.comment.resource.CommentResource;
import com.example.gogotrips.comment.resource.CommentResponseResource;
import com.example.gogotrips.plan.api.PlansController;
import com.example.gogotrips.plan.domain.service.PlanServiceImpl;
import com.example.gogotrips.plan.resource.PlanResource;
import com.example.gogotrips.plan.resource.PlanResponseResource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.when;

public class CommentControllerTest {
    @InjectMocks
    private CommentController commentController;

    @Mock
    private CommentServicelmpl commentServicelmpl;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateComment() {
        // Mock input data
        Long commentId = 1L;
        CommentResource commentDto = new CommentResource();
        commentDto.setContenido("Que buena recomendacion");
        commentDto.setCalificacion(10);

        // Mock the response from the service
        CommentResponseResource mockResponse = new CommentResponseResource();
        mockResponse.setId(commentId);
        mockResponse.setContenido(commentDto.getContenido());
        mockResponse.setCalificacion(commentDto.getCalificacion());

        when(commentServicelmpl.createComment(commentDto)).thenReturn(mockResponse);

        ResponseEntity<CommentResponseResource> responseEntity = commentController.createComment(commentDto);

        // Assertions
        assert responseEntity.getStatusCode() == HttpStatus.CREATED;
        assert responseEntity.getBody() != null;
        assert responseEntity.getBody().getId() != null;
        // Add more assertions as needed
    }
}
