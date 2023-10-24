package com.example.gogotrips.shoppingcart.mappers;

import com.example.gogotrips.shoppingcart.domain.entity.Shoppingcart;
import com.example.gogotrips.shoppingcart.resource.ShoppingcartResource;
import com.example.gogotrips.shoppingcart.resource.ShoppingcartResponseResource;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ShoppingcartMapper {
    private final ModelMapper modelMapper;

    public ShoppingcartMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Shoppingcart resourceToEntity(ShoppingcartResource shoppingcartResource) {
        return modelMapper.map(shoppingcartResource, Shoppingcart.class);
    }

    public ShoppingcartResource entityToResource(Shoppingcart shoppingcart) {
        return modelMapper.map(shoppingcart, ShoppingcartResource.class);
    }

    public ShoppingcartResponseResource entityToResponseResource(Shoppingcart shoppingcart) {
        return modelMapper.map(shoppingcart, ShoppingcartResponseResource.class);
    }

    public List<Shoppingcart> resourceListToEntityList(List<ShoppingcartResource> shoppingcartResources) {
        return shoppingcartResources
                .stream()
                .map(this::resourceToEntity)
                .toList();
    }

    public List<ShoppingcartResource> entityListToResourceList(List<Shoppingcart> shoppingcarts) {
        return shoppingcarts
                .stream()
                .map(this::entityToResource)
                .toList();
    }

    public List<ShoppingcartResponseResource> entityListToResponseResourceList(List<Shoppingcart> shoppingcarts) {
        return shoppingcarts
                .stream()
                .map(this::entityToResponseResource)
                .toList();
    }
}
