package com.trippal.trippal_backend.dtos;

import java.util.List;

public class RoadmapReorderRequestDto {
    private List<Long> roadmapItemIds;

    public List<Long> getRoadmapItemIds() {
        return roadmapItemIds;
    }

    public void setRoadmapItemIds(List<Long> roadmapItemIds) {
        this.roadmapItemIds = roadmapItemIds;
    }
}
