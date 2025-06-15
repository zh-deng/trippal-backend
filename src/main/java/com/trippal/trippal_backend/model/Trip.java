package com.trippal.trippal_backend.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    @OrderColumn(name = "position")
    private List<RoadmapItem> roadmapItems = new ArrayList<>();

    @OneToMany(mappedBy = "trip", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TripComment> comments = new ArrayList<>();

    @ManyToMany(mappedBy = "starredTrips")
    private Set<UserInfo> starredByUsers = new HashSet<>();

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

    public Set<UserInfo> getStarredByUsers() {
        return starredByUsers;
    }

    public void setStarredByUsers(Set<UserInfo> starredByUsers) {
        this.starredByUsers = starredByUsers;
    }

    public int getStars() {
        return starredByUsers.size();
    }
}
