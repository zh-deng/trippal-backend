package com.trippal.trippal_backend.controller;

import com.trippal.trippal_backend.dtos.RoadmapItemDto;
import com.trippal.trippal_backend.model.RoadmapItem;
import com.trippal.trippal_backend.model.Trip;
import com.trippal.trippal_backend.service.RoadmapItemService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RoadmapItemControllerTest {

    @Mock
    private RoadmapItemService roadmapItemService;

    @InjectMocks
    private RoadmapItemController roadmapItemController;

    @BeforeEach
    void initEach() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateRoadmapItem() {
        RoadmapItemDto inputDto = new RoadmapItemDto();
        RoadmapItem mockItem = new RoadmapItem();
        Trip trip = new Trip();
        trip.setId(1L);
        mockItem.setTrip(trip);
        RoadmapItemDto expectedDto = new RoadmapItemDto(mockItem);

        when(roadmapItemService.createRoadmapItem(inputDto)).thenReturn(mockItem);

        ResponseEntity<RoadmapItemDto> response = roadmapItemController.createRoadmapItem(inputDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        RoadmapItemDto actualDto = response.getBody();
        assertNotNull(actualDto);
        assertEquals(expectedDto.getTripId(), actualDto.getTripId());
        assertEquals(expectedDto.getId(), actualDto.getId());
        assertEquals(expectedDto.getTitle(), actualDto.getTitle());

        verify(roadmapItemService, times(1)).createRoadmapItem(inputDto);
    }

    @Test
    void testGetRoadmapItemById() {
        Long id = 1L;
        RoadmapItem mockItem = new RoadmapItem();
        Trip trip = new Trip();
        trip.setId(1L);
        mockItem.setTrip(trip);
        RoadmapItemDto expectedDto = new RoadmapItemDto(mockItem);

        when(roadmapItemService.getRoadmapItemById(id)).thenReturn(mockItem);

        ResponseEntity<RoadmapItemDto> response = roadmapItemController.getRoadmapItemById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        RoadmapItemDto actualDto = response.getBody();
        assertNotNull(actualDto);
        assertEquals(expectedDto.getTripId(), actualDto.getTripId());
        assertEquals(expectedDto.getId(), actualDto.getId());
        assertEquals(expectedDto.getTitle(), actualDto.getTitle());

        verify(roadmapItemService, times(1)).getRoadmapItemById(id);
    }

    @Test
    void testUpdateRoadmapItem() {
        Long id = 1L;
        RoadmapItemDto inputDto = new RoadmapItemDto();
        RoadmapItem mockItem = new RoadmapItem();
        Trip trip = new Trip();
        trip.setId(1L);
        mockItem.setTrip(trip);
        RoadmapItemDto expectedDto = new RoadmapItemDto(mockItem);

        when(roadmapItemService.updateRoadmapItem(inputDto)).thenReturn(mockItem);

        ResponseEntity<RoadmapItemDto> response = roadmapItemController.updateRoadmapItem(inputDto, id);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        RoadmapItemDto actualDto = response.getBody();
        assertNotNull(actualDto);
        assertEquals(expectedDto.getTripId(), actualDto.getTripId());
        assertEquals(expectedDto.getId(), actualDto.getId());
        assertEquals(expectedDto.getTitle(), actualDto.getTitle());

        verify(roadmapItemService, times(1)).updateRoadmapItem(inputDto);
    }

    @Test
    void testDeleteRoadmapItemSuccess() {
        Long id = 1L;
        when(roadmapItemService.deleteRoadmapItem(id)).thenReturn(true);

        ResponseEntity<Void> response = roadmapItemController.deleteRoadmapItem(id);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(roadmapItemService, times(1)).deleteRoadmapItem(id);
    }

    @Test
    void testDeleteRoadmapItemFailure() {
        Long id = 2L;
        when(roadmapItemService.deleteRoadmapItem(id)).thenReturn(false);

        ResponseEntity<Void> response = roadmapItemController.deleteRoadmapItem(id);

        assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
        verify(roadmapItemService, times(1)).deleteRoadmapItem(id);
    }
}
