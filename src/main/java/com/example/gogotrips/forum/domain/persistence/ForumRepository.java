package com.example.gogotrips.forum.domain.persistence;

import com.example.gogotrips.forum.domain.entity.Forum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ForumRepository extends JpaRepository<Forum, Long> {
}
