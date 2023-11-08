package controller;

import com.example.gogotrips.plandetails.api.PlanDetailsController;
import com.example.gogotrips.plandetails.domain.service.PlanDetailsService;
import com.example.gogotrips.plandetails.resource.PlanDetailsResource;
import com.example.gogotrips.plandetails.resource.PlanDetailsResponseResource;
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

public class PlanDetailsControllerTest {
    @InjectMocks
    private PlanDetailsController planDetailsController;

    @Mock
    private PlanDetailsService planDetailsService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreatePlanDetails() throws Exception {
        PlanDetailsResource planDetailsResource = new PlanDetailsResource();

        PlanDetailsResponseResource planDetailsResponseResource = new PlanDetailsResponseResource();

        when(planDetailsService.createPlanDetails(planDetailsResource)).thenReturn(planDetailsResponseResource);

        ResponseEntity<PlanDetailsResponseResource> responseEntity = planDetailsController.createPlanDetails(planDetailsResource);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(planDetailsResponseResource, responseEntity.getBody());
    }

    @Test
    public void testGetPlanDetailsById() {
        Long planDestailsId = 1L;
        PlanDetailsResponseResource planDetailsResponseResource = new PlanDetailsResponseResource();

        when(planDetailsService.getPlanDetailsById(planDestailsId)).thenReturn(planDetailsResponseResource);

        ResponseEntity<PlanDetailsResponseResource> responseEntity = planDetailsController.getPlanDetailsById(planDestailsId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(planDetailsResponseResource, responseEntity.getBody());
    }

    @Test
    public void testGetAllPLansDetails() {
        List<PlanDetailsResponseResource> responseDtoList = new ArrayList<>();

        when(planDetailsService.getAllPlanDetails()).thenReturn(responseDtoList);

        ResponseEntity<List<PlanDetailsResponseResource>> responseEntity = planDetailsController.getAllPlanDetailss();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(responseDtoList, responseEntity.getBody());
    }

    @Test
    public void testUpdatePLanDetails() {
        Long planDetailsId = 1L;
        PlanDetailsResource planDetailsResource = new PlanDetailsResource();

        PlanDetailsResponseResource planDto = new PlanDetailsResponseResource();

        when(planDetailsService.updatePlanDetails(planDetailsId, planDetailsResource)).thenReturn(planDto);

        ResponseEntity<PlanDetailsResponseResource> responseEntity = planDetailsController.updatePlanDetails(planDetailsId, planDetailsResource);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(planDto, responseEntity.getBody());
    }

    @Test
    public void testDeletePLanDetails() {
        Long planDetailsId = 1L;

        ResponseEntity<Void> responseEntity = planDetailsController.deletePlanDetails(planDetailsId);

        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
        verify(planDetailsService, times(1)).deletePlanDetails(planDetailsId);
    }
}

