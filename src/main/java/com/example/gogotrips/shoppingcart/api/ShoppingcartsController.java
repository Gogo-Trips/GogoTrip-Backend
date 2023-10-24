package com.example.gogotrips.shoppingcart.api;

import com.example.gogotrips.shoppingcart.domain.service.ShoppingcartService;
import com.example.gogotrips.shoppingcart.resource.ShoppingcartResource;
import com.example.gogotrips.shoppingcart.resource.ShoppingcartResponseResource;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/shoppingcarts")
@RequiredArgsConstructor
public class ShoppingcartsController {
    private final ShoppingcartService shoppingcartService;

    @PostMapping
    public ResponseEntity<ShoppingcartResponseResource> createShoppingcart(@Valid @RequestBody ShoppingcartResource shoppingcartResource) {
        ShoppingcartResponseResource shoppingcartResponseResource = shoppingcartService.createShoppingcart(shoppingcartResource);
        return new ResponseEntity<>(shoppingcartResponseResource, HttpStatus.CREATED);
    }

    @GetMapping("/{shoppingcartId}")
    public ResponseEntity<ShoppingcartResponseResource> getShoppingcartById(@PathVariable Long shoppingcartId) {
        ShoppingcartResponseResource shoppingcartResponseResource = shoppingcartService.getShoppingcartById(shoppingcartId);
        return new ResponseEntity<>(shoppingcartResponseResource, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ShoppingcartResponseResource>> getAllShoppingcarts() {
        List<ShoppingcartResponseResource> shoppingcartResponseResources = shoppingcartService.getAllShoppingcarts();
        return new ResponseEntity<>(shoppingcartResponseResources, HttpStatus.OK);
    }

    @PutMapping("/{shoppingcartId}")
    public ResponseEntity<ShoppingcartResponseResource> updateShoppingcart(
            @PathVariable Long shoppingcartId,
            @Valid @RequestBody ShoppingcartResource shoppingcartResource) {
        ShoppingcartResponseResource shoppingcartResponseResource = shoppingcartService.updateShoppingcart(shoppingcartId, shoppingcartResource);
        return new ResponseEntity<>(shoppingcartResponseResource, HttpStatus.OK);
    }

    @DeleteMapping("/{shoppingcartId}")
    public ResponseEntity<Void> deleteShoppingcart(@PathVariable Long shoppingcartId) {
        shoppingcartService.deleteShoppingcart(shoppingcartId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
