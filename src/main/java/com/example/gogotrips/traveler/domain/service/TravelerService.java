package com.example.gogotrips.traveler.domain.service;

import com.example.gogotrips.traveler.resource.TravelerResource;
import com.example.gogotrips.traveler.resource.TravelerResponseResource;

import java.util.List;

public interface TravelerService {
    List<TravelerResponseResource> getAllTravelers();
    TravelerResponseResource getTravelerById(Long travelerId);
    TravelerResponseResource createTraveler(TravelerResource travelerResource);
    TravelerResponseResource updateTraveler(Long travelerId, TravelerResource travelerResource);
    void deleteTraveler(Long travelerId);
}
