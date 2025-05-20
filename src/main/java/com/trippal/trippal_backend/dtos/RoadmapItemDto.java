package com.trippal.trippal_backend.dtos;

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

    public RoadmapItemDto(Long id, String title, Date date, String notes, List<UploadedFile> files, Country country, City city, Attraction attraction, Long tripId) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.notes = notes;
        this.files = files;
        this.country = country;
        this.city = city;
        this.attraction = attraction;
        this.tripId = tripId;
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
