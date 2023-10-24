package com.example.gogotrips.plandetails.mappers;

import com.example.gogotrips.plandetails.domain.entity.PlanDetails;
import com.example.gogotrips.plandetails.resource.PlanDetailsResource;
import com.example.gogotrips.plandetails.resource.PlanDetailsResponseResource;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PlanDetailsMapper {
    private final ModelMapper modelMapper;

    public PlanDetailsMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public PlanDetails resourceToEntity(PlanDetailsResource plandetailsResource) {
        return modelMapper.map(plandetailsResource, PlanDetails.class);
    }

    public PlanDetailsResource entityToResource(PlanDetails plandetails) {
        return modelMapper.map(plandetails, PlanDetailsResource.class);
    }

    public PlanDetailsResponseResource entityToResponseResource(PlanDetails plandetails) {
        return modelMapper.map(plandetails, PlanDetailsResponseResource.class);
    }

    public List<PlanDetails> resourceListToEntityList(List<PlanDetailsResource> planDetailsResources) {
        return planDetailsResources
                .stream()
                .map(this::resourceToEntity)
                .toList();
    }

    public List<PlanDetailsResource> entityListToResourceList(List<PlanDetails> planDetails) {
        return planDetails
                .stream()
                .map(this::entityToResource)
                .toList();
    }

    public List<PlanDetailsResponseResource> entityListToResponseResourceList(List<PlanDetails> planDetails) {
        return planDetails
                .stream()
                .map(this::entityToResponseResource)
                .toList();
    }
}
