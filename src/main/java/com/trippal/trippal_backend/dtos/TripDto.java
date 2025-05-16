package com.trippal.trippal_backend.dtos;

import com.trippal.trippal_backend.model.RoadmapItem;

import java.util.List;

public class TripDto {
    private Long id;
    private String title;
    private boolean isPublic;
    private List<RoadmapItem> roadmapItems;
    private Long userId;

    public TripDto(Long id, String title, boolean isPublic, Long userId, List<RoadmapItem> roadmapItems) {
        this.id = id;
        this.title = title;
        this.isPublic = isPublic;
        this.userId = userId;
        this.roadmapItems = roadmapItems;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public Long getUserId() {
        return userId;
    }
}
