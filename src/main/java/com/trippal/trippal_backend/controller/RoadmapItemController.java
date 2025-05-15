package com.trippal.trippal_backend.controller;

import com.trippal.trippal_backend.model.RoadmapItem;
import com.trippal.trippal_backend.repository.RoadmapItemRepository;
import com.trippal.trippal_backend.service.RoadmapItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/roadmap")
public class RoadmapItemController {

    private final RoadmapItemService roadmapItemService;

    public RoadmapItemController(RoadmapItemService roadmapItemService) {
        this.roadmapItemService = roadmapItemService;
    }

    @PostMapping
    public ResponseEntity<RoadmapItem> create(@RequestBody RoadmapItem item) {
        RoadmapItem saved = roadmapItemService.createRoadmapItem(item);
        return ResponseEntity.ok(saved);
    }
}
