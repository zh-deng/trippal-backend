package com.trippal.trippal_backend.model;

import jakarta.persistence.*;

@Entity
public class UploadedFile extends BaseEntity {

    private String name;
    private String url;

    @ManyToOne
    @JoinColumn(name = "roadmap_item_id")
    private RoadmapItem roadmapItem;

    public UploadedFile() {}

    public UploadedFile(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public RoadmapItem getRoadmapItem() {
        return roadmapItem;
    }

    public void setRoadmapItem(RoadmapItem roadmapItem) {
        this.roadmapItem = roadmapItem;
    }
}
