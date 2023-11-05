package controller;

import com.example.gogotrips.businessman.api.BusinessmanController;
import com.example.gogotrips.businessman.domain.service.BusinessmanService;
import com.example.gogotrips.businessman.resource.BusinessmanResource;
import com.example.gogotrips.businessman.resource.BusinessmanResponseResource;
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

public class BusinessmanControllerTest {
    @InjectMocks
    private BusinessmanController businessmanController;

    @Mock
    private BusinessmanService businessmanService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateBusinessman() throws Exception {
        BusinessmanResource businessmanResource = new BusinessmanResource();

        BusinessmanResponseResource businessmanResponseResource = new BusinessmanResponseResource();

        when(businessmanService.createBusinessman(businessmanResource)).thenReturn(businessmanResponseResource);

        ResponseEntity<BusinessmanResponseResource> responseEntity = businessmanController.createBusinessman(businessmanResource);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(businessmanResponseResource, responseEntity.getBody());
    }

    @Test
    public void testGetBusinessmanById() throws Exception {
        Long businessmanId = 1L;
        BusinessmanResponseResource businessmanResponseResource = new BusinessmanResponseResource();

        when(businessmanService.getBusinessmanById(businessmanId)).thenReturn(businessmanResponseResource);

        ResponseEntity<BusinessmanResponseResource> responseEntity = businessmanController.getBusinessmanById(businessmanId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(businessmanResponseResource, responseEntity.getBody());
    }

    @Test
    public void testGetAllBusinessmen() throws Exception {
        List<BusinessmanResponseResource> responseDtoList = new ArrayList<>();

        when(businessmanService.getAllBusinessmen()).thenReturn(responseDtoList);

        ResponseEntity<List<BusinessmanResponseResource>> responseEntity = businessmanController.getAllBusinessmen();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(responseDtoList, responseEntity.getBody());
    }

    @Test
    public void testUpdateBusinessman() throws Exception {
        Long businessmanId = 1L;
        BusinessmanResource businessmanResource = new BusinessmanResource();

        BusinessmanResponseResource responseDto = new BusinessmanResponseResource();

        when(businessmanService.updateBusinessman(businessmanId, businessmanResource)).thenReturn(responseDto);

        ResponseEntity<BusinessmanResponseResource> responseEntity = businessmanController.updateBusinessman(businessmanId, businessmanResource);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(responseDto, responseEntity.getBody());
    }

    @Test
    public void testDeleteBusinessman() throws Exception {
        Long businessmanId = 1L;

        ResponseEntity<Void> responseEntity = businessmanController.deleteBusinessman(businessmanId);

        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
        verify(businessmanService, times(1)).deleteBusinessman(businessmanId);
    }
}
