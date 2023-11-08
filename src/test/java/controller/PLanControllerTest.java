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

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
    public void testCreatePlan() throws Exception {
        PlanResource planResource = new PlanResource();

        PlanResponseResource planResponseResource = new PlanResponseResource();

        when(planService.createPlan(planResource)).thenReturn(planResponseResource);

        ResponseEntity<PlanResponseResource> responseEntity = plansController.createPLan(planResource);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(planResponseResource, responseEntity.getBody());
    }

    @Test
    public void testGetPlanById() {
        Long planId = 1L;
        PlanResponseResource planResponseResource = new PlanResponseResource();

        when(planService.getPlanById(planId)).thenReturn(planResponseResource);

        ResponseEntity<PlanResponseResource> responseEntity = plansController.getPlanById(planId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(planResponseResource, responseEntity.getBody());
    }

    @Test
    public void testGetAllPLans() {
        List<PlanResponseResource> responseDtoList = new ArrayList<>();

        when(planService.getAllPlans()).thenReturn(responseDtoList);

        ResponseEntity<List<PlanResponseResource>> responseEntity = plansController.getAllplans();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(responseDtoList, responseEntity.getBody());
    }

    @Test
    public void testUpdatePLan() {
        Long planId = 1L;
        PlanResource planResource = new PlanResource();

        PlanResponseResource planDto = new PlanResponseResource();

        when(planService.updatePlan(planId, planResource)).thenReturn(planDto);

        ResponseEntity<PlanResponseResource> responseEntity = plansController.updatePlan(planId, planResource);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(planDto, responseEntity.getBody());
    }

    @Test
    public void testDeletePLan() {
        Long planId = 1L;

        ResponseEntity<Void> responseEntity = plansController.deletePlan(planId);

        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
        verify(planService, times(1)).deletePlan(planId);
    }
}
