package com.example.gogotrips.traveler.domain.persistence;

import com.example.gogotrips.traveler.domain.entity.Traveler;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TravelerRepository extends JpaRepository<Traveler, Long> {
}
