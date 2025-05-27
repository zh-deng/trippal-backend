package com.trippal.trippal_backend.service;

import com.trippal.trippal_backend.model.RoadmapItem;
import com.trippal.trippal_backend.model.Trip;
import com.trippal.trippal_backend.model.UserInfo;
import com.trippal.trippal_backend.repository.TripRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class TripServiceTest {

    @Mock
    TripRepository tripRepository;

    @InjectMocks
    TripService tripService;

    Trip trip;

    @BeforeEach
    public void initEach() {
        UserInfo user = new UserInfo();
        List<RoadmapItem> roadmapItems = new ArrayList<>();

        trip = new Trip();
        trip.setId(1L);
        trip.setTitle("MyTrip");
        trip.setUser(user);
        trip.setRoadmapItems(roadmapItems);
    }

    @Test
    public void getTripById_WithValidId_ShouldReturnTrip() {
        Mockito.when(tripRepository.findById(1L)).thenReturn(Optional.ofNullable(trip));
        Trip returnedTrip = tripService.getTripById(1L);

        Assertions.assertEquals(trip.getId(), returnedTrip.getId());
        Assertions.assertEquals(trip.getTitle(), returnedTrip.getTitle());
        Assertions.assertEquals(trip.getUser(), returnedTrip.getUser());
        Assertions.assertEquals(trip.getRoadmapItems(), returnedTrip.getRoadmapItems());
        Assertions.assertEquals(trip.isPublic(), returnedTrip.isPublic());
        Mockito.verify(tripRepository).findById(1L);
    }

    @Test
    public void getTripById_WithInvalidId_ShouldThrowEntityNotFoundException() {
        Long invalidId = 999L;

        Mockito.when(tripRepository.findById(invalidId)).thenReturn(Optional.empty());

        EntityNotFoundException exception = Assertions.assertThrows(EntityNotFoundException.class, () -> {
            tripService.getTripById(invalidId);
        });

        Assertions.assertEquals("Trip not found with id: 999", exception.getMessage());
        Mockito.verify(tripRepository).findById(invalidId);
    }

    @Test
    public void createTrip_WithValidTrip_ShouldReturnTrip() {
        Mockito.when(tripRepository.save(trip)).thenReturn(trip);
        Trip createdTrip = tripService.createTrip(trip);

        Assertions.assertEquals(trip.getId(), createdTrip.getId());
        Assertions.assertEquals(trip.getTitle(), createdTrip.getTitle());
        Assertions.assertEquals(trip.getUser(), createdTrip.getUser());
        Assertions.assertEquals(trip.getRoadmapItems(), createdTrip.getRoadmapItems());
        Assertions.assertEquals(trip.isPublic(), createdTrip.isPublic());
        Mockito.verify(tripRepository).save(Mockito.any(Trip.class));
    }

    @Test
    public void updateTrip_WithUpdatedTrip_ShouldReturnNewTrip() {
        Trip existingTrip = new Trip();
        existingTrip.setId(2L);
        existingTrip.setTitle("OldTitle");
        existingTrip.setUser(trip.getUser());
        existingTrip.setPublic(false);
        existingTrip.setRoadmapItems(new ArrayList<>());

        trip.setId(2L);
        trip.setTitle("MyNewTrip");
        trip.setPublic(true);
        trip.setRoadmapItems(List.of(new RoadmapItem()));

        Mockito.when(tripRepository.findById(2L)).thenReturn(Optional.of(existingTrip));
        Mockito.when(tripRepository.save(Mockito.any(Trip.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Trip updatedTrip = tripService.updateTrip(2L, trip);

        Assertions.assertEquals("MyNewTrip", updatedTrip.getTitle());
        Assertions.assertTrue(updatedTrip.isPublic());
        Assertions.assertEquals(1, updatedTrip.getRoadmapItems().size());
        Assertions.assertEquals(trip.getUser(), updatedTrip.getUser());
        Mockito.verify(tripRepository).findById(2L);
        Mockito.verify(tripRepository).save(Mockito.any(Trip.class));
    }

    @Test
    public void updateTrip_WithInvalidId_ShouldReturnEntityNotFoundException() {
        Long invalidId = 999L;

        Mockito.when(tripRepository.findById(invalidId)).thenReturn(Optional.empty());

        EntityNotFoundException exception = Assertions.assertThrows(EntityNotFoundException.class, () -> {
            tripService.updateTrip(invalidId, trip);
        });

        Assertions.assertEquals("Trip not found with id: 999", exception.getMessage());
        Mockito.verify(tripRepository).findById(invalidId);
    }

    @Test
    public void deleteTrip_WithValidId_ShouldReturnTrue() {
        Long validId = 1L;

        Mockito.when(tripRepository.findById(validId)).thenReturn(Optional.of(trip));

        boolean result = tripService.deleteTrip(validId);

        Assertions.assertTrue(result);
        Mockito.verify(tripRepository).findById(validId);
        Mockito.verify(tripRepository).deleteById(validId);
    }

    @Test
    public void deleteTrip_WithInvalidId_ShouldReturnFalse() {
        Long invalidId = 999L;

        Mockito.when(tripRepository.findById(invalidId)).thenReturn(Optional.empty());

        boolean result = tripService.deleteTrip(invalidId);

        Assertions.assertFalse(result);
        Mockito.verify(tripRepository).findById(invalidId);
        Mockito.verify(tripRepository, Mockito.never()).deleteById(Mockito.any());
    }
}
