package com.trippal.trippal_backend.controller;

import com.trippal.trippal_backend.dtos.TripDto;
import com.trippal.trippal_backend.model.Trip;
import com.trippal.trippal_backend.service.CommunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/community")
public class CommunityController {

    private final CommunityService communityService;

    @Autowired
    public CommunityController(CommunityService communityService) {
        this.communityService = communityService;
    }

    @GetMapping
    public Page<TripDto> getPublicTrips(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<Trip> tripPage = communityService.getPublicTrips(page, size);

        return tripPage.map(TripDto::new);
    }

    @GetMapping
    public Page<TripDto> getPublicTripsByCountry(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam String countryName
    ) {
        Page<Trip> tripPage = communityService.getPublicTripsByCountry(page, size, countryName);

        return tripPage.map(TripDto::new);
    }
}
