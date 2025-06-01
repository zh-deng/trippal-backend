package com.trippal.trippal_backend.repository;

import com.trippal.trippal_backend.model.Trip;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TripRepository extends JpaRepository<Trip, Long> {
    Page<Trip> findByIsPublicTrue(Pageable pageable);

    @Query("SELECT DISTINCT t FROM Trip t JOIN t.roadmapItems r WHERE r.country.name = :countryName AND t.isPublic = true")
    Page<Trip> findPublicTripsByCountryName(Pageable pageable, @Param("countryName") String name);
}
