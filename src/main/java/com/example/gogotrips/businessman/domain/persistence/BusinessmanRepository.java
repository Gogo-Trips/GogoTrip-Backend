package com.example.gogotrips.businessman.domain.persistence;

import com.example.gogotrips.businessman.domain.entity.Businessman;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusinessmanRepository extends JpaRepository<Businessman, Long> {

}
