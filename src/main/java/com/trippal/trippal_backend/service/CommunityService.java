package com.trippal.trippal_backend.service;

import com.trippal.trippal_backend.model.Trip;
import com.trippal.trippal_backend.repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CommunityService {

    private final TripRepository tripRepository;

    @Autowired
    public CommunityService(TripRepository tripRepository) {
        this.tripRepository = tripRepository;
    }

    // Handles pagination of public trips
    public Page<Trip> getPublicTrips(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return tripRepository.findByPublicTrue(pageable);
    }
}
