package com.trippal.trippal_backend.model.embeddable;

import jakarta.persistence.Embeddable;

@Embeddable
public class Attraction extends LocationBase {

    public Attraction() {
    }

    public Attraction(String name, Double latitude, Double longitude) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
