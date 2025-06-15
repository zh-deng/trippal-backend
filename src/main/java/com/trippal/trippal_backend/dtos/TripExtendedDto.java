package com.trippal.trippal_backend.dtos;

import com.trippal.trippal_backend.model.Trip;
import com.trippal.trippal_backend.model.UserInfo;
import com.trippal.trippal_backend.repository.UserInfoRepository;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class TripExtendedDto {
    private Long id;
    private String title;
    private boolean isPublic;
    private List<RoadmapItemDto> roadmapItems;
    private Long userId;
    private int stars;
    private List<TripCommentDto> comments;
    private boolean isStarredByCurrentUser;

    public TripExtendedDto() {
    }

    public TripExtendedDto(Trip trip, UserInfoRepository userInfoRepository, UserInfo currentUser) {
        this.id = trip.getId();
        this.title = trip.getTitle();
        this.isPublic = trip.isPublic();
        this.userId = trip.getUser().getId();
        this.roadmapItems = trip.getRoadmapItems().stream()
                .filter(Objects::nonNull)
                .map(RoadmapItemDto::new).collect(Collectors.toList());
        this.stars = trip.getStars();
        this.comments = trip.getComments().stream()
                .map(comment -> new TripCommentDto(comment, userInfoRepository))
                .collect(Collectors.toList());
        this.isStarredByCurrentUser = currentUser != null && trip.getStarredByUsers().stream()
                .anyMatch(u -> u.getId().equals(currentUser.getId()));
        ;
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

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public List<TripCommentDto> getComments() {
        return comments;
    }

    public void setComments(List<TripCommentDto> comments) {
        this.comments = comments;
    }

    public boolean isStarredByCurrentUser() {
        return isStarredByCurrentUser;
    }

    public void setStarredByCurrentUser(boolean starredByCurrentUser) {
        isStarredByCurrentUser = starredByCurrentUser;
    }
}