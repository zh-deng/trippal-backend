package com.trippal.trippal_backend.repository;

import com.trippal.trippal_backend.model.RoadmapItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoadmapItemRepository extends JpaRepository<RoadmapItem, Long> {
    @Query("SELECT DISTINCT r.country.name FROM RoadmapItem r WHERE r.trip.isPublic = true")
    List<String> findDistinctCountryNamesByPublicTrips();
}
