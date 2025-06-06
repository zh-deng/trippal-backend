package com.trippal.trippal_backend.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Trip extends BaseEntity {

    @Column(nullable = false)
    private String title;

    private boolean isPublic = false;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now(); // Set the creation time before saving
    }

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserInfo userInfo;

    @OneToMany(mappedBy = "trip", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RoadmapItem> roadmapItems = new ArrayList<>();

    @OneToMany(mappedBy = "trip", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TripComment> comments = new ArrayList<>();

    @Column
    private int stars = 0;

    public Trip() {
    }

    public Trip(String title, UserInfo userInfo) {
        this.title = title;
        this.userInfo = userInfo;
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

    public void setPublic(boolean isPublic) {
        this.isPublic = isPublic;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public UserInfo getUser() {
        return userInfo;
    }

    public void setUser(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public List<RoadmapItem> getRoadmapItems() {
        return roadmapItems;
    }

    public void setRoadmapItems(List<RoadmapItem> roadmapItems) {
        this.roadmapItems = roadmapItems;
    }

    public void addRoadmapItem(RoadmapItem roadmapItem) {
        roadmapItems.add(roadmapItem);
        roadmapItem.setTrip(this);
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public List<TripComment> getComments() {
        return comments;
    }

    public void setComments(List<TripComment> comments) {
        this.comments = comments;
    }

    public void addComment(TripComment tripComment) {
        comments.add(tripComment);
        tripComment.setTrip(this);
    }
}
