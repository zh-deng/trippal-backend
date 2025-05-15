package com.trippal.trippal_backend.repository;

import com.trippal.trippal_backend.model.RoadmapItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoadmapItemRepository extends JpaRepository<RoadmapItem, Long> {
}
