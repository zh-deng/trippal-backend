package com.trippal.trippal_backend.service;

import com.trippal.trippal_backend.model.Trip;
import com.trippal.trippal_backend.model.UserInfo;
import com.trippal.trippal_backend.repository.TripRepository;
import jakarta.persistence.EntityNotFoundException;
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

    public Trip getTripById(Long id) {
        return tripRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Trip not found with id: " + id));
    }

    ;

    public Trip createTrip(Trip trip) {
        return tripRepository.save(trip);
    }

    public Trip updateTrip(Long id, Trip updatedTrip, UserInfo user) {
        Optional<Trip> existingTripOpt = tripRepository.findById(id);

        if (existingTripOpt.isPresent()) {
            Trip existingTrip = existingTripOpt.get();

            existingTrip.setTitle(updatedTrip.getTitle());
            existingTrip.setPublic(updatedTrip.isPublic());
            existingTrip.getRoadmapItems().clear();
            existingTrip.getRoadmapItems().addAll(updatedTrip.getRoadmapItems());

            return tripRepository.save(existingTrip);
        } else {
            throw new EntityNotFoundException("Trip not found with id: " + id);
        }
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
