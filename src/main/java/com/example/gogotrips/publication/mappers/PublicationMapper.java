package com.example.gogotrips.publication.mappers;

import com.example.gogotrips.publication.domain.entity.Publication;
import com.example.gogotrips.publication.resource.PublicationResource;
import com.example.gogotrips.publication.resource.PublicationResponseResource;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PublicationMapper {
    private final ModelMapper modelMapper;

    public PublicationMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Publication resourceToEntity(PublicationResource publicationResource) {
        return modelMapper.map(publicationResource, Publication.class);
    }

    public PublicationResource entityToResource(Publication publication) {
        return modelMapper.map(publication, PublicationResource.class);
    }

    public PublicationResponseResource entityToResponseResource(Publication publication) {
        return modelMapper.map(publication, PublicationResponseResource.class);
    }

    public List<Publication> resourceListToEntityList(List<PublicationResource> publicationResources) {
        return publicationResources
                .stream()
                .map(this::resourceToEntity)
                .toList();
    }

    public List<PublicationResource> entityListToResourceList(List<Publication> publications) {
        return publications
                .stream()
                .map(this::entityToResource)
                .toList();
    }

    public List<PublicationResponseResource> entityListToResponseResourceList(List<Publication> publications) {
        return publications
                .stream()
                .map(this::entityToResponseResource)
                .toList();
    }
}
