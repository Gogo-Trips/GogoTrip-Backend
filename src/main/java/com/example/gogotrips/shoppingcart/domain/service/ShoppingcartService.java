package com.example.gogotrips.shoppingcart.domain.service;

import com.example.gogotrips.shoppingcart.resource.ShoppingcartResource;
import com.example.gogotrips.shoppingcart.resource.ShoppingcartResponseResource;

import java.util.List;

public interface ShoppingcartService {
    List<ShoppingcartResponseResource> getAllShoppingcarts();
    ShoppingcartResponseResource getShoppingcartById(Long shoppingcartId);
    ShoppingcartResponseResource createShoppingcart(ShoppingcartResource shoppingcartResource);
    ShoppingcartResponseResource updateShoppingcart(Long shoppingcartId, ShoppingcartResource shoppingcartResource);
    void deleteShoppingcart(Long shoppingcartId);
}
