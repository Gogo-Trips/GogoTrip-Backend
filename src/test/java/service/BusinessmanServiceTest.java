package service;

import com.example.gogotrips.businessman.domain.entity.Businessman;
import com.example.gogotrips.businessman.domain.persistence.BusinessmanRepository;
import com.example.gogotrips.businessman.domain.service.BusinessmanServicelmpl;
import com.example.gogotrips.businessman.mappers.BusinessmanMapper;
import com.example.gogotrips.businessman.resource.BusinessmanResource;
import com.example.gogotrips.businessman.resource.BusinessmanResponseResource;
import com.example.gogotrips.shared.exception.ResourceAlreadyExistsException;
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

public class BusinessmanServiceTest {
    @InjectMocks
    private BusinessmanServicelmpl businessmanServicelmpl;

    @Mock
    private BusinessmanRepository businessmanRepository;

    @Mock
    private BusinessmanMapper businessmanMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllBusinessmen() {
        // Arrange
        List<Businessman> businessmen = new ArrayList<>();
        List<BusinessmanResponseResource> businessmanResponseResources = new ArrayList<>();

        when(businessmanRepository.findAll()).thenReturn(businessmen);
        when(businessmanMapper.entityListToResponseResourceList(businessmen)).thenReturn(businessmanResponseResources);

        // Act
        List<BusinessmanResponseResource> result = businessmanServicelmpl.getAllBusinessmen();

        // Assert
        assertEquals(businessmanResponseResources, result);
    }

    @Test
    public void testGetBusinessmanById() {
        // Arrange
        Long businessmanId = 1L;
        Businessman businessman = new Businessman();
        BusinessmanResponseResource responseDto = new BusinessmanResponseResource();

        when(businessmanRepository.findById(businessmanId)).thenReturn(Optional.of(businessman));
        when(businessmanMapper.entityToResponseResource(businessman)).thenReturn(responseDto);

        // Act
        BusinessmanResponseResource result = businessmanServicelmpl.getBusinessmanById(businessmanId);

        // Assert
        assertEquals(responseDto, result);
    }

    @Test
    public void testGetBusinessmanByIdNotFound() {
        // Arrange
        Long nonExistentBusinessmanId = 999L;

        when(businessmanRepository.findById(nonExistentBusinessmanId)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> {
            businessmanServicelmpl.getBusinessmanById(nonExistentBusinessmanId);
        });
    }

    @Test
    public void testCreateBusinessman() {
        // Arrange
        BusinessmanResource businessmanResource = new BusinessmanResource();
        businessmanResource.setCompanyName("Hoteles El Encanto");
        businessmanResource.setRuc("123456789");
        businessmanResource.setEmail("hoteleselencanto@gmail.com");
        businessmanResource.setServices("Cuartos, piscinas, fiestas");
        businessmanResource.setPhone(947778498);
        businessmanResource.setDirection("La Molina 292");

        Businessman businessman = new Businessman();
        businessman.setCompanyName(businessmanResource.getCompanyName());
        businessman.setRuc(businessmanResource.getRuc());
        businessman.setEmail(businessmanResource.getEmail());
        businessman.setServices(businessmanResource.getServices());
        businessman.setPhone(businessmanResource.getPhone());
        businessman.setDirection(businessmanResource.getDirection());

        BusinessmanResponseResource businessmanResponseResource = new BusinessmanResponseResource();
        businessmanResponseResource.setId(1L);
        businessmanResponseResource.setCompanyName(businessmanResource.getCompanyName());
        businessmanResponseResource.setEmail(businessmanResource.getEmail());
        businessmanResponseResource.setRuc(businessmanResource.getRuc());
        businessmanResponseResource.setEmail(businessmanResource.getEmail());
        businessmanResponseResource.setServices(businessmanResource.getServices());
        businessmanResponseResource.setPhone(businessmanResource.getPhone());
        businessmanResponseResource.setDirection(businessmanResource.getDirection());

        when(businessmanRepository.existsByEmail(businessmanResource.getEmail())).thenReturn(false);
        when(businessmanMapper.resourceToEntity(businessmanResource)).thenReturn(businessman);
        when(businessmanRepository.save(businessman)).thenReturn(businessman);
        when(businessmanMapper.entityToResponseResource(businessman)).thenReturn(businessmanResponseResource);

        // Act
        BusinessmanResponseResource result = businessmanServicelmpl.createBusinessman(businessmanResource);

        // Assert
        assertEquals(businessmanResponseResource, result);
    }


