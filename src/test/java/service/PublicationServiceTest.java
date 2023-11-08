package service;

import com.example.gogotrips.publication.domain.entity.Publication;
import com.example.gogotrips.publication.domain.persistence.PublicationRepository;
import com.example.gogotrips.publication.domain.service.PublicationServiceImpl;
import com.example.gogotrips.publication.mappers.PublicationMapper;
import com.example.gogotrips.publication.resource.PublicationResource;
import com.example.gogotrips.publication.resource.PublicationResponseResource;
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

public class PublicationServiceTest {
    @InjectMocks
    private PublicationServiceImpl publicationService;

    @Mock
    private PublicationRepository publicationRepository;

    @Mock
    private PublicationMapper publicationMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllPublications() {
        // Arrange
        List<Publication> Publications = new ArrayList<>();
        List<PublicationResponseResource> publicationResponseResources = new ArrayList<>();

        when(publicationRepository.findAll()).thenReturn(Publications);
        when(publicationMapper.entityListToResponseResourceList(Publications)).thenReturn(publicationResponseResources);

        // Act
        List<PublicationResponseResource> result = publicationService.getAllPublications();

        // Assert
        assertEquals(publicationResponseResources, result);
    }

    @Test
    public void testGetPublicationById() {
        // Arrange
        Long PublicationId = 1L;
        Publication publication = new Publication();
        PublicationResponseResource responseDto = new PublicationResponseResource();

        when(publicationRepository.findById(PublicationId)).thenReturn(Optional.of(publication));
        when(publicationMapper.entityToResponseResource(publication)).thenReturn(responseDto);

        // Act
        PublicationResponseResource result = publicationService.getPublicationById(PublicationId);

        // Assert
        assertEquals(responseDto, result);
    }

    @Test
    public void testGetPublicationByIdNotFound() {
        // Arrange
        Long nonExistentPublicationId = 999L;

        when(publicationRepository.findById(nonExistentPublicationId)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> {
            publicationService.getPublicationById(nonExistentPublicationId);
        });
    }

    @Test
    public void testCreatePublication() {
        // Arrange
        PublicationResource publicationResource = new PublicationResource();
        publicationResource.setTitle("Nombre de la Publication");
        publicationResource.setContent("Contenido");

        Publication publication = new Publication();
        publicationResource.setTitle(publicationResource.getTitle());
        publicationResource.setContent(publicationResource.getContent());

        PublicationResponseResource publicationResponseResource = new PublicationResponseResource();
        publicationResponseResource.setId(1L);
        publicationResponseResource.setTitle(publicationResponseResource.getTitle());
        publicationResponseResource.setContent(publicationResponseResource.getContent());

        when(publicationMapper.resourceToEntity(publicationResource)).thenReturn(publication);
        when(publicationRepository.save(publication)).thenReturn(publication);
        when(publicationMapper.entityToResponseResource(publication)).thenReturn(publicationResponseResource);

        // Act
        PublicationResponseResource result = publicationService.createPublication(publicationResource);

        // Assert
        assertEquals(publicationResponseResource, result);
    }


    @Test
    public void testUpdatePublication() {//Edit
        // Arrange
        Long PublicationId = 1L;
        PublicationResource publicationIdDto = new PublicationResource();
        Publication publication = new Publication();
        PublicationResponseResource responseDto = new PublicationResponseResource();

        publication.setContent("New Content");
        publication.setTitle("New Title");

        // Configura el comportamiento esperado de los mocks
        when(publicationRepository.findById(PublicationId)).thenReturn(Optional.of(publication));
        when(publicationRepository.save(any())).thenReturn(publication); // Cambia esto si es necesario
        when(publicationMapper.entityToResponseResource(publication)).thenReturn(responseDto);

        // Act
        PublicationResponseResource result = publicationService.updatePublication(PublicationId, publicationIdDto);

        // Assert
        assertNotNull(result); // Asegúrate de que result no sea nulo
        assertEquals(responseDto, result); // Asegúrate de que result sea igual a responseDto
    }

    @Test
    public void testUpdatePublicationNotFound() {
        // Arrange
        Long nonExistentPublicationId = 999L;
        PublicationResource publicationDto = new PublicationResource();
        publicationDto.setTitle("UpdatedTitle");
        publicationDto.setContent("UpdatedContent");

        when(publicationRepository.findById(nonExistentPublicationId)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> {
            publicationService.updatePublication(nonExistentPublicationId, publicationDto);
        });
    }

    @Test
    public void testDeletePublication() {
        // Arrange
        Long PublicationId = 1L;

        when(publicationRepository.existsById(PublicationId)).thenReturn(true);

        // Act
        publicationService.deletePublication(PublicationId);

        // Assert
        verify(publicationRepository, times(1)).deleteById(PublicationId);
    }

    @Test
    public void testDeletePublicationNotFound() {
        // Arrange
        Long nonExistentPublicationId = 999L;

        when(publicationRepository.existsById(nonExistentPublicationId)).thenReturn(false);

        // Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> {
            publicationService.deletePublication(nonExistentPublicationId);
        });
    }
}
