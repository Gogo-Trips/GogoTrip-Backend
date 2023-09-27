package com.example.gogotrips.traveler.api;

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
@RequestMapping("/api/v1/travelers")
@RequiredArgsConstructor
public class TravelersController {

    private final TravelerService travelerService;

    @PostMapping
    public ResponseEntity<TravelerResponseResource> createTraveler(@Valid @RequestBody TravelerResource travelerResource) {
        TravelerResponseResource travelerResponseResource = travelerService.createTraveler(travelerResource);
        return new ResponseEntity<>(travelerResponseResource, HttpStatus.CREATED);
    }

    @GetMapping("/{travelerId}")
    public ResponseEntity<TravelerResponseResource> getTravelerById(@PathVariable Long travelerId) {
        TravelerResponseResource travelerResponseResource = travelerService.getTravelerById(travelerId);
        return new ResponseEntity<>(travelerResponseResource, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<TravelerResponseResource>> getAllTravelers() {
        List<TravelerResponseResource> travelerResponseResources = travelerService.getAllTravelers();
        return new ResponseEntity<>(travelerResponseResources, HttpStatus.OK);
    }

    @PutMapping("/{travelerId}")
    public ResponseEntity<TravelerResponseResource> updateTraveler(
            @PathVariable Long travelerId,
            @Valid @RequestBody TravelerResource travelerResource) {
        TravelerResponseResource travelerResponseResource = travelerService.updateTraveler(travelerId, travelerResource);
        return new ResponseEntity<>(travelerResponseResource, HttpStatus.OK);
    }

    @DeleteMapping("/{travelerId}")
    public ResponseEntity<Void> deleteTraveler(@PathVariable Long travelerId) {
        travelerService.deleteTraveler(travelerId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
