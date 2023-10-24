package com.example.gogotrips.plan.domain.service;

import com.example.gogotrips.plan.resource.PlanResource;
import com.example.gogotrips.plan.resource.PlanResponseResource;

import java.util.List;

public interface PlanService {
    List<PlanResponseResource> getAllPlans();
    PlanResponseResource getPlanById(Long travelerId);
    PlanResponseResource createPlan(PlanResource planResource);
    PlanResponseResource updatePlan(Long planId, PlanResource planResource);
    void deletePlan(Long planId);
}
