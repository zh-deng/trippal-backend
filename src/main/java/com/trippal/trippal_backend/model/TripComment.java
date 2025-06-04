package com.trippal.trippal_backend.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class TripComment extends BaseEntity {

    @Column
    private String content;

    @Column
    private Long authorId;

    @ManyToOne
    @JoinColumn(name = "trip_id", nullable = false)
    private Trip trip;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now(); // Set the creation time before saving
    }

    public TripComment() {
    }

    public TripComment(String content, Long authorId, Trip trip) {
        this.content = content;
        this.authorId = authorId;
        this.trip = trip;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }
}
