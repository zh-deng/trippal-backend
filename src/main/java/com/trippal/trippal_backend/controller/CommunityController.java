package com.trippal.trippal_backend.controller;

import com.trippal.trippal_backend.dtos.TripExtendedDto;
import com.trippal.trippal_backend.model.Trip;
import com.trippal.trippal_backend.repository.UserInfoRepository;
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
    private final UserInfoRepository userInfoRepository;

    @Autowired
    public CommunityController(CommunityService communityService, UserInfoRepository userInfoRepository) {
        this.communityService = communityService;
        this.userInfoRepository = userInfoRepository;
    }

    @GetMapping
    public Page<TripExtendedDto> getPublicTrips(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "6") int size,
            @RequestParam(required = false) String countryName
    ) {
        Page<Trip> tripPage = (countryName != null && !countryName.isBlank())
                ? communityService.getPublicTripsByCountry(page, size, countryName)
                : communityService.getPublicTrips(page, size);
        return tripPage.map(trip -> new TripExtendedDto(trip, userInfoRepository));
    }
}
