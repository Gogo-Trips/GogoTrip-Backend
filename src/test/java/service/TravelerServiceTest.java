package service;

import com.example.gogotrips.shared.exception.ResourceAlreadyExistsException;
import com.example.gogotrips.shared.exception.ResourceNotFoundException;
import com.example.gogotrips.traveler.domain.entity.Traveler;
import com.example.gogotrips.traveler.domain.persistence.TravelerRepository;
import com.example.gogotrips.traveler.domain.service.TravelerServiceImpl;
import com.example.gogotrips.traveler.mappers.TravelerMapper;
import com.example.gogotrips.traveler.resource.TravelerResource;
import com.example.gogotrips.traveler.resource.TravelerResponseResource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class TravelerServiceTest {
    @InjectMocks
    private TravelerServiceImpl travelerService;

    @Mock
    private TravelerRepository travelerRepository;

    @Mock
    private TravelerMapper travelerMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllTravelers() {
        // Arrange
        List<Traveler> travelers = new ArrayList<>();
        List<TravelerResponseResource> travelerResponseResources = new ArrayList<>();

        when(travelerRepository.findAll()).thenReturn(travelers);
        when(travelerMapper.entityListToResponseResourceList(travelers)).thenReturn(travelerResponseResources);

        // Act
        List<TravelerResponseResource> result = travelerService.getAllTravelers();

        // Assert
        assertEquals(travelerResponseResources, result);
    }

    @Test
    public void testGetTravelerById() {
        // Arrange
        Long travelerId = 1L;
        Traveler traveler = new Traveler();
        TravelerResponseResource responseDto = new TravelerResponseResource();

        when(travelerRepository.findById(travelerId)).thenReturn(Optional.of(traveler));
        when(travelerMapper.entityToResponseResource(traveler)).thenReturn(responseDto);

        // Act
        TravelerResponseResource result = travelerService.getTravelerById(travelerId);

        // Assert
        assertEquals(responseDto, result);
    }

    @Test
    public void testGetTravelerByIdNotFound() {
        // Arrange
        Long nonExistentTravelerId = 999L;

        when(travelerRepository.findById(nonExistentTravelerId)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> {
            travelerService.getTravelerById(nonExistentTravelerId);
        });
    }

    @Test
    public void testCreateTraveler() {
        // Arrange
        TravelerResource travelerResource = new TravelerResource();
        travelerResource.setName("John");
        travelerResource.setEmail("john.doe@example.com");
        travelerResource.setInformationCard("Visa: 4578 7848 7898 7878");

        Traveler traveler = new Traveler();
        traveler.setName(travelerResource.getName());
        traveler.setEmail(travelerResource.getEmail());
        traveler.setInformationCard(travelerResource.getInformationCard());

        TravelerResponseResource travelerResponseResource = new TravelerResponseResource();
        travelerResponseResource.setId(1L);
        travelerResponseResource.setName(travelerResource.getName());
        travelerResponseResource.setEmail(travelerResource.getEmail());
        travelerResponseResource.setInformationCard(travelerResource.getInformationCard());

        when(travelerRepository.existsByEmail(travelerResource.getEmail())).thenReturn(false);
        when(travelerMapper.resourceToEntity(travelerResource)).thenReturn(traveler);
        when(travelerRepository.save(traveler)).thenReturn(traveler);
        when(travelerMapper.entityToResponseResource(traveler)).thenReturn(travelerResponseResource);

        // Act
        TravelerResponseResource result = travelerService.createTraveler(travelerResource);

        // Assert
        assertEquals(travelerResponseResource, result);
    }


    @Test
    public void testCreateTravelerAlreadyExists() {
        // Arrange
        TravelerResource travelerResource = new TravelerResource();
        travelerResource.setName("John");
        travelerResource.setEmail("john.doe@example.com");
        travelerResource.setInformationCard("Visa: 4578 7848 7898 7878");

        when(travelerRepository.existsByEmail(travelerResource.getEmail())).thenReturn(true);

        // Act and Assert
        assertThrows(ResourceAlreadyExistsException.class, () -> {
            travelerService.createTraveler(travelerResource);
        });
    }


    @Test
    public void testUpdateTraveler() {
        // Arrange
        Long travelerId = 1L;
        TravelerResource travelerIdDto = new TravelerResource();
        Traveler traveler = new Traveler();
        TravelerResponseResource responseDto = new TravelerResponseResource();

        // Configura el objeto Traveler con datos de ejemplo
        traveler.setName("John");
        traveler.setEmail("john.doe@example.com");
        traveler.setInformationCard("Visa: 4578 7848 7898 7878");



        // Configura el comportamiento esperado de los mocks
        when(travelerRepository.findById(travelerId)).thenReturn(Optional.of(traveler));
        when(travelerRepository.save(any())).thenReturn(traveler); // Cambia esto si es necesario
        when(travelerMapper.entityToResponseResource(traveler)).thenReturn(responseDto);

        // Act
        TravelerResponseResource result = travelerService.updateTraveler(travelerId, travelerIdDto);

        // Assert
        assertNotNull(result); // Asegúrate de que result no sea nulo
        assertEquals(responseDto, result); // Asegúrate de que result sea igual a responseDto
    }

    

    @Test
    public void testUpdateTravelerNotFound() {
        // Arrange
        Long nonExistentTravelerId = 999L;
        TravelerResource travelerDto = new TravelerResource();
        travelerDto.setName("UpdatedFirstName");
        travelerDto.setEmail("updated.email@example.com");
        travelerDto.setInformationCard("UpdatedInformationCard");

        when(travelerRepository.findById(nonExistentTravelerId)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> {
            travelerService.updateTraveler(nonExistentTravelerId, travelerDto);
        });
    }

    @Test
    public void testDeleteTraveler() {
        // Arrange
        Long travelerId = 1L;

        when(travelerRepository.existsById(travelerId)).thenReturn(true);

        // Act
        travelerService.deleteTraveler(travelerId);

        // Assert
        verify(travelerRepository, times(1)).deleteById(travelerId);
    }

    @Test
    public void testDeleteTravelerNotFound() {
        // Arrange
        Long nonExistentTravelerId = 999L;

        when(travelerRepository.existsById(nonExistentTravelerId)).thenReturn(false);

        // Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> {
            travelerService.deleteTraveler(nonExistentTravelerId);
        });
    }
}
