package service;

import com.example.gogotrips.plandetails.domain.entity.PlanDetails;
import com.example.gogotrips.plandetails.domain.persistence.PlanDetailsRespository;
import com.example.gogotrips.plandetails.domain.service.PlanDetailsServiceImpl;
import com.example.gogotrips.plandetails.mappers.PlanDetailsMapper;
import com.example.gogotrips.plandetails.resource.PlanDetailsResource;
import com.example.gogotrips.plandetails.resource.PlanDetailsResponseResource;
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

public class PlanDetailsServiceTest {
    @InjectMocks
    private PlanDetailsServiceImpl planDetailsService;

    @Mock
    private PlanDetailsRespository planDetailsRespository;

    @Mock
    private PlanDetailsMapper planDetailsMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllPlans() {
        // Arrange
        List<PlanDetails> planDetails = new ArrayList<>();
        List<PlanDetailsResponseResource> planDetailsResponseResources = new ArrayList<>();

        when(planDetailsRespository.findAll()).thenReturn(planDetails);
        when(planDetailsMapper.entityListToResponseResourceList(planDetails)).thenReturn(planDetailsResponseResources);

        // Act
        List<PlanDetailsResponseResource> result = planDetailsService.getAllPlanDetails();

        // Assert
        assertEquals(planDetailsResponseResources, result);
    }

    @Test
    public void testGetPLanDetailsById() {
        // Arrange
        Long planDetailsId = 1L;
        PlanDetails planDetails = new PlanDetails();
        PlanDetailsResponseResource responseDto = new PlanDetailsResponseResource();

        when(planDetailsRespository.findById(planDetailsId)).thenReturn(Optional.of(planDetails));
        when(planDetailsMapper.entityToResponseResource(planDetails)).thenReturn(responseDto);

        // Act
        PlanDetailsResponseResource result = planDetailsService.getPlanDetailsById(planDetailsId);

        // Assert
        assertEquals(responseDto, result);
    }

    @Test
    public void testGetPLanDetailsByIdNotFound() {
        // Arrange
        Long nonExistentPlanDetailsId = 999L;

        when(planDetailsRespository.findById(nonExistentPlanDetailsId)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> {
            planDetailsService.getPlanDetailsById(nonExistentPlanDetailsId);
        });
    }

    @Test
    public void testCreatePlanDetails() {
        // Arrange
        PlanDetailsResource planDetailsResource = new PlanDetailsResource();
        planDetailsResource.setType("Lugar");
        planDetailsResource.setAmount(5);
        planDetailsResource.setPrice(15.0f);
        planDetailsResource.setAmount(20);

        PlanDetails planDetails = new PlanDetails();
        planDetailsResource.setType(planDetailsResource.getType());
        planDetailsResource.setAmount(planDetailsResource.getAmount());
        planDetailsResource.setPrice(planDetailsResource.getPrice());
        planDetailsResource.setAmount(planDetailsResource.getAmount());

        PlanDetailsResponseResource planDetailsResponseResource = new PlanDetailsResponseResource();
        planDetailsResponseResource.setAmount(planDetailsResponseResource.getAmount());
        planDetailsResponseResource.setPrice(planDetailsResponseResource.getPrice());
        planDetailsResponseResource.setAmount(planDetailsResponseResource.getAmount());

        when(planDetailsMapper.resourceToEntity(planDetailsResource)).thenReturn(planDetails);
        when(planDetailsRespository.save(planDetails)).thenReturn(planDetails);
        when(planDetailsMapper.entityToResponseResource(planDetails)).thenReturn(planDetailsResponseResource);

        // Act
        PlanDetailsResponseResource result = planDetailsService.createPlanDetails(planDetailsResource);

        // Assert
        assertEquals(planDetailsResponseResource, result);
    }


    @Test
    public void testUpdatePlanDetails() {//Edit
        // Arrange
        Long planDetailsId = 1L;
        PlanDetailsResource planDetailsIdDto = new PlanDetailsResource();
        PlanDetails planDetails = new PlanDetails();
        PlanDetailsResponseResource responseDto = new PlanDetailsResponseResource();

        planDetails.setType("New Type");
        planDetails.setAmount(0);
        planDetails.setPrice(0.0f);
        planDetails.setAmount(0);

        // Configura el comportamiento esperado de los mocks
        when(planDetailsRespository.findById(planDetailsId)).thenReturn(Optional.of(planDetails));
        when(planDetailsRespository.save(any())).thenReturn(planDetails); // Cambia esto si es necesario
        when(planDetailsMapper.entityToResponseResource(planDetails)).thenReturn(responseDto);

        // Act
        PlanDetailsResponseResource result = planDetailsService.updatePlanDetails(planDetailsId, planDetailsIdDto);

        // Assert
        assertNotNull(result); // Asegúrate de que result no sea nulo
        assertEquals(responseDto, result); // Asegúrate de que result sea igual a responseDto
    }

    @Test
    public void testUpdatePlanDetailsNotFound() {
        // Arrange
        Long nonExistentPlanDetailsId = 999L;
        PlanDetailsResource planDto = new PlanDetailsResource();
        planDto.setType("UpdatedType");
        planDto.setAmount(0);
        planDto.setPrice(0.0f);
        planDto.setAmount(0);

        when(planDetailsRespository.findById(nonExistentPlanDetailsId)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> {
            planDetailsService.updatePlanDetails(nonExistentPlanDetailsId, planDto);
        });
    }

    @Test
    public void testDeletePlanDetails() {
        // Arrange
        Long planDetailsId = 1L;

        when(planDetailsRespository.existsById(planDetailsId)).thenReturn(true);

        // Act
        planDetailsService.deletePlanDetails(planDetailsId);

        // Assert
        verify(planDetailsRespository, times(1)).deleteById(planDetailsId);
    }

    @Test
    public void testDeletePlanDetailsNotFound() {
        // Arrange
        Long nonExistentPlanDetailsId = 999L;

        when(planDetailsRespository.existsById(nonExistentPlanDetailsId)).thenReturn(false);

        // Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> {
            planDetailsService.deletePlanDetails(nonExistentPlanDetailsId);
        });
    }
}
