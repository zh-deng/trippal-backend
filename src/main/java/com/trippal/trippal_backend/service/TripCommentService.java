package com.trippal.trippal_backend.service;

import com.trippal.trippal_backend.dtos.TripCommentDto;
import com.trippal.trippal_backend.model.Trip;
import com.trippal.trippal_backend.model.TripComment;
import com.trippal.trippal_backend.repository.TripCommentRepository;
import com.trippal.trippal_backend.repository.TripRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TripCommentService {

    private final TripCommentRepository tripCommentRepository;
    private final TripRepository tripRepository;

    @Autowired
    public TripCommentService(TripCommentRepository tripCommentRepository, TripRepository tripRepository) {
        this.tripCommentRepository = tripCommentRepository;
        this.tripRepository = tripRepository;
    }

    @Transactional
    public TripComment createTripComment(TripCommentDto tripCommentDto) {
        Trip trip = tripRepository.findById(tripCommentDto.getTripId()).orElseThrow(() -> new RuntimeException("Trip not found"));

        TripComment tripComment = new TripComment(tripCommentDto.getContent(), tripCommentDto.getAuthorId(), trip);

        return tripCommentRepository.save(tripComment);
    }

    @Transactional
    public boolean deleteTripComment(Long id) {
        if (tripCommentRepository.existsById(id)) {
            tripCommentRepository.deleteById(id);
            return true;
        }

        return false;
    }
}
