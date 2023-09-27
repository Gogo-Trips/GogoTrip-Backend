package com.example.gogotrips.traveler.domain.service;

import com.example.gogotrips.shared.exception.ResourceNotFoundException;
import com.example.gogotrips.traveler.domain.entity.Traveler;
import com.example.gogotrips.traveler.domain.persistence.TravelerRepository;
import com.example.gogotrips.traveler.mappers.TravelerMapper;
import com.example.gogotrips.traveler.resource.TravelerResource;
import com.example.gogotrips.traveler.resource.TravelerResponseResource;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TravelerServiceImpl implements TravelerService {

    private final TravelerRepository travelerRepository;

    private final TravelerMapper travelerMapper;

    @Override
    public List<TravelerResponseResource> getAllTravelers() {
        List<Traveler> travelers = travelerRepository.findAll();
        return travelerMapper.entityListToResponseResourceList(travelers);
    }

    @Override
    public TravelerResponseResource getTravelerById(Long travelerId) {
        Traveler traveler = travelerRepository.findById(travelerId)
                .orElseThrow(() -> new ResourceNotFoundException("Traveler not found with id: " + travelerId));
        return travelerMapper.entityToResponseResource(traveler);
    }

    @Transactional
    @Override
    public TravelerResponseResource createTraveler(TravelerResource travelerResource) {
        Traveler traveler = travelerMapper.resourceToEntity(travelerResource);
        traveler = travelerRepository.save(traveler);

        return travelerMapper.entityToResponseResource(traveler);
    }

    @Transactional
    @Override
    public TravelerResponseResource updateTraveler(Long travelerId, TravelerResource travelerResource) {
        Optional<Traveler> optionalTraveler = travelerRepository.findById(travelerId);

        if (optionalTraveler.isPresent()) {
            Traveler traveler = optionalTraveler.get();

            traveler.setEmail(travelerResource.getEmail());
            traveler.setName(travelerResource.getName());
            traveler.setInformationCard(travelerResource.getInformationCard());

            traveler = travelerRepository.save(traveler);
            return travelerMapper.entityToResponseResource(traveler);
        } else {
            throw new ResourceNotFoundException("Traveler not found with id: " + travelerId);
        }
    }

    @Override
    @Transactional
    public void deleteTraveler(Long travelerId) {
        if (!travelerRepository.existsById(travelerId)) {
            throw new ResourceNotFoundException("Traveler not found with id: " + travelerId);
        }

        travelerRepository.deleteById(travelerId);
    }
}
