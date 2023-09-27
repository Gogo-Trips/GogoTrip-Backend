package com.example.gogotrips.publication.api;

import com.example.gogotrips.publication.domain.service.PublicationService;
import com.example.gogotrips.publication.resource.PublicationResource;
import com.example.gogotrips.publication.resource.PublicationResponseResource;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/publications")
@RequiredArgsConstructor
public class PublicationsController {

    private final PublicationService publicationService;

    @PostMapping
    public ResponseEntity<PublicationResponseResource> createPublication(@Valid @RequestBody PublicationResource publicationResource) {
        PublicationResponseResource publicationResponseResource = publicationService.createPublication(publicationResource);
        return new ResponseEntity<>(publicationResponseResource, HttpStatus.CREATED);
    }

    @GetMapping("/{publicationId}")
    public ResponseEntity<PublicationResponseResource> getPublicationById(@PathVariable Long publicationId) {
        PublicationResponseResource publicationResponseResource = publicationService.getPublicationById(publicationId);
        return new ResponseEntity<>(publicationResponseResource, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<PublicationResponseResource>> getAllPublications() {
        List<PublicationResponseResource> publicationResponseResources = publicationService.getAllPublications();
        return new ResponseEntity<>(publicationResponseResources, HttpStatus.OK);
    }

    @PutMapping("/{publicationId}")
    public ResponseEntity<PublicationResponseResource> updatePublication(
            @PathVariable Long publicationId,
            @Valid @RequestBody PublicationResource publicationResource) {
        PublicationResponseResource publicationResponseResources = publicationService.updatePublication(publicationId, publicationResource);
        return new ResponseEntity<>(publicationResponseResources, HttpStatus.OK);
    }

    @DeleteMapping("/{publicationId}")
    public ResponseEntity<Void> deletePublication(@PathVariable Long publicationId) {
        publicationService.deletePublication(publicationId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
