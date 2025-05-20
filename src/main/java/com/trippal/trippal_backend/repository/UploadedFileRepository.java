package com.trippal.trippal_backend.repository;

import com.trippal.trippal_backend.model.RoadmapItem;
import com.trippal.trippal_backend.model.UploadedFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UploadedFileRepository extends JpaRepository<UploadedFile, Long> {
    void deleteByRoadmapItem(RoadmapItem roadmapItem);
}
