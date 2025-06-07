package com.trippal.trippal_backend.model.embeddable;

import jakarta.persistence.Embeddable;

@Embeddable
public class City extends LocationBase {

    public City() {
    }

    public City(String name, Double latitude, Double longitude) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
