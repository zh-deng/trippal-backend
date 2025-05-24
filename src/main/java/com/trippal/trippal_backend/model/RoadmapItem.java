package com.trippal.trippal_backend.model;

import com.trippal.trippal_backend.dtos.RoadmapItemDto;
import com.trippal.trippal_backend.model.embeddable.Attraction;
import com.trippal.trippal_backend.model.embeddable.City;
import com.trippal.trippal_backend.model.embeddable.Country;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class RoadmapItem extends BaseEntity {

    @Column(nullable = false)
    private String title;

    @Column
    private Date date;

    @Column
    private String notes;

    @OneToMany(mappedBy = "roadmapItem", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UploadedFile> files = new ArrayList<>();

    @Embedded
    private Country country;

    @Embedded
    private City city;

    @Embedded
    private Attraction attraction;

    @ManyToOne
    @JoinColumn(name = "trip_id", nullable = false)
    private Trip trip;

    public RoadmapItem() {
    }

    public RoadmapItem(String title, Date date, String notes, Country country, City city, Attraction attraction, Trip trip) {
        this.title = title;
        this.date = date;
        this.notes = notes;
        this.country = country;
        this.city = city;
        this.attraction = attraction;
        this.trip = trip;
    }

    public RoadmapItem(RoadmapItemDto roadmapItemDto, Trip trip) {
        this.title = roadmapItemDto.getTitle();
        this.date = roadmapItemDto.getDate();
        this.notes = roadmapItemDto.getNotes();
        this.country = roadmapItemDto.getCountry();
        this.city = roadmapItemDto.getCity();
        this.attraction = roadmapItemDto.getAttraction();
        this.trip = trip;
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

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
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
