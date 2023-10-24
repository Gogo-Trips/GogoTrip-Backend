package com.example.gogotrips.shoppingcart.domain.persistence;

import com.example.gogotrips.shoppingcart.domain.entity.Shoppingcart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingcartRepository extends JpaRepository<Shoppingcart, Long> {
}
