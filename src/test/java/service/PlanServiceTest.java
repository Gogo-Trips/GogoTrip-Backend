package service;

import com.example.gogotrips.plan.domain.entity.Plan;
import com.example.gogotrips.plan.domain.persistence.PlanRepository;
import com.example.gogotrips.plan.domain.service.PlanServiceImpl;
import com.example.gogotrips.plan.mappers.PlanMapper;
import com.example.gogotrips.plan.resource.PlanResource;
import com.example.gogotrips.plan.resource.PlanResponseResource;
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

public class PlanServiceTest {
    @InjectMocks
    private PlanServiceImpl planService;

    @Mock
    private PlanRepository planRepository;

    @Mock
    private PlanMapper planMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllPlans() {
        // Arrange
        List<Plan> plans = new ArrayList<>();
        List<PlanResponseResource> planResponseResources = new ArrayList<>();

        when(planRepository.findAll()).thenReturn(plans);
        when(planMapper.entityListToResponseResourceList(plans)).thenReturn(planResponseResources);

        // Act
        List<PlanResponseResource> result = planService.getAllPlans();

        // Assert
        assertEquals(planResponseResources, result);
    }

    @Test
    public void testGetPLanById() {
        // Arrange
        Long planId = 1L;
        Plan pLan = new Plan();
        PlanResponseResource responseDto = new PlanResponseResource();

        when(planRepository.findById(planId)).thenReturn(Optional.of(pLan));
        when(planMapper.entityToResponseResource(pLan)).thenReturn(responseDto);

        // Act
        PlanResponseResource result = planService.getPlanById(planId);

        // Assert
        assertEquals(responseDto, result);
    }

    @Test
    public void testGetPLanByIdNotFound() {
        // Arrange
        Long nonExistentPlanId = 999L;

        when(planRepository.findById(nonExistentPlanId)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> {
            planService.getPlanById(nonExistentPlanId);
        });
    }

    @Test
    public void testCreatePlan() {
        // Arrange
        PlanResource planResource = new PlanResource();
        planResource.setName("Nombre del Plan");
        planResource.setDescription("Description");

        Plan plan = new Plan();
        planResource.setName(planResource.getName());
        planResource.setDescription(planResource.getDescription());

        PlanResponseResource planResponseResource = new PlanResponseResource();
        planResponseResource.setId(1L);
        planResponseResource.setName(planResponseResource.getName());
        planResponseResource.setDescription(planResponseResource.getDescription());

        when(planMapper.resourceToEntity(planResource)).thenReturn(plan);
        when(planRepository.save(plan)).thenReturn(plan);
        when(planMapper.entityToResponseResource(plan)).thenReturn(planResponseResource);

        // Act
        PlanResponseResource result = planService.createPlan(planResource);

        // Assert
        assertEquals(planResponseResource, result);
    }


    @Test
    public void testUpdatePLan() {//Edit
        // Arrange
        Long planId = 1L;
        PlanResource planIdDto = new PlanResource();
        Plan plan = new Plan();
        PlanResponseResource responseDto = new PlanResponseResource();

        plan.setName("New Name");
        plan.setDescription("New Description");

        // Configura el comportamiento esperado de los mocks
        when(planRepository.findById(planId)).thenReturn(Optional.of(plan));
        when(planRepository.save(any())).thenReturn(plan); // Cambia esto si es necesario
        when(planMapper.entityToResponseResource(plan)).thenReturn(responseDto);

        // Act
        PlanResponseResource result = planService.updatePlan(planId, planIdDto);

        // Assert
        assertNotNull(result); // Asegúrate de que result no sea nulo
        assertEquals(responseDto, result); // Asegúrate de que result sea igual a responseDto
    }

    @Test
    public void testUpdatePlanNotFound() {
        // Arrange
        Long nonExistentPlanId = 999L;
        PlanResource planDto = new PlanResource();
        planDto.setName("UpdatedName");
        planDto.setDescription("UpdatedDescription");

        when(planRepository.findById(nonExistentPlanId)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> {
            planService.updatePlan(nonExistentPlanId, planDto);
        });
    }

    @Test
    public void testDeletePlan() {
        // Arrange
        Long planId = 1L;

        when(planRepository.existsById(planId)).thenReturn(true);

        // Act
        planService.deletePlan(planId);

        // Assert
        verify(planRepository, times(1)).deleteById(planId);
    }

    @Test
    public void testDeletePlanNotFound() {
        // Arrange
        Long nonExistentPlanId = 999L;

        when(planRepository.existsById(nonExistentPlanId)).thenReturn(false);

        // Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> {
            planService.deletePlan(nonExistentPlanId);
        });
    }
}
