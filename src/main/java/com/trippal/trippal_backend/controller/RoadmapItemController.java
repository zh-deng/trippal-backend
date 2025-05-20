package com.trippal.trippal_backend.controller;

import com.trippal.trippal_backend.dtos.RoadmapItemDto;
import com.trippal.trippal_backend.dtos.TripDto;
import com.trippal.trippal_backend.model.RoadmapItem;
import com.trippal.trippal_backend.model.Trip;
import com.trippal.trippal_backend.model.UserInfo;
import com.trippal.trippal_backend.service.RoadmapItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/roadmapItem")
public class RoadmapItemController {

    private final RoadmapItemService roadmapItemService;

    public RoadmapItemController(RoadmapItemService roadmapItemService) {
        this.roadmapItemService = roadmapItemService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoadmapItemDto> getRoadmapItemById(@PathVariable Long id) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (userDetails != null) {
            RoadmapItem existingRoadmapItem = roadmapItemService.getRoadmapItemById(id);
            RoadmapItemDto roadmapItemDto = new RoadmapItemDto(existingRoadmapItem.getId(), existingRoadmapItem.getTitle(), existingRoadmapItem.getDate(), existingRoadmapItem.getNotes(), existingRoadmapItem.getFiles(), existingRoadmapItem.getCountry(), existingRoadmapItem.getCity(), existingRoadmapItem.getAttraction(), existingRoadmapItem.getTrip().getId());

            return ResponseEntity.ok(roadmapItemDto);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    };

    @PostMapping
    public ResponseEntity<RoadmapItemDto> createRoadmapItem(@RequestBody RoadmapItemDto item) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (userDetails != null) {
            RoadmapItem createdRoadmapItem = roadmapItemService.createRoadmapItem(item);
            RoadmapItemDto roadmapItemDto = new RoadmapItemDto(createdRoadmapItem.getId(), createdRoadmapItem.getTitle(), createdRoadmapItem.getDate(), createdRoadmapItem.getNotes(), createdRoadmapItem.getFiles(), createdRoadmapItem.getCountry(), createdRoadmapItem.getCity(), createdRoadmapItem.getAttraction(), createdRoadmapItem.getTrip().getId());

            return ResponseEntity.ok(roadmapItemDto);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoadmapItemDto> updateTrip(@RequestBody RoadmapItemDto item, @PathVariable Long id) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (userDetails != null) {
            RoadmapItem updatedRoadmapItem = roadmapItemService.updateRoadmapItem(item);
            RoadmapItemDto updatedRoadmapItemDto = new RoadmapItemDto(updatedRoadmapItem.getId(), updatedRoadmapItem.getTitle(), updatedRoadmapItem.getDate(), updatedRoadmapItem.getNotes(), updatedRoadmapItem.getFiles(), updatedRoadmapItem.getCountry(), updatedRoadmapItem.getCity(), updatedRoadmapItem.getAttraction(), updatedRoadmapItem.getTrip().getId());

            return ResponseEntity.ok(updatedRoadmapItemDto);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }
}
