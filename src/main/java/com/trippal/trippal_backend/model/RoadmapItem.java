package com.trippal.trippal_backend.model;

import jakarta.persistence.*;

@Entity
public class RoadmapItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
