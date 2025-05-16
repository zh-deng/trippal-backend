package com.trippal.trippal_backend.service;

import com.trippal.trippal_backend.dtos.RoadmapItemDto;
import com.trippal.trippal_backend.model.RoadmapItem;
import com.trippal.trippal_backend.model.Trip;
import com.trippal.trippal_backend.repository.RoadmapItemRepository;
import com.trippal.trippal_backend.repository.TripRepository;
import org.springframework.stereotype.Service;

@Service
public class RoadmapItemService {

    private final RoadmapItemRepository roadmapItemRepository;
    private final TripRepository tripRepository;

    public RoadmapItemService(RoadmapItemRepository roadmapItemRepository, TripRepository tripRepository) {
        this.roadmapItemRepository = roadmapItemRepository;
        this.tripRepository = tripRepository;
    }

    public RoadmapItem createRoadmapItem(RoadmapItemDto roadmapItemDto) {
        Trip trip = tripRepository.findById(roadmapItemDto.getTripId()).orElseThrow(() -> new RuntimeException("Trip not found"));

        RoadmapItem roadmapItem = new RoadmapItem(roadmapItemDto.getTitle(), roadmapItemDto.getDate(), roadmapItemDto.getCountry(), roadmapItemDto.getCity(), roadmapItemDto.getAttraction(), trip);

        return roadmapItemRepository.save(roadmapItem);
    }

}
