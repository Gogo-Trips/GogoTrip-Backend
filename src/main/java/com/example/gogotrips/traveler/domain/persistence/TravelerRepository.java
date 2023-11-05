package com.example.gogotrips.traveler.domain.persistence;

import com.example.gogotrips.traveler.domain.entity.Traveler;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TravelerRepository extends JpaRepository<Traveler, Long> {
    Optional<Traveler> findByName(String Name);

    boolean existsByEmail(String email);

    Optional<Traveler> findByEmail(String email);
}
