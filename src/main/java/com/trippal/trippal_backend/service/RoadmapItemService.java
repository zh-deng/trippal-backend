package com.trippal.trippal_backend.service;

import com.trippal.trippal_backend.model.RoadmapItem;
import com.trippal.trippal_backend.repository.RoadmapItemRepository;
import org.springframework.stereotype.Service;

@Service
public class RoadmapItemService {

    private final RoadmapItemRepository roadmapItemRepository;

    public RoadmapItemService(RoadmapItemRepository roadmapItemRepository) {
        this.roadmapItemRepository = roadmapItemRepository;
    }

    public RoadmapItem createRoadmapItem(RoadmapItem item) {
        return roadmapItemRepository.save(item);
    }

}
