package com.trippal.trippal_backend.service;

import com.trippal.trippal_backend.model.Trip;
import com.trippal.trippal_backend.repository.TripRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class CommunityServiceTest {

    @Mock
    private TripRepository tripRepository;

    @InjectMocks
    private CommunityService communityService;

    @Test
    public void testGetPublicTrips_ReturnsPaginatedTrips() {
        int page = 0;
        int size = 2;
        Pageable pageable = PageRequest.of(page, size);

        List<Trip> tripList = Arrays.asList(new Trip(), new Trip());
        Page<Trip> tripPage = new PageImpl<>(tripList, pageable, tripList.size());

        Mockito.when(tripRepository.findByPublicTrue(pageable)).thenReturn(tripPage);

        Page<Trip> result = communityService.getPublicTrips(page, size);

        assertNotNull(result);
        assertEquals(2, result.getContent().size());
        Mockito.verify(tripRepository, Mockito.times(1)).findByPublicTrue(pageable);
    }
}
