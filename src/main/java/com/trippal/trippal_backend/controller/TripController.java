package com.trippal.trippal_backend.controller;

import com.trippal.trippal_backend.dtos.RoadmapItemPreviewDto;
import com.trippal.trippal_backend.dtos.RoadmapReorderRequestDto;
import com.trippal.trippal_backend.dtos.TripDto;
import com.trippal.trippal_backend.model.Trip;
import com.trippal.trippal_backend.model.UserInfo;
import com.trippal.trippal_backend.service.PdfGenerationService;
import com.trippal.trippal_backend.service.TripService;
import com.trippal.trippal_backend.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/trip")
public class TripController {

    private final TripService tripService;
    private final UserInfoService userInfoService;
    private final PdfGenerationService pdfGenerationService;

    @Autowired
    public TripController(TripService tripService, UserInfoService userInfoService, PdfGenerationService pdfGenerationService) {
        this.tripService = tripService;
        this.userInfoService = userInfoService;
        this.pdfGenerationService = pdfGenerationService;
    }

    @PostMapping
    public ResponseEntity<TripDto> createTrip(@RequestBody Trip trip) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserInfo user = userInfoService.getUserByEmail(userDetails.getUsername());

        Trip createdTrip = tripService.createTrip(new Trip(trip.getTitle(), user));
        TripDto tripDto = new TripDto(createdTrip);

        return ResponseEntity.status(HttpStatus.CREATED).body(tripDto);
    }

    @GetMapping("/{id}/roadmapList")
    public ResponseEntity<List<RoadmapItemPreviewDto>> getRoadmapList(@PathVariable Long id) {
        Trip existingTrip = tripService.getTripById(id);

        List<RoadmapItemPreviewDto> roadmapItemPreviewDtos = existingTrip.getRoadmapItems().stream()
                .map(RoadmapItemPreviewDto::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok(roadmapItemPreviewDtos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TripDto> updateTrip(@RequestBody Trip trip, @PathVariable Long id) {
        Trip updatedTrip = tripService.updateTrip(id, trip);
        TripDto tripDto = new TripDto(updatedTrip);

        return ResponseEntity.ok(tripDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrip(@PathVariable Long id) {
        boolean deleted = tripService.deleteTrip(id);

        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/{id}/publish/toggle")
    public ResponseEntity<Void> togglePublishStatus(@PathVariable Long id) {
        tripService.togglePublishStatus(id);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/star/toggle")
    public ResponseEntity<Void> toggleStarStatus(@PathVariable Long id) {
        tripService.toggleStarStatus(id);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/copy")
    public ResponseEntity<TripDto> saveSharedTrip(@PathVariable Long id) {
        Trip sharedTrip = tripService.saveSharedTrip(id);
        TripDto sharedTripDto = new TripDto(sharedTrip);

        return ResponseEntity.status(HttpStatus.CREATED).body(sharedTripDto);
    }

    // Generates a pdf with trip information
    @GetMapping("/{id}/download/{language}")
    public ResponseEntity<Resource> downloadTripPdf(@PathVariable Long id, @PathVariable String language) {
        Trip trip = tripService.getTripById(id);
        byte[] pdfBytes = pdfGenerationService.generateTripPdf(trip, language);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + trip.getTitle() + ".pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .contentLength(pdfBytes.length)
                .body(new ByteArrayResource(pdfBytes));

    }

    @PutMapping("{id}/roadmap/reorder")
    public ResponseEntity<Void> reorderRoadmapItems(
            @PathVariable Long id,
            @RequestBody RoadmapReorderRequestDto request
    ) {
        tripService.reorderRoadmapItems(id, request.getRoadmapItemIds());

        return ResponseEntity.ok().build();
    }
}
