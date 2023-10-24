package com.example.gogotrips.plan.api;

import com.example.gogotrips.plan.domain.service.PlanService;
import com.example.gogotrips.plan.resource.PlanResource;
import com.example.gogotrips.plan.resource.PlanResponseResource;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/plans")
@RequiredArgsConstructor
public class PlansController {
    private final PlanService planService;

    @PostMapping
    public ResponseEntity<PlanResponseResource> createPLan(@Valid @RequestBody PlanResource planResource) {
        PlanResponseResource planResponseResource = planService.createPlan(planResource);
        return new ResponseEntity<>(planResponseResource, HttpStatus.CREATED);
    }

    @GetMapping("/{planId}")
    public ResponseEntity<PlanResponseResource> getPlanById(@PathVariable Long planId) {
        PlanResponseResource planResponseResource = planService.getPlanById(planId);
        return new ResponseEntity<>(planResponseResource, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<PlanResponseResource>> getAllplans() {
        List<PlanResponseResource> planResponseResources = planService.getAllPlans();
        return new ResponseEntity<>(planResponseResources, HttpStatus.OK);
    }

    @PutMapping("/{planId}")
    public ResponseEntity<PlanResponseResource> updatePlan(
            @PathVariable Long planId,
            @Valid @RequestBody PlanResource planResource) {
        PlanResponseResource planResponseResource = planService.updatePlan(planId, planResource);
        return new ResponseEntity<>(planResponseResource, HttpStatus.OK);
    }

    @DeleteMapping("/{planId}")
    public ResponseEntity<Void> deletePlan(@PathVariable Long planId) {
        planService.deletePlan(planId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
