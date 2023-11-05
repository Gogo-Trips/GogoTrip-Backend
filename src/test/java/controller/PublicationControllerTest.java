package controller;

import com.example.gogotrips.plan.api.PlansController;
import com.example.gogotrips.plan.domain.service.PlanServiceImpl;
import com.example.gogotrips.plan.resource.PlanResource;
import com.example.gogotrips.plan.resource.PlanResponseResource;
import com.example.gogotrips.publication.api.PublicationsController;
import com.example.gogotrips.publication.domain.service.PublicationService;
import com.example.gogotrips.publication.mappers.PublicationMapper;
import com.example.gogotrips.publication.resource.PublicationResource;
import com.example.gogotrips.publication.resource.PublicationResponseResource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.when;

public class PublicationControllerTest {
    @InjectMocks
    private PublicationsController publicationsController;

    @Mock
    private PublicationService publicationService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreatePublication() {
        // Mock input data
        Long publicationId = 1L;
        PublicationResource publicationResource = new PublicationResource();
        publicationResource.setTitle("Mis gustos del hotel Paris");
        publicationResource.setContent("Es muy bonito y acojedor");

        // Mock the response from the service
        PublicationResponseResource mockResponse = new PublicationResponseResource();
        mockResponse.setId(publicationId);
        mockResponse.setTitle(publicationResource.getTitle());
        mockResponse.setContent(publicationResource.getContent());

        when(publicationService.createPublication(
                publicationResource
        )).thenReturn(mockResponse);

        ResponseEntity<PublicationResponseResource> responseEntity = publicationsController
                .createPublication(publicationResource);

        assert responseEntity.getStatusCode() == HttpStatus.CREATED;
        assert responseEntity.getBody() != null;
        assert responseEntity.getBody().getId() != null;
        // Add more assertions as needed
    }
}
