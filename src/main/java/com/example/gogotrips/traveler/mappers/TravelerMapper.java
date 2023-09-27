package com.example.gogotrips.traveler.mappers;

import com.example.gogotrips.traveler.domain.entity.Traveler;
import com.example.gogotrips.traveler.resource.TravelerResource;
import com.example.gogotrips.traveler.resource.TravelerResponseResource;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TravelerMapper {
    private final ModelMapper modelMapper;

    public TravelerMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Traveler resourceToEntity(TravelerResource travelerResource) {
        return modelMapper.map(travelerResource, Traveler.class);
    }

    public TravelerResource entityToResource(Traveler traveler) {
        return modelMapper.map(traveler, TravelerResource.class);
    }

    public TravelerResponseResource entityToResponseResource(Traveler traveler) {
        return modelMapper.map(traveler, TravelerResponseResource.class);
    }

    public List<Traveler> resourceListToEntityList(List<TravelerResource> travelerResources) {
        return travelerResources
                .stream()
                .map(this::resourceToEntity)
                .toList();
    }

    public List<TravelerResource> entityListToResourceList(List<Traveler> travelers) {
        return travelers
                .stream()
                .map(this::entityToResource)
                .toList();
    }

    public List<TravelerResponseResource> entityListToResponseResourceList(List<Traveler> travelers) {
        return travelers
                .stream()
                .map(this::entityToResponseResource)
                .toList();
    }
}
