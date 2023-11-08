package controller;

import com.example.gogotrips.publication.api.PublicationsController;
import com.example.gogotrips.publication.domain.service.PublicationServiceImpl;
import com.example.gogotrips.publication.resource.PublicationResource;
import com.example.gogotrips.publication.resource.PublicationResponseResource;
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

public class PublicationControllerTest {
    @InjectMocks
    private PublicationsController publicationsController;

    @Mock
    private PublicationServiceImpl publicationService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreatePublication() throws Exception {
        PublicationResource publicationResource = new PublicationResource();

        PublicationResponseResource publicationResponseResource = new PublicationResponseResource();

        when(publicationService.createPublication(publicationResource)).thenReturn(publicationResponseResource);

        ResponseEntity<PublicationResponseResource> responseEntity = publicationsController.createPublication(publicationResource);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(publicationResponseResource, responseEntity.getBody());
    }

    @Test
    public void testGetPublicationById() {
        Long PublicationId = 1L;
        PublicationResponseResource publicationResponseResource = new PublicationResponseResource();

        when(publicationService.getPublicationById(PublicationId)).thenReturn(publicationResponseResource);

        ResponseEntity<PublicationResponseResource> responseEntity = publicationsController.getPublicationById(PublicationId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(publicationResponseResource, responseEntity.getBody());
    }

    @Test
    public void testGetAllPublications() {
        List<PublicationResponseResource> responseDtoList = new ArrayList<>();

        when(publicationService.getAllPublications()).thenReturn(responseDtoList);

        ResponseEntity<List<PublicationResponseResource>> responseEntity = publicationsController.getAllPublications();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(responseDtoList, responseEntity.getBody());
    }

    @Test
    public void testUpdatePublication() {
        Long PublicationId = 1L;
        PublicationResource publicationResource = new PublicationResource();

        PublicationResponseResource PublicationDto = new PublicationResponseResource();

        when(publicationService.updatePublication(PublicationId, publicationResource)).thenReturn(PublicationDto);

        ResponseEntity<PublicationResponseResource> responseEntity = publicationsController.updatePublication(PublicationId, publicationResource);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(PublicationDto, responseEntity.getBody());
    }

    @Test
    public void testDeletePublication() {
        Long PublicationId = 1L;

        ResponseEntity<Void> responseEntity = publicationsController.deletePublication(PublicationId);

        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
        verify(publicationService, times(1)).deletePublication(PublicationId);
    }
}
