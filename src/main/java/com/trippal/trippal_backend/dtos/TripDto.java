package com.trippal.trippal_backend.dtos;

import com.trippal.trippal_backend.model.Trip;

import java.util.List;
import java.util.stream.Collectors;

public class TripDto {
    private Long id;
    private String title;
    private boolean isPublic;
    private List<RoadmapItemDto> roadmapItems;
    private Long userId;

    public TripDto() {
    }

    public TripDto(Trip trip) {
        this.id = trip.getId();
        this.title = trip.getTitle();
        this.isPublic = trip.isPublic();
        this.userId = trip.getUser().getId();
        this.roadmapItems = trip.getRoadmapItems().stream().map(RoadmapItemDto::new).collect(Collectors.toList());
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
    }

    public List<RoadmapItemDto> getRoadmapItems() {
        return roadmapItems;
    }

    public void setRoadmapItems(List<RoadmapItemDto> roadmapItems) {
        this.roadmapItems = roadmapItems;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
