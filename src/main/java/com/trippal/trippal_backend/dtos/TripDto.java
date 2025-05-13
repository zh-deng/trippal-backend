package com.trippal.trippal_backend.dtos;

public class TripDto {
    private Long id;
    private String title;
    private boolean isPublic;
    private Long userId;

    public TripDto(Long id, String title, boolean isPublic, Long userId) {
        this.id = id;
        this.title = title;
        this.isPublic = isPublic;
        this.userId = userId;
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
