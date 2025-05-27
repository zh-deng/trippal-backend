package com.trippal.trippal_backend.service;

import com.trippal.trippal_backend.dtos.RoadmapItemDto;
import com.trippal.trippal_backend.model.RoadmapItem;
import com.trippal.trippal_backend.model.Trip;
import com.trippal.trippal_backend.model.UploadedFile;
import com.trippal.trippal_backend.model.embeddable.Attraction;
import com.trippal.trippal_backend.model.embeddable.City;
import com.trippal.trippal_backend.model.embeddable.Country;
import com.trippal.trippal_backend.repository.RoadmapItemRepository;
import com.trippal.trippal_backend.repository.TripRepository;
import com.trippal.trippal_backend.repository.UploadedFileRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RoadmapItemServiceTest {

    @Mock
    private RoadmapItemRepository roadmapItemRepository;

    @Mock
    private TripRepository tripRepository;

    @Mock
    private UploadedFileRepository uploadedFileRepository;

    @InjectMocks
    private RoadmapItemService roadmapItemService;

    private Trip trip;
    private RoadmapItemDto roadmapItemDto;
    private RoadmapItem roadmapItem;

    private static final Date FIXED_DATE = new GregorianCalendar(2024, Calendar.JANUARY, 1).getTime();


    @BeforeEach
    void setUp() {
        trip = new Trip();
        trip.setId(1L);

        roadmapItemDto = new RoadmapItemDto();
        roadmapItemDto.setTripId(1L);
        roadmapItemDto.setTitle("Visit Eiffel Tower");
        roadmapItemDto.setDate(FIXED_DATE);
        roadmapItemDto.setNotes("Best view at sunset");
        roadmapItemDto.setCountry(new Country("France", 10.5, 10.5));
        roadmapItemDto.setCity(new City("Paris", 11.5, 11.5));
        roadmapItemDto.setAttraction(new Attraction("Eiffel Tower", 12.5, 12.5));

        UploadedFile fileDto = new UploadedFile();
        fileDto.setName("photo.jpg");
        fileDto.setUrl("http://example.com/photo.jpg");
        roadmapItemDto.setFiles(List.of(fileDto));

        roadmapItem = new RoadmapItem();
        roadmapItem.setId(1L);
        roadmapItem.setTrip(trip);
    }

    @Test
    void getRoadmapItemById_WithValidId_ShouldReturnItem() {
        when(roadmapItemRepository.findById(1L)).thenReturn(Optional.of(roadmapItem));

        RoadmapItem result = roadmapItemService.getRoadmapItemById(1L);

        assertEquals(roadmapItem, result);
        verify(roadmapItemRepository).findById(1L);
    }

    @Test
    void getRoadmapItemById_WithInvalidId_ShouldThrowException() {
        when(roadmapItemRepository.findById(2L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> roadmapItemService.getRoadmapItemById(2L));
    }

    @Test
    void createRoadmapItem_ShouldSaveAndReturnItemWithFiles() {
        when(tripRepository.findById(1L)).thenReturn(Optional.of(trip));
        when(roadmapItemRepository.save(any(RoadmapItem.class))).thenReturn(roadmapItem);

        RoadmapItem result = roadmapItemService.createRoadmapItem(roadmapItemDto);

        assertEquals(roadmapItem.getTrip(), result.getTrip());
        verify(tripRepository).findById(1L);
        verify(roadmapItemRepository).save(any(RoadmapItem.class));
        verify(uploadedFileRepository).saveAll(anyList());
    }

    @Test
    void updateRoadmapItem_WithInvalidId_ShouldThrowException() {
        when(roadmapItemRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> roadmapItemService.updateRoadmapItem(roadmapItemDto));
    }

    @Test
    void deleteRoadmapItem_WithValidId_ShouldReturnTrue() {
        when(roadmapItemRepository.findById(1L)).thenReturn(Optional.of(roadmapItem));

        boolean result = roadmapItemService.deleteRoadmapItem(1L);

        assertTrue(result);
        verify(roadmapItemRepository).deleteById(1L);
    }

    @Test
    void deleteRoadmapItem_WithInvalidId_ShouldReturnFalse() {
        when(roadmapItemRepository.findById(1L)).thenReturn(Optional.empty());

        boolean result = roadmapItemService.deleteRoadmapItem(1L);

        assertFalse(result);
        verify(roadmapItemRepository, never()).deleteById(any());
    }
}
