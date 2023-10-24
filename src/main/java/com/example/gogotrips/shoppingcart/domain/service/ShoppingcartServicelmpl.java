package com.example.gogotrips.shoppingcart.domain.service;

import com.example.gogotrips.shared.exception.ResourceNotFoundException;
import com.example.gogotrips.shoppingcart.domain.entity.Shoppingcart;
import com.example.gogotrips.shoppingcart.domain.persistence.ShoppingcartRepository;
import com.example.gogotrips.shoppingcart.mappers.ShoppingcartMapper;
import com.example.gogotrips.shoppingcart.resource.ShoppingcartResource;
import com.example.gogotrips.shoppingcart.resource.ShoppingcartResponseResource;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ShoppingcartServicelmpl implements ShoppingcartService{
    private final ShoppingcartRepository shoppingcartRepository;

    private final ShoppingcartMapper shoppingcartMapper;

    @Override
    public List<ShoppingcartResponseResource> getAllShoppingcarts() {
        List<Shoppingcart> shoppingcarts = shoppingcartRepository.findAll();
        return shoppingcartMapper.entityListToResponseResourceList(shoppingcarts);
    }

    @Override
    public ShoppingcartResponseResource getShoppingcartById(Long shoppingcartId) {
        Shoppingcart shoppingcart = shoppingcartRepository.findById(shoppingcartId)
                .orElseThrow(() -> new ResourceNotFoundException("Shoppingcart not found with id: " + shoppingcartId));
        return shoppingcartMapper.entityToResponseResource(shoppingcart);
    }

    @Transactional
    @Override
    public ShoppingcartResponseResource createShoppingcart(ShoppingcartResource shoppingcartResource) {
        Shoppingcart shoppingcart = shoppingcartMapper.resourceToEntity(shoppingcartResource);
        shoppingcart = shoppingcartRepository.save(shoppingcart);

        return shoppingcartMapper.entityToResponseResource(shoppingcart);
    }

    @Transactional
    @Override
    public ShoppingcartResponseResource updateShoppingcart(Long shoppingcartId, ShoppingcartResource shoppingcartResource) {
        Optional<Shoppingcart> optionalshoppingcart = shoppingcartRepository.findById(shoppingcartId);

        if (optionalshoppingcart.isPresent()) {
            Shoppingcart shoppingcart = optionalshoppingcart.get();

            shoppingcart.setAmount(shoppingcartResource.getAmount());

            shoppingcart = shoppingcartRepository.save(shoppingcart);
            return shoppingcartMapper.entityToResponseResource(shoppingcart);
        } else {
            throw new ResourceNotFoundException("Shoppingcart not found with id: " + shoppingcartId);
        }
    }

    @Override
    @Transactional
    public void deleteShoppingcart(Long shoppingcartId) {
        if (!shoppingcartRepository.existsById(shoppingcartId)) {
            throw new ResourceNotFoundException("Shoppingcart not found with id: " + shoppingcartId);
        }

        shoppingcartRepository.deleteById(shoppingcartId);
    }
}
