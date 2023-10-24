package com.example.gogotrips.plandetails.api;

import com.example.gogotrips.plandetails.domain.service.PlanDetailsService;
import com.example.gogotrips.plandetails.resource.PlanDetailsResource;
import com.example.gogotrips.plandetails.resource.PlanDetailsResponseResource;
import com.example.gogotrips.traveler.domain.service.TravelerService;
import com.example.gogotrips.traveler.resource.TravelerResource;
import com.example.gogotrips.traveler.resource.TravelerResponseResource;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/plandetails")
@RequiredArgsConstructor
public class PlanDetailsController {
    private final PlanDetailsService planDetailsService;

    @PostMapping
    public ResponseEntity<PlanDetailsResponseResource> createPlanDetails(@Valid @RequestBody PlanDetailsResource planDetailsResource) {
        PlanDetailsResponseResource planDetailsResponseResource = planDetailsService.createPlanDetails(planDetailsResource);
        return new ResponseEntity<>(planDetailsResponseResource, HttpStatus.CREATED);
    }

    @GetMapping("/{plandetailsId}")
    public ResponseEntity<PlanDetailsResponseResource> getPlanDetailsById(@PathVariable Long plandetailsId) {
        PlanDetailsResponseResource planDetailsResponseResource = planDetailsService.getPlanDetailsById(plandetailsId);
        return new ResponseEntity<>(planDetailsResponseResource, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<PlanDetailsResponseResource>> getAllPlanDetailss() {
        List<PlanDetailsResponseResource> planDetailsResponseResources = planDetailsService.getAllPlanDetails();
        return new ResponseEntity<>(planDetailsResponseResources, HttpStatus.OK);
    }

    @PutMapping("/{plandetailsId}")
    public ResponseEntity<PlanDetailsResponseResource> updatePlanDetails(
            @PathVariable Long plandetailsId,
            @Valid @RequestBody PlanDetailsResource planDetailsResource) {
        PlanDetailsResponseResource planDetailsResponseResource = planDetailsService.updatePlanDetails(plandetailsId, planDetailsResource);
        return new ResponseEntity<>(planDetailsResponseResource, HttpStatus.OK);
    }

    @DeleteMapping("/{PlanDetailsId}")
    public ResponseEntity<Void> deletePlanDetails(@PathVariable Long plandetailsId) {
        planDetailsService.deletePlanDetails(plandetailsId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
