package com.example.gogotrips.plandetails.domain.service;

import com.example.gogotrips.plandetails.domain.entity.PlanDetails;
import com.example.gogotrips.plandetails.domain.persistence.PlanDetailsRespository;
import com.example.gogotrips.plandetails.mappers.PlanDetailsMapper;
import com.example.gogotrips.plandetails.resource.PlanDetailsResource;
import com.example.gogotrips.plandetails.resource.PlanDetailsResponseResource;
import com.example.gogotrips.shared.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PlanDetailsServiceImpl implements PlanDetailsService{
    private final PlanDetailsRespository planDetailsRespository;

    private final PlanDetailsMapper planDetailsMapper;

    @Override
    public List<PlanDetailsResponseResource> getAllPlanDetails() {
        List<PlanDetails> planDetails = planDetailsRespository.findAll();
        return planDetailsMapper.entityListToResponseResourceList(planDetails);
    }

    @Override
    public PlanDetailsResponseResource getPlanDetailsById(Long plandetailsId) {
        PlanDetails plandetails = planDetailsRespository.findById(plandetailsId)
                .orElseThrow(() -> new ResourceNotFoundException("Plan details not found with id: " + plandetailsId));
        return planDetailsMapper.entityToResponseResource(plandetails);
    }

    @Transactional
    @Override
    public PlanDetailsResponseResource createPlanDetails(PlanDetailsResource planDetailsResource) {
        PlanDetails plandetails = planDetailsMapper.resourceToEntity(planDetailsResource);
        plandetails = planDetailsRespository.save(plandetails);

        return planDetailsMapper.entityToResponseResource(plandetails);
    }

    @Transactional
    @Override
    public PlanDetailsResponseResource updatePlanDetails(Long plandetailsId, PlanDetailsResource planDetailsResource) {
        Optional<PlanDetails> optionalplandetails = planDetailsRespository.findById(plandetailsId);

        if (optionalplandetails.isPresent()) {
            PlanDetails plandetails = optionalplandetails.get();

            plandetails.setAmount(planDetailsResource.getAmount());
            plandetails.setType(planDetailsResource.getType());
            plandetails.setPrice(planDetailsResource.getPrice());

            plandetails = planDetailsRespository.save(plandetails);
            return planDetailsMapper.entityToResponseResource(plandetails);
        } else {
            throw new ResourceNotFoundException("Plan details not found with id: " + plandetailsId);
        }
    }

    @Override
    @Transactional
    public void deletePlanDetails(Long plandetailsId) {
        if (!planDetailsRespository.existsById(plandetailsId)) {
            throw new ResourceNotFoundException("Plan details not found with id: " + plandetailsId);
        }

        planDetailsRespository.deleteById(plandetailsId);
    }
}
