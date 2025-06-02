package com.trippal.trippal_backend.controller;

import com.trippal.trippal_backend.dtos.TripDto;
import com.trippal.trippal_backend.model.Trip;
import com.trippal.trippal_backend.model.UserInfo;
import com.trippal.trippal_backend.service.CommunityService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CommunityControllerTest {

    @Mock
    private CommunityService communityService;

    @InjectMocks
    private CommunityController communityController;

    @Test
    public void testGetPublicTrips_ReturnsPaginatedDtos() {
        int page = 0;
        int size = 10;

        UserInfo user = new UserInfo();
        user.setId(1L);

        Trip trip = new Trip();
        trip.setUser(user);

        Page<Trip> mockTripPage = new PageImpl<>(Collections.singletonList(trip));

        when(communityService.getPublicTrips(page, size))
                .thenReturn(mockTripPage);

        Page<TripDto> result = communityController.getPublicTrips(page, size, null);

        // Check if 1 TripDto was returned
        assertEquals(1, result.getContent().size());
        // Check if conversion of Trip to TripDto happened
        assertInstanceOf(TripDto.class, result.getContent().getFirst());

        // Checks if service was called once
        verify(communityService, times(1)).getPublicTrips(page, size);
    }
}
