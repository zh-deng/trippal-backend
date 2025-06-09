package com.trippal.trippal_backend.service;

import com.trippal.trippal_backend.model.Trip;
import com.trippal.trippal_backend.model.UserInfo;
import com.trippal.trippal_backend.repository.TripRepository;
import com.trippal.trippal_backend.repository.UserInfoRepository;
import com.trippal.trippal_backend.util.TripCloner;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TripService {

    private final TripRepository tripRepository;
    private final UserInfoRepository userInfoRepository;
    private final TripCloner tripCloner;

    @Autowired
    public TripService(TripRepository tripRepository, UserInfoRepository userInfoRepository, TripCloner tripCloner) {
        this.tripRepository = tripRepository;
        this.userInfoRepository = userInfoRepository;
        this.tripCloner = tripCloner;
    }

    public Trip getTripById(Long id) {
        return tripRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Trip not found with id: " + id));
    }

    public Trip createTrip(Trip trip) {
        return tripRepository.save(trip);
    }

    public Trip updateTrip(Long id, Trip updatedTrip) {
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

    public boolean deleteTrip(Long id) {
        Optional<Trip> tripOpt = tripRepository.findById(id);
        if (tripOpt.isPresent()) {
            tripRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Transactional
    public void starTrip(Long tripId, UserInfo user) {
        Trip trip = tripRepository.findById(tripId)
                .orElseThrow(() -> new EntityNotFoundException("Trip not found"));

        if (!user.getStarredTrips().contains(trip)) {
            user.getStarredTrips().add(trip);
            trip.getStarredByUsers().add(user);

            userInfoRepository.save(user);
            tripRepository.save(trip);
        }
    }

    @Transactional
    public void unstarTrip(Long tripId, UserInfo user) {
        Trip trip = tripRepository.findById(tripId)
                .orElseThrow(() -> new EntityNotFoundException("Trip not found"));

        if (user.getStarredTrips().contains(trip)) {
            user.getStarredTrips().remove(trip);
            trip.getStarredByUsers().remove(user);

            userInfoRepository.save(user);
            tripRepository.save(trip);
        }
    }

    public Trip saveSharedTrip(Long id, UserInfo user) {
        Optional<Trip> existingTripOpt = tripRepository.findById(id);

        if (existingTripOpt.isPresent()) {
            Trip copiedTrip = tripCloner.deepCopyTrip(existingTripOpt.get(), user);

            return tripRepository.save(copiedTrip);
        } else {
            throw new EntityNotFoundException("Trip not found with id: " + id);
        }
    }
}
