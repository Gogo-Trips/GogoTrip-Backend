package service;

import com.example.gogotrips.shared.exception.ResourceNotFoundException;
import com.example.gogotrips.shoppingcart.domain.entity.Shoppingcart;
import com.example.gogotrips.shoppingcart.domain.persistence.ShoppingcartRepository;
import com.example.gogotrips.shoppingcart.domain.service.ShoppingcartServicelmpl;
import com.example.gogotrips.shoppingcart.mappers.ShoppingcartMapper;
import com.example.gogotrips.shoppingcart.resource.ShoppingcartResource;
import com.example.gogotrips.shoppingcart.resource.ShoppingcartResponseResource;
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

public class ShoppingCartServiceTest {
    @InjectMocks
    private ShoppingcartServicelmpl shoppingcartServicelmpl;

    @Mock
    private ShoppingcartRepository shoppingcartRepository;

    @Mock
    private ShoppingcartMapper shoppingcartMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllShoppingcarts() {
        // Arrange
        List<Shoppingcart> Shoppingcarts = new ArrayList<>();
        List<ShoppingcartResponseResource> shoppingcartResponseResources = new ArrayList<>();

        when(shoppingcartRepository.findAll()).thenReturn(Shoppingcarts);
        when(shoppingcartMapper.entityListToResponseResourceList(Shoppingcarts)).thenReturn(shoppingcartResponseResources);

        // Act
        List<ShoppingcartResponseResource> result = shoppingcartServicelmpl.getAllShoppingcarts();

        // Assert
        assertEquals(shoppingcartResponseResources, result);
    }

    @Test
    public void testGetShoppingcartById() {
        // Arrange
        Long ShoppingcartId = 1L;
        Shoppingcart shoppingcart = new Shoppingcart();
        ShoppingcartResponseResource responseDto = new ShoppingcartResponseResource();

        when(shoppingcartRepository.findById(ShoppingcartId)).thenReturn(Optional.of(shoppingcart));
        when(shoppingcartMapper.entityToResponseResource(shoppingcart)).thenReturn(responseDto);

        // Act
        ShoppingcartResponseResource result = shoppingcartServicelmpl.getShoppingcartById(ShoppingcartId);

        // Assert
        assertEquals(responseDto, result);
    }

    @Test
    public void testGetShoppingcartByIdNotFound() {
        // Arrange
        Long nonExistentShoppingcartId = 999L;

        when(shoppingcartRepository.findById(nonExistentShoppingcartId)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> {
            shoppingcartServicelmpl.getShoppingcartById(nonExistentShoppingcartId);
        });
    }

    @Test
    public void testCreateShoppingcart() {
        // Arrange
        ShoppingcartResource shoppingcartResource = new ShoppingcartResource();
        shoppingcartResource.setAmount(5);

        Shoppingcart shoppingcart = new Shoppingcart();
        shoppingcartResource.setAmount(shoppingcartResource.getAmount());

        ShoppingcartResponseResource shoppingcartResponseResource = new ShoppingcartResponseResource();
        shoppingcartResponseResource.setId(1L);
        shoppingcartResponseResource.setAmount(shoppingcartResponseResource.getAmount());

        when(shoppingcartMapper.resourceToEntity(shoppingcartResource)).thenReturn(shoppingcart);
        when(shoppingcartRepository.save(shoppingcart)).thenReturn(shoppingcart);
        when(shoppingcartMapper.entityToResponseResource(shoppingcart)).thenReturn(shoppingcartResponseResource);

        // Act
        ShoppingcartResponseResource result = shoppingcartServicelmpl.createShoppingcart(shoppingcartResource);

        // Assert
        assertEquals(shoppingcartResponseResource, result);
    }


    @Test
    public void testUpdateShoppingcart() {//Edit
        // Arrange
        Long ShoppingcartId = 1L;
        ShoppingcartResource ShoppingcartIdDto = new ShoppingcartResource();
        Shoppingcart shoppingcart = new Shoppingcart();
        ShoppingcartResponseResource responseDto = new ShoppingcartResponseResource();

        shoppingcart.setAmount(0);

        // Configura el comportamiento esperado de los mocks
        when(shoppingcartRepository.findById(ShoppingcartId)).thenReturn(Optional.of(shoppingcart));
        when(shoppingcartRepository.save(any())).thenReturn(shoppingcart); // Cambia esto si es necesario
        when(shoppingcartMapper.entityToResponseResource(shoppingcart)).thenReturn(responseDto);

        // Act
        ShoppingcartResponseResource result = shoppingcartServicelmpl.updateShoppingcart(ShoppingcartId, ShoppingcartIdDto);

        // Assert
        assertNotNull(result); // Asegúrate de que result no sea nulo
        assertEquals(responseDto, result); // Asegúrate de que result sea igual a responseDto
    }

    @Test
    public void testUpdateShoppingcartNotFound() {
        // Arrange
        Long nonExistentShoppingcartId = 999L;
        ShoppingcartResource ShoppingcartDto = new ShoppingcartResource();
        ShoppingcartDto.setAmount(0);

        when(shoppingcartRepository.findById(nonExistentShoppingcartId)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> {
            shoppingcartServicelmpl.updateShoppingcart(nonExistentShoppingcartId, ShoppingcartDto);
        });
    }

    @Test
    public void testDeleteShoppingcart() {
        // Arrange
        Long ShoppingcartId = 1L;

        when(shoppingcartRepository.existsById(ShoppingcartId)).thenReturn(true);

        // Act
        shoppingcartServicelmpl.deleteShoppingcart(ShoppingcartId);

        // Assert
        verify(shoppingcartRepository, times(1)).deleteById(ShoppingcartId);
    }

    @Test
    public void testDeleteShoppingcartNotFound() {
        // Arrange
        Long nonExistentShoppingcartId = 999L;

        when(shoppingcartRepository.existsById(nonExistentShoppingcartId)).thenReturn(false);

        // Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> {
            shoppingcartServicelmpl.deleteShoppingcart(nonExistentShoppingcartId);
        });
    }
}
