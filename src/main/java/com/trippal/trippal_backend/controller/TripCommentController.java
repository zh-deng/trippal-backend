package com.trippal.trippal_backend.controller;

import com.trippal.trippal_backend.dtos.TripCommentDto;
import com.trippal.trippal_backend.model.TripComment;
import com.trippal.trippal_backend.repository.UserInfoRepository;
import com.trippal.trippal_backend.service.TripCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/tripComment")
public class TripCommentController {

    private final TripCommentService tripCommentService;
    private final UserInfoRepository userInfoRepository;

    @Autowired
    public TripCommentController(TripCommentService tripCommentService, UserInfoRepository userInfoRepository) {
        this.tripCommentService = tripCommentService;
        this.userInfoRepository = userInfoRepository;
    }

    @PostMapping
    public ResponseEntity<TripCommentDto> createTripComment(@RequestBody TripCommentDto tripCommentDto) {
        TripComment createdTripComment = tripCommentService.createTripComment(tripCommentDto);
        TripCommentDto createdTripCommentDto = new TripCommentDto(createdTripComment, userInfoRepository);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdTripCommentDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTripComment(@PathVariable Long id) {
        boolean deleted = tripCommentService.deleteTripComment(id);

        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }
}
