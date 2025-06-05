package com.trippal.trippal_backend.dtos;

import com.trippal.trippal_backend.model.TripComment;
import com.trippal.trippal_backend.model.UserInfo;
import com.trippal.trippal_backend.repository.UserInfoRepository;

import java.time.LocalDateTime;

public class TripCommentDto {
    private Long id;
    private String content;
    private String author;
    private LocalDateTime createdAt;

    public TripCommentDto() {
    }

    public TripCommentDto(TripComment tripComment, UserInfoRepository userInfoRepository) {
        this.id = tripComment.getId();
        this.content = tripComment.getContent();
        this.author = userInfoRepository.findById(tripComment.getAuthorId())
                .map(UserInfo::getName)
                .orElse("Unknown");
        this.createdAt = tripComment.getCreatedAt();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
