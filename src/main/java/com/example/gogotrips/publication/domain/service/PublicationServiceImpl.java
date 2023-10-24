package com.example.gogotrips.publication.domain.service;

import com.example.gogotrips.publication.domain.entity.Publication;
import com.example.gogotrips.publication.domain.persistence.PublicationRepository;
import com.example.gogotrips.publication.mappers.PublicationMapper;
import com.example.gogotrips.publication.resource.PublicationResource;
import com.example.gogotrips.publication.resource.PublicationResponseResource;
import com.example.gogotrips.shared.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PublicationServiceImpl implements PublicationService {

    private final PublicationRepository publicationRepository;

    private final PublicationMapper publicationMapper;

    @Override
    public List<PublicationResponseResource> getAllPublications() {
        List<Publication> publications = publicationRepository.findAll();
        return publicationMapper.entityListToResponseResourceList(publications);
    }

    @Override
    public PublicationResponseResource getPublicationById(Long publicationId) {
        Publication publication = publicationRepository.findById(publicationId)
                .orElseThrow(() -> new ResourceNotFoundException("Publication not found with id: " + publicationId));
        return publicationMapper.entityToResponseResource(publication);
    }

    @Override
    public PublicationResponseResource createPublication(PublicationResource publicationResource) {
        Publication publication = publicationMapper.resourceToEntity(publicationResource);
        publication = publicationRepository.save(publication);

        return publicationMapper.entityToResponseResource(publication);
    }

    @Override
    public PublicationResponseResource updatePublication(Long publicationId, PublicationResource publicationResource) {
        Optional<Publication> optionalPublication = publicationRepository.findById(publicationId);

        if (optionalPublication.isPresent()) {
            Publication publication = optionalPublication.get();

            publication.setContent(publicationResource.getContent());
            publication.setTitle(publicationResource.getTitle());

            publication = publicationRepository.save(publication);
            return publicationMapper.entityToResponseResource(publication);
        } else {
            throw new ResourceNotFoundException("Publication not found with id: " + publicationId);
        }
    }

    @Override
    public void deletePublication(Long publicationId) {
        if (!publicationRepository.existsById(publicationId)) {
            throw new ResourceNotFoundException("Publication not found with id: " + publicationId);
        }

        publicationRepository.deleteById(publicationId);
    }
}
