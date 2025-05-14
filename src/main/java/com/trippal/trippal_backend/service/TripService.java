package com.trippal.trippal_backend.service;

import com.trippal.trippal_backend.model.Trip;
import com.trippal.trippal_backend.model.UserInfo;
import com.trippal.trippal_backend.repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TripService {

    private final TripRepository tripRepository;

    @Autowired
    public TripService(TripRepository tripRepository) {
        this.tripRepository = tripRepository;
    }

    public Trip createTrip(Trip trip) {
        return tripRepository.save(trip);
    }

    public boolean deleteTrip(Long id, UserInfo user) {
        Optional<Trip> tripOpt = tripRepository.findById(id);
        if (tripOpt.isPresent() && tripOpt.get().getUser().getId().equals(user.getId())) {
            tripRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
