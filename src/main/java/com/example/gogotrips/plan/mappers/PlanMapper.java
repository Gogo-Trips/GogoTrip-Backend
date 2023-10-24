package com.example.gogotrips.plan.mappers;

import com.example.gogotrips.plan.domain.entity.Plan;
import com.example.gogotrips.plan.resource.PlanResource;
import com.example.gogotrips.plan.resource.PlanResponseResource;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PlanMapper {
    private final ModelMapper modelMapper;

    public PlanMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Plan resourceToEntity(PlanResource planResource) {
        return modelMapper.map(planResource, Plan.class);
    }

    public PlanResource entityToResource(Plan plan) {
        return modelMapper.map(plan, PlanResource.class);
    }

    public PlanResponseResource entityToResponseResource(Plan plan) {
        return modelMapper.map(plan, PlanResponseResource.class);
    }

    public List<Plan> resourceListToEntityList(List<PlanResource> planResources) {
        return planResources
                .stream()
                .map(this::resourceToEntity)
                .toList();
    }

    public List<PlanResource> entityListToResourceList(List<Plan> plans) {
        return plans
                .stream()
                .map(this::entityToResource)
                .toList();
    }

    public List<PlanResponseResource> entityListToResponseResourceList(List<Plan> plans) {
        return plans
                .stream()
                .map(this::entityToResponseResource)
                .toList();
    }
}
