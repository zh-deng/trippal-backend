package com.trippal.trippal_backend.service;

import com.trippal.trippal_backend.model.RoadmapItem;
import com.trippal.trippal_backend.model.Trip;
import com.trippal.trippal_backend.model.UserInfo;
import com.trippal.trippal_backend.repository.TripRepository;
import com.trippal.trippal_backend.repository.UserInfoRepository;
import com.trippal.trippal_backend.util.TripCloner;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class TripService {

    private final TripRepository tripRepository;
    private final UserInfoRepository userInfoRepository;
    private final TripCloner tripCloner;
    private final UserInfoService userInfoService;

    @Autowired
    public TripService(TripRepository tripRepository, UserInfoRepository userInfoRepository, TripCloner tripCloner, UserInfoService userInfoService) {
        this.tripRepository = tripRepository;
        this.userInfoRepository = userInfoRepository;
        this.tripCloner = tripCloner;
        this.userInfoService = userInfoService;
    }

    public Trip getTripById(Long id) {
        return tripRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Trip not found with id: " + id));
    }

    public Trip createTrip(Trip trip) {
        return tripRepository.save(trip);
    }

    @Transactional
    public Trip updateTrip(Long id, Trip updatedTrip) {
        Trip trip = tripRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Trip not found with id: " + id));

        trip.setTitle(updatedTrip.getTitle());
        trip.setPublic(updatedTrip.isPublic());
        trip.getRoadmapItems().clear();
        trip.getRoadmapItems().addAll(updatedTrip.getRoadmapItems());

        return tripRepository.save(trip);
    }

    @Transactional
    public boolean deleteTrip(Long id) {
        if (tripRepository.existsById(id)) {
            tripRepository.deleteById(id);
            return true;
        }

        return false;
    }

    @Transactional
    public void togglePublishStatus(Long id) {
        Trip trip = tripRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Trip not found with id: " + id));

        trip.setPublic(!trip.isPublic());
    }

    @Transactional
    public void toggleStarStatus(Long tripId) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserInfo user = userInfoService.getUserByEmail(userDetails.getUsername());

        Trip trip = tripRepository.findById(tripId)
                .orElseThrow(() -> new EntityNotFoundException("Trip not found"));

        if (!user.getStarredTrips().contains(trip)) {
            user.getStarredTrips().add(trip);
            trip.getStarredByUsers().add(user);
        } else {
            user.getStarredTrips().remove(trip);
            trip.getStarredByUsers().remove(user);
        }

        userInfoRepository.save(user);
        tripRepository.save(trip);
    }

    @Transactional
    public Trip saveSharedTrip(Long id) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserInfo user = userInfoService.getUserByEmail(userDetails.getUsername());

        Trip trip = tripRepository.findById(id).
                orElseThrow(() -> new EntityNotFoundException("Trip not found"));

        Trip copiedTrip = tripCloner.deepCopyTrip(trip, user);

        return tripRepository.save(copiedTrip);
    }

    @Transactional
    public void reorderRoadmapItems(Long tripId, List<Long> newOrderIds) {
        Trip trip = tripRepository.findById(tripId)
                .orElseThrow(() -> new EntityNotFoundException("Trip not found"));

        Map<Long, RoadmapItem> itemMap = trip.getRoadmapItems().stream()
                .collect(Collectors.toMap(RoadmapItem::getId, Function.identity()));

        List<RoadmapItem> reorderedList = new ArrayList<>();

        for (Long id : newOrderIds) {
            RoadmapItem item = itemMap.get(id);
            if (item == null) {
                throw new IllegalArgumentException("RoadmapItem ID " + id + " is not part of this trip.");
            }

            reorderedList.add(item);
        }

        trip.getRoadmapItems().clear();
        trip.getRoadmapItems().addAll(reorderedList);

        tripRepository.save(trip);
    }
}
