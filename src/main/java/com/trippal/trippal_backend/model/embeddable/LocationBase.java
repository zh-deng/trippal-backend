package com.trippal.trippal_backend.model.embeddable;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Transient;

@MappedSuperclass
public abstract class LocationBase {

    @Column(name = "name", nullable = true)
    protected String name;

    @Column(name = "latitude", nullable = true)
    protected Double latitude;

    @Column(name = "longitude", nullable = true)
    protected Double longitude;

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