    @Test
    public void testCreateBusinessmanAlreadyExists() {
        // Arrange
        BusinessmanResource businessmanResource = new BusinessmanResource();
        businessmanResource.setCompanyName("Hoteles El Encanto");
        businessmanResource.setRuc("123456789");
        businessmanResource.setEmail("hoteleselencanto@gmail.com");
        businessmanResource.setServices("Cuartos, piscinas, fiestas");
        businessmanResource.setPhone(947778498);
        businessmanResource.setDirection("La Molina 292");

        when(businessmanRepository.existsByEmail(businessmanResource.getEmail())).thenReturn(true);

        // Act and Assert
        assertThrows(ResourceAlreadyExistsException.class, () -> {
            businessmanServicelmpl.createBusinessman(businessmanResource);
        });
    }


    @Test
    public void testUpdateBusinessman() {
        // Arrange
        Long businessmanId = 1L;
        BusinessmanResource businessmanIdDto = new BusinessmanResource();
        Businessman businessman = new Businessman();
        BusinessmanResponseResource responseDto = new BusinessmanResponseResource();

        businessman.setCompanyName("Hoteles El Encanto");
        businessman.setRuc("123456789");
        businessman.setEmail("hoteleselencanto@gmail.com");
        businessman.setServices("Cuartos, piscinas, fiestas");
        businessman.setPhone(947778498);
        businessman.setDirection("La Molina 292");
        
        // Configura el comportamiento esperado de los mocks
        when(businessmanRepository.findById(businessmanId)).thenReturn(Optional.of(businessman));
        when(businessmanRepository.save(any())).thenReturn(businessman); // Cambia esto si es necesario
        when(businessmanMapper.entityToResponseResource(businessman)).thenReturn(responseDto);

        // Act
        BusinessmanResponseResource result = businessmanServicelmpl.updateBusinessman(businessmanId, businessmanIdDto);

        // Assert
        assertNotNull(result); // Asegúrate de que result no sea nulo
        assertEquals(responseDto, result); // Asegúrate de que result sea igual a responseDto
    }

    @Test
    public void testUpdateBusinessmanNotFound() {
        // Arrange
        Long nonExistentBusinessmanId = 999L;
        BusinessmanResource businessmanDto = new BusinessmanResource();
        businessmanDto.setCompanyName("UpdatedCompanyName");
        businessmanDto.setRuc("UpdatedRuc");
        businessmanDto.setEmail("updated.email@example.com");
        businessmanDto.setPhone(1);
        businessmanDto.setDirection("UpdatedDirection");

        when(businessmanRepository.findById(nonExistentBusinessmanId)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> {
            businessmanServicelmpl.updateBusinessman(nonExistentBusinessmanId, businessmanDto);
        });
    }

    @Test
    public void testDeleteBusinessman() {
        // Arrange
        Long businessmanId = 1L;

        when(businessmanRepository.existsById(businessmanId)).thenReturn(true);

        // Act
        businessmanServicelmpl.deleteBusinessman(businessmanId);

        // Assert
        verify(businessmanRepository, times(1)).deleteById(businessmanId);
    }

    @Test
    public void testDeleteBusinessmanNotFound() {
        // Arrange
        Long nonExistentBusinessmanId = 999L;

        when(businessmanRepository.existsById(nonExistentBusinessmanId)).thenReturn(false);

        // Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> {
            businessmanServicelmpl.deleteBusinessman(nonExistentBusinessmanId);
        });
    }
}
