package com.example.gogotrips.publication.domain.service;

import com.example.gogotrips.publication.resource.PublicationResource;
import com.example.gogotrips.publication.resource.PublicationResponseResource;

import java.util.List;

public interface PublicationService {
    List<PublicationResponseResource> getAllPublications();
    PublicationResponseResource getPublicationById(Long publicationId);
    PublicationResponseResource createPublication(PublicationResource publicationResource);
    PublicationResponseResource updatePublication(Long publicationId, PublicationResource publicationResource);
    void deletePublication(Long publicationId);
}
