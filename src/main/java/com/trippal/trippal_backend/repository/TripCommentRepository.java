package com.trippal.trippal_backend.repository;

import com.trippal.trippal_backend.model.TripComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TripCommentRepository extends JpaRepository<TripComment, Long> {
}
