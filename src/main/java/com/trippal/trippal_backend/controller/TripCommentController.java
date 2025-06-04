package com.trippal.trippal_backend.controller;

import com.trippal.trippal_backend.service.TripCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/tripComment")
public class TripCommentController {

    private final TripCommentService tripCommentService;

    @Autowired
    public TripCommentController(TripCommentService tripCommentService) {
        this.tripCommentService = tripCommentService;
    }


}
