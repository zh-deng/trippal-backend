package com.trippal.trippal_backend.dtos;

import com.trippal.trippal_backend.model.UserInfo;

import java.util.List;

public class UserInfoDto {

    private Long id;
    private String name;
    private String email;
    private List<TripDto> trips;

    public UserInfoDto() {
    }

    public UserInfoDto(UserInfo userInfo, List<TripDto> trips) {
        this.id = userInfo.getId();
        this.name = userInfo.getName();
        this.email = userInfo.getEmail();
        this.trips = trips;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<TripDto> getTrips() {
        return trips;
    }

    public void setTrips(List<TripDto> trips) {
        this.trips = trips;
    }
}