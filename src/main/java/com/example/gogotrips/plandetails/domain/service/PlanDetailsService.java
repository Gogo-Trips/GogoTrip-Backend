package com.example.gogotrips.plandetails.domain.service;

import com.example.gogotrips.plandetails.resource.PlanDetailsResource;
import com.example.gogotrips.plandetails.resource.PlanDetailsResponseResource;
import java.util.List;

public interface PlanDetailsService {
    List<PlanDetailsResponseResource> getAllPlanDetails();
    PlanDetailsResponseResource getPlanDetailsById(Long plandetailsId);
    PlanDetailsResponseResource createPlanDetails(PlanDetailsResource planDetailsResource);
    PlanDetailsResponseResource updatePlanDetails(Long plandetailsId, PlanDetailsResource planDetailsResource);
    void deletePlanDetails(Long plandetailsId);
}
