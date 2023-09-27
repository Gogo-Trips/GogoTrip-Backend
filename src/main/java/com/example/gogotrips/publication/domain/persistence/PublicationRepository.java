package com.example.gogotrips.publication.domain.persistence;

import com.example.gogotrips.publication.domain.entity.Publication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublicationRepository extends JpaRepository<Publication, Long> {
}
