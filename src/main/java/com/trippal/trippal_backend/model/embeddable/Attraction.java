package com.trippal.trippal_backend.model.embeddable;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Transient;

@Embeddable
public class Attraction {

    @Column(name = "attraction_name", nullable = true)
    private String name;

    @Column(name = "attraction_latitude", nullable = true)
    private Double latitude;

    @Column(name = "attraction_longitude", nullable = true)
    private Double longitude;

    public Attraction() {
    }

    public Attraction(String name, Double latitude, Double longitude) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    // Not persisted in db
    @Transient
    @JsonProperty("coordinates")
    public double[] getCoordinates() {
        return new double[]{latitude, longitude};
    }

    @JsonProperty("coordinates")
    public void setCoordinates(double[] coords) {
        if (coords != null && coords.length == 2) {
            this.latitude = coords[0];
            this.longitude = coords[1];
        }
    }
}
