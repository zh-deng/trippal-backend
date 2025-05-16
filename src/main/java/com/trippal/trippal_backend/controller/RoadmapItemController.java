package com.trippal.trippal_backend.controller;

import com.trippal.trippal_backend.dtos.RoadmapItemDto;
import com.trippal.trippal_backend.model.RoadmapItem;
import com.trippal.trippal_backend.service.RoadmapItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/roadmapItem")
public class RoadmapItemController {

    private final RoadmapItemService roadmapItemService;

    public RoadmapItemController(RoadmapItemService roadmapItemService) {
        this.roadmapItemService = roadmapItemService;
    }

    @PostMapping
    public ResponseEntity<RoadmapItemDto> createRoadmapItem(@RequestBody RoadmapItemDto item) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (userDetails != null) {
            RoadmapItem createdRoadmapItem = roadmapItemService.createRoadmapItem(item);
            RoadmapItemDto roadmapItemDto = new RoadmapItemDto(createdRoadmapItem.getId(), createdRoadmapItem.getTitle(), createdRoadmapItem.getDate(), createdRoadmapItem.getCountry(), createdRoadmapItem.getCity(), createdRoadmapItem.getAttraction(), createdRoadmapItem.getTrip().getId());

            return ResponseEntity.ok(roadmapItemDto);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

    }
}
