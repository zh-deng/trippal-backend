package com.trippal.trippal_backend.controller;

import com.trippal.trippal_backend.dtos.TripDto;
import com.trippal.trippal_backend.model.Trip;
import com.trippal.trippal_backend.model.UserInfo;
import com.trippal.trippal_backend.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/trip")
public class TripController {

    private final TripService tripService;

    @Autowired
    public TripController(TripService tripService) {
        this.tripService = tripService;
    }

    @PostMapping
    public ResponseEntity<TripDto> createTrip(@RequestBody Trip trip, @AuthenticationPrincipal UserInfo user) {
        trip.setUser(user);
        Trip createdTrip = tripService.createTrip(trip);
        TripDto tripDto = new TripDto(createdTrip.getId(), createdTrip.getTitle(), createdTrip.isPublic(), user.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(tripDto);
    }
}
