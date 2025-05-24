package com.trippal.trippal_backend.dtos;

import com.trippal.trippal_backend.model.RoadmapItem;

public class RoadmapItemPreviewDto {
    private Long id;
    private String title;

    public RoadmapItemPreviewDto() {
    }

    public RoadmapItemPreviewDto(RoadmapItem roadmapItem) {
        this.id = roadmapItem.getId();
        this.title = roadmapItem.getTitle();
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
}
