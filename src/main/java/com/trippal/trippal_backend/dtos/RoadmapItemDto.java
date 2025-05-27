package com.trippal.trippal_backend.dtos;

import com.trippal.trippal_backend.model.RoadmapItem;
import com.trippal.trippal_backend.model.UploadedFile;
import com.trippal.trippal_backend.model.embeddable.Attraction;
import com.trippal.trippal_backend.model.embeddable.City;
import com.trippal.trippal_backend.model.embeddable.Country;

import java.util.Date;
import java.util.List;

public class RoadmapItemDto {
    private Long id;
    private String title;
    private Date date;
    private List<UploadedFile> files;
    private String notes;
    private Country country;
    private City city;
    private Attraction attraction;
    private Long tripId;

    public RoadmapItemDto() {
    }

    public RoadmapItemDto(RoadmapItem roadmapItem) {
        this.id = roadmapItem.getId();
        this.title = roadmapItem.getTitle();
        this.date = roadmapItem.getDate();
        this.notes = roadmapItem.getNotes();
        this.files = roadmapItem.getFiles();
        this.country = roadmapItem.getCountry();
        this.city = roadmapItem.getCity();
        this.attraction = roadmapItem.getAttraction();
        this.tripId = roadmapItem.getTrip().getId();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Attraction getAttraction() {
        return attraction;
    }

    public void setAttraction(Attraction attraction) {
        this.attraction = attraction;
    }

    public Long getTripId() {
        return tripId;
    }

    public void setTripId(Long tripId) {
        this.tripId = tripId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public List<UploadedFile> getFiles() {
        return files;
    }

    public void setFiles(List<UploadedFile> files) {
        this.files = files;
    }
}
