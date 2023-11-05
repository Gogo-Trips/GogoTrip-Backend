package com.example.gogotrips.businessman.domain.persistence;

import com.example.gogotrips.businessman.domain.entity.Businessman;
import com.example.gogotrips.traveler.domain.entity.Traveler;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BusinessmanRepository extends JpaRepository<Businessman, Long> {
    Optional<Businessman> findBycompanyName(String companyName);

    boolean existsByEmail(String email);

    Optional<Businessman> findByEmail(String email);

}
