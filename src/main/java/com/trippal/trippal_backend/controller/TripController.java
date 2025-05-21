package com.trippal.trippal_backend.controller;

import com.trippal.trippal_backend.dtos.RoadmapItemPreviewDto;
import com.trippal.trippal_backend.dtos.TripDto;
import com.trippal.trippal_backend.model.Trip;
import com.trippal.trippal_backend.model.UserInfo;
import com.trippal.trippal_backend.service.TripService;
import com.trippal.trippal_backend.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/trip")
public class TripController {

    private final TripService tripService;
    private final UserInfoService userInfoService;

    @Autowired
    public TripController(TripService tripService, UserInfoService userInfoService) {
        this.tripService = tripService;
        this.userInfoService = userInfoService;
    }

    @GetMapping("/{id}/roadmapList")
    public ResponseEntity<List<RoadmapItemPreviewDto>> getRoadmapList(@PathVariable Long id) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (userDetails != null) {
            Trip existingTrip = tripService.getTripById(id);
            List<RoadmapItemPreviewDto> roadmapItemPreviewDtos = existingTrip.getRoadmapItems().stream()
                    .map(item -> new RoadmapItemPreviewDto(item.getId(), item.getTitle()))
                    .collect(Collectors.toList());

            return ResponseEntity.ok(roadmapItemPreviewDtos);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    };

    @PostMapping
    public ResponseEntity<TripDto> createTrip(@RequestBody Trip trip) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (userDetails != null) {
            UserInfo user = userInfoService.getUserByEmail(userDetails.getUsername());

            Trip createdTrip = tripService.createTrip(new Trip(trip.getTitle(), user));
            TripDto tripDto = new TripDto(createdTrip.getId(), createdTrip.getTitle(), createdTrip.isPublic(), user.getId(), createdTrip.getRoadmapItems());

            return ResponseEntity.status(HttpStatus.CREATED).body(tripDto);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<TripDto> updateTrip(@RequestBody Trip trip, @PathVariable Long id) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (userDetails != null) {
            UserInfo user = userInfoService.getUserByEmail(userDetails.getUsername());

            Trip updatedTrip = tripService.updateTrip(id, trip, user);
            TripDto tripDto = new TripDto(updatedTrip.getId(), updatedTrip.getTitle(), updatedTrip.isPublic(), user.getId(), updatedTrip.getRoadmapItems());

            return ResponseEntity.status(HttpStatus.OK).body(tripDto);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrip(@PathVariable Long id) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (userDetails != null) {
            UserInfo user = userInfoService.getUserByEmail(userDetails.getUsername());
            boolean deleted = tripService.deleteTrip(id, user);

            if (deleted) {
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
