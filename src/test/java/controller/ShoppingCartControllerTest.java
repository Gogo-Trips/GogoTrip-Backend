package controller;

import com.example.gogotrips.shoppingcart.api.ShoppingcartsController;
import com.example.gogotrips.shoppingcart.domain.service.ShoppingcartServicelmpl;
import com.example.gogotrips.shoppingcart.resource.ShoppingcartResource;
import com.example.gogotrips.shoppingcart.resource.ShoppingcartResponseResource;
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

public class ShoppingCartControllerTest {
    @InjectMocks
    private ShoppingcartsController shoppingCartController;

    @Mock
    private ShoppingcartServicelmpl shoppingCartService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateShoppingcart() throws Exception {
        ShoppingcartResource shoppingcartResource = new ShoppingcartResource();

        ShoppingcartResponseResource shoppingcartResponseResource = new ShoppingcartResponseResource();

        when(shoppingCartService.createShoppingcart(shoppingcartResource)).thenReturn(shoppingcartResponseResource);

        ResponseEntity<ShoppingcartResponseResource> responseEntity = shoppingCartController.createShoppingcart(shoppingcartResource);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(shoppingcartResponseResource, responseEntity.getBody());
    }

    @Test
    public void testGetShoppingcartById() {
        Long ShoppingcartId = 1L;
        ShoppingcartResponseResource shoppingcartResponseResource = new ShoppingcartResponseResource();

        when(shoppingCartService.getShoppingcartById(ShoppingcartId)).thenReturn(shoppingcartResponseResource);

        ResponseEntity<ShoppingcartResponseResource> responseEntity = shoppingCartController.getShoppingcartById(ShoppingcartId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(shoppingcartResponseResource, responseEntity.getBody());
    }

    @Test
    public void testGetAllShoppingcarts() {
        List<ShoppingcartResponseResource> responseDtoList = new ArrayList<>();

        when(shoppingCartService.getAllShoppingcarts()).thenReturn(responseDtoList);

        ResponseEntity<List<ShoppingcartResponseResource>> responseEntity = shoppingCartController.getAllShoppingcarts();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(responseDtoList, responseEntity.getBody());
    }

    @Test
    public void testUpdateShoppingcart() {
        Long ShoppingcartId = 1L;
        ShoppingcartResource shoppingcartResource = new ShoppingcartResource();

        ShoppingcartResponseResource ShoppingcartDto = new ShoppingcartResponseResource();

        when(shoppingCartService.updateShoppingcart(ShoppingcartId, shoppingcartResource)).thenReturn(ShoppingcartDto);

        ResponseEntity<ShoppingcartResponseResource> responseEntity = shoppingCartController.updateShoppingcart(ShoppingcartId, shoppingcartResource);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(ShoppingcartDto, responseEntity.getBody());
    }

    @Test
    public void testDeleteShoppingcart() {
        Long ShoppingcartId = 1L;

        ResponseEntity<Void> responseEntity = shoppingCartController.deleteShoppingcart(ShoppingcartId);

        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
        verify(shoppingCartService, times(1)).deleteShoppingcart(ShoppingcartId);
    }
}

