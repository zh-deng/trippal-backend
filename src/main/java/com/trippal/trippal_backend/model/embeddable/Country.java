package com.trippal.trippal_backend.model.embeddable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class Country extends LocationBase {

    @Column(name = "country_countryCode", nullable = true)
    private String countryCode;

    public Country() {
    }

    public Country(String name, Double latitude, Double longitude, String countryCode) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.countryCode = countryCode;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }
}
