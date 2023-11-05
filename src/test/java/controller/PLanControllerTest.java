package controller;

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

import static org.mockito.Mockito.*;

public class PLanControllerTest {
    @InjectMocks
    private PlansController plansController;

    @Mock
    private PlanServiceImpl planService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateForum() {
        // Mock input data
        Long forumId = 1L;
        PlanResource planDto = new PlanResource();
        planDto.setName("Hoteles en Bali");
        planDto.setDescription("En este plan encontraras todos nuestros paquetes de hoteles que ofrecemos para ti");

        // Mock the response from the service
        PlanResponseResource mockResponse = new PlanResponseResource();
        mockResponse.setId(forumId);
        mockResponse.setName(planDto.getName());
        mockResponse.setDescription(planDto.getDescription());

        when(planService.createPlan(
                planDto
        )).thenReturn(mockResponse);

        ResponseEntity<PlanResponseResource> responseEntity = plansController
                .createPLan(planDto);

        assert responseEntity.getStatusCode() == HttpStatus.CREATED;
        assert responseEntity.getBody() != null;
        assert responseEntity.getBody().getId() != null;
        // Add more assertions as needed
    }
}
