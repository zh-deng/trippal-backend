package com.trippal.trippal_backend.dtos;

public class RoadmapItemPreviewDto {
    private Long id;
    private String title;

    public RoadmapItemPreviewDto(Long id, String title) {
        this.id = id;
        this.title = title;
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
