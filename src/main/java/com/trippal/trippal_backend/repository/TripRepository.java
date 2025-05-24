package com.trippal.trippal_backend.repository;

import com.trippal.trippal_backend.model.Trip;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TripRepository extends JpaRepository<Trip, Long> {
    Page<Trip> findByPublicTrue(Pageable pageable);
}
