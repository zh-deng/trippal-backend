package com.trippal.trippal_backend.dtos;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.List;

public class UserInfoDto {

    private Long id;
    private String name;
    private String email;
    private List<TripDto> trips;

    // All-argument constructor
    public UserInfoDto(Long id, String name, String email, List<TripDto> trips) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.trips = trips;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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