package com.trippal.trippal_backend.controller;

import com.trippal.trippal_backend.dtos.RoadmapItemDto;
import com.trippal.trippal_backend.model.RoadmapItem;
import com.trippal.trippal_backend.service.RoadmapItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/roadmapItem")
public class RoadmapItemController {

    private final RoadmapItemService roadmapItemService;

    @Autowired
    public RoadmapItemController(RoadmapItemService roadmapItemService) {
        this.roadmapItemService = roadmapItemService;
    }

    @PostMapping
    public ResponseEntity<RoadmapItemDto> createRoadmapItem(@RequestBody RoadmapItemDto item) {
        RoadmapItem createdRoadmapItem = roadmapItemService.createRoadmapItem(item);
        RoadmapItemDto roadmapItemDto = new RoadmapItemDto(createdRoadmapItem);

        return ResponseEntity.ok(roadmapItemDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoadmapItemDto> getRoadmapItemById(@PathVariable Long id) {
        RoadmapItem existingRoadmapItem = roadmapItemService.getRoadmapItemById(id);
        RoadmapItemDto roadmapItemDto = new RoadmapItemDto(existingRoadmapItem);

        return ResponseEntity.ok(roadmapItemDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoadmapItemDto> updateRoadmapItem(@RequestBody RoadmapItemDto item, @PathVariable Long id) {
        RoadmapItem updatedRoadmapItem = roadmapItemService.updateRoadmapItem(item);
        RoadmapItemDto updatedRoadmapItemDto = new RoadmapItemDto(updatedRoadmapItem);

        return ResponseEntity.ok(updatedRoadmapItemDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoadmapItem(@PathVariable Long id) {
        boolean deleted = roadmapItemService.deleteRoadmapItem(id);

        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }
}
