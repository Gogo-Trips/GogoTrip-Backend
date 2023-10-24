package com.example.gogotrips.plandetails.domain.persistence;

import com.example.gogotrips.plandetails.domain.entity.PlanDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanDetailsRespository extends JpaRepository<PlanDetails,Long> {
}
