package com.example.gogotrips.plan.domain.service;

import com.example.gogotrips.plan.domain.entity.Plan;
import com.example.gogotrips.plan.domain.persistence.PlanRepository;
import com.example.gogotrips.plan.mappers.PlanMapper;
import com.example.gogotrips.plan.resource.PlanResource;
import com.example.gogotrips.plan.resource.PlanResponseResource;
import com.example.gogotrips.shared.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PlanServiceImpl implements PlanService{
    private final PlanRepository planRepository;

    private final PlanMapper planMapper;

    @Override
    public List<PlanResponseResource> getAllPlans() {
        List<Plan> plans = planRepository.findAll();
        return planMapper.entityListToResponseResourceList(plans);
    }

    @Override
    public PlanResponseResource getPlanById(Long planId) {
        Plan plan = planRepository.findById(planId)
                .orElseThrow(() -> new ResourceNotFoundException("Plan not found with id: " + planId));
        return planMapper.entityToResponseResource(plan);
    }

    @Transactional
    @Override
    public PlanResponseResource createPlan(PlanResource planResource) {
        Plan plan = planMapper.resourceToEntity(planResource);
        plan = planRepository.save(plan);

        return planMapper.entityToResponseResource(plan);
    }

    @Transactional
    @Override
    public PlanResponseResource updatePlan(Long planId, PlanResource planResource) {
        Optional<Plan> optionalplan = planRepository.findById(planId);

        if (optionalplan.isPresent()) {
            Plan plan = optionalplan.get();

            plan.setName(planResource.getName());
            plan.setDescription(planResource.getDescription());

            plan = planRepository.save(plan);
            return planMapper.entityToResponseResource(plan);
        } else {
            throw new ResourceNotFoundException("Plan not found with id: " + planId);
        }
    }

    @Override
    @Transactional
    public void deletePlan(Long planId) {
        if (!planRepository.existsById(planId)) {
            throw new ResourceNotFoundException("Plan not found with id: " + planId);
        }

        planRepository.deleteById(planId);
    }
}
