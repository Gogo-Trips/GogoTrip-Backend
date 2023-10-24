package com.example.gogotrips.businessman.api;

import com.example.gogotrips.businessman.domain.service.BusinessmanService;
import com.example.gogotrips.businessman.resource.BusinessmanResource;
import com.example.gogotrips.businessman.resource.BusinessmanResponseResource;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/businessmen")
@RequiredArgsConstructor
public class BusinessmanController {
    private final BusinessmanService businessmanService;

    @PostMapping
    public ResponseEntity<BusinessmanResponseResource> createBusinessman(@Valid @RequestBody BusinessmanResource businessmanResource) {
        BusinessmanResponseResource businessmanResponseResource = businessmanService.createBusinessman(businessmanResource);
        return new ResponseEntity<>(businessmanResponseResource, HttpStatus.CREATED);
    }

    @GetMapping("/{businessmanId}")
    public ResponseEntity<BusinessmanResponseResource> getBusinessmanById(@PathVariable Long businessmanId) {
        BusinessmanResponseResource businessmanResponseResource = businessmanService.getBusinessmanById(businessmanId);
        return new ResponseEntity<>(businessmanResponseResource, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<BusinessmanResponseResource>> getAllBusinessmen() {
        List<BusinessmanResponseResource> businessmanResponseResources = businessmanService.getAllBusinessmen();
        return new ResponseEntity<>(businessmanResponseResources, HttpStatus.OK);
    }

    @PutMapping("/{businessmanId}")
    public ResponseEntity<BusinessmanResponseResource> updateBusinessman(
            @PathVariable Long businessmanId,
            @Valid @RequestBody BusinessmanResource businessmanResource) {
        BusinessmanResponseResource businessmanResponseResource = businessmanService.updateBusinessman(businessmanId, businessmanResource);
        return new ResponseEntity<>(businessmanResponseResource, HttpStatus.OK);
    }

    @DeleteMapping("/{businessmanId}")
    public ResponseEntity<Void> deleteBusinessman(@PathVariable Long businessmanId) {
        businessmanService.deleteBusinessman(businessmanId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
