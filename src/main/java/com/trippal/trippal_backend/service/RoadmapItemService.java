package com.trippal.trippal_backend.service;

import com.trippal.trippal_backend.dtos.RoadmapItemDto;
import com.trippal.trippal_backend.model.RoadmapItem;
import com.trippal.trippal_backend.model.Trip;
import com.trippal.trippal_backend.repository.RoadmapItemRepository;
import com.trippal.trippal_backend.repository.TripRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoadmapItemService {

    private final RoadmapItemRepository roadmapItemRepository;
    private final TripRepository tripRepository;

    public RoadmapItemService(RoadmapItemRepository roadmapItemRepository, TripRepository tripRepository) {
        this.roadmapItemRepository = roadmapItemRepository;
        this.tripRepository = tripRepository;
    }

    public RoadmapItem getRoadmapItemById(Long id) {
        return roadmapItemRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Roadmap item not found with id: " + id));
    };

    public RoadmapItem createRoadmapItem(RoadmapItemDto roadmapItemDto) {
        Trip trip = tripRepository.findById(roadmapItemDto.getTripId()).orElseThrow(() -> new RuntimeException("Trip not found"));

        RoadmapItem roadmapItem = new RoadmapItem(roadmapItemDto.getTitle(), roadmapItemDto.getDate(), roadmapItemDto.getCountry(), roadmapItemDto.getCity(), roadmapItemDto.getAttraction(), trip);

        return roadmapItemRepository.save(roadmapItem);
    }

}
