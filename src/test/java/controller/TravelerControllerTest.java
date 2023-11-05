package controller;

import com.example.gogotrips.traveler.api.TravelersController;
import com.example.gogotrips.traveler.domain.service.TravelerService;
import com.example.gogotrips.traveler.domain.service.TravelerServiceImpl;
import com.example.gogotrips.traveler.resource.TravelerResource;
import com.example.gogotrips.traveler.resource.TravelerResponseResource;
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

public class TravelerControllerTest {
    @InjectMocks
    private TravelersController travelersController;

    @Mock
    private TravelerServiceImpl travelerService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateTraveler() throws Exception {
        TravelerResource travelerResource = new TravelerResource();

        TravelerResponseResource travelerResponseResource = new TravelerResponseResource();

        when(travelerService.createTraveler(travelerResource)).thenReturn(travelerResponseResource);

        ResponseEntity<TravelerResponseResource> responseEntity = travelersController.createTraveler(travelerResource);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(travelerResponseResource, responseEntity.getBody());
    }

    @Test
    public void testGetTravelerById() throws Exception {
        Long travelerId = 1L;
        TravelerResponseResource travelerResponseResource = new TravelerResponseResource();
        // Definir cualquier objeto necesario en responseDto

        when(travelerService.getTravelerById(travelerId)).thenReturn(travelerResponseResource);

        ResponseEntity<TravelerResponseResource> responseEntity = travelersController.getTravelerById(travelerId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(travelerResponseResource, responseEntity.getBody());
    }

    @Test
    public void testGetAllTravelers() throws Exception {
        List<TravelerResponseResource> responseDtoList = new ArrayList<>();

        when(travelerService.getAllTravelers()).thenReturn(responseDtoList);

        ResponseEntity<List<TravelerResponseResource>> responseEntity = travelersController.getAllTravelers();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(responseDtoList, responseEntity.getBody());
    }

    @Test
    public void testUpdateTraveler() throws Exception {
        Long travelerId = 1L;
        TravelerResource travelerResource = new TravelerResource();

        TravelerResponseResource responseDto = new TravelerResponseResource();

        when(travelerService.updateTraveler(travelerId, travelerResource)).thenReturn(responseDto);

        ResponseEntity<TravelerResponseResource> responseEntity = travelersController.updateTraveler(travelerId, travelerResource);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(responseDto, responseEntity.getBody());
    }

    @Test
    public void testDeleteTraveler() throws Exception {
        Long travelerId = 1L;

        ResponseEntity<Void> responseEntity = travelersController.deleteTraveler(travelerId);

        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
        verify(travelerService, times(1)).deleteTraveler(travelerId);
    }

}
