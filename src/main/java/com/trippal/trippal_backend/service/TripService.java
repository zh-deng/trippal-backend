package com.trippal.trippal_backend.service;

import com.trippal.trippal_backend.model.Trip;
import com.trippal.trippal_backend.repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class TripService {

    private final TripRepository tripRepository;

    @Autowired
    public TripService(TripRepository tripRepository) {
        this.tripRepository = tripRepository;
    }

    public Trip createTrip(Trip trip) {
        return tripRepository.save(trip);
    }
}
