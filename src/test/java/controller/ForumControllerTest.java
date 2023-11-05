package controller;

import com.example.gogotrips.forum.api.ForumController;
import com.example.gogotrips.forum.domain.service.ForumService;
import com.example.gogotrips.forum.resource.ForumResource;
import com.example.gogotrips.forum.resource.ForumResponseResource;
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

public class ForumControllerTest {
    @InjectMocks
    private ForumController forumController;

    @Mock
    private ForumService forumService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateForum() {
        // Mock input data
        Long forumId = 1L;
        ForumResource planDto = new ForumResource();
        planDto.setTitle("Estancia en el Hotel Costas del Sol");

        // Mock the response from the service
        ForumResponseResource mockResponse = new ForumResponseResource();
        mockResponse.setId(1L);
        mockResponse.setTitle(planDto.getTitle());

        when(forumService.createForum(
                planDto
        )).thenReturn(mockResponse);

        ResponseEntity<ForumResponseResource> responseEntity = forumController
                .createForum(planDto);

        assert responseEntity.getStatusCode() == HttpStatus.CREATED;
        assert responseEntity.getBody() != null;
        assert responseEntity.getBody().getId() != null;
        // Add more assertions as needed
    }
}
