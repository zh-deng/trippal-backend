package com.trippal.trippal_backend.service;

import com.trippal.trippal_backend.dtos.RoadmapItemDto;
import com.trippal.trippal_backend.model.RoadmapItem;
import com.trippal.trippal_backend.model.Trip;
import com.trippal.trippal_backend.model.UploadedFile;
import com.trippal.trippal_backend.repository.RoadmapItemRepository;
import com.trippal.trippal_backend.repository.TripRepository;
import com.trippal.trippal_backend.repository.UploadedFileRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoadmapItemService {

    private final RoadmapItemRepository roadmapItemRepository;
    private final TripRepository tripRepository;
    private final UploadedFileRepository uploadedFileRepository;
    private final EntityManager entityManager;

    @Autowired
    public RoadmapItemService(RoadmapItemRepository roadmapItemRepository, TripRepository tripRepository, UploadedFileRepository uploadedFileRepository, EntityManager entityManager) {
        this.roadmapItemRepository = roadmapItemRepository;
        this.tripRepository = tripRepository;
        this.uploadedFileRepository = uploadedFileRepository;
        this.entityManager = entityManager;
    }

    public RoadmapItem getRoadmapItemById(Long id) {
        return roadmapItemRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Roadmap item not found with id: " + id));
    }

    @Transactional
    public RoadmapItem createRoadmapItem(RoadmapItemDto roadmapItemDto) {
        Trip trip = tripRepository.findById(roadmapItemDto.getTripId()).orElseThrow(() -> new RuntimeException("Trip not found"));

        RoadmapItem roadmapItem = new RoadmapItem(roadmapItemDto, trip);

        List<UploadedFile> files = roadmapItemDto.getFiles().stream()
                .map(fileDto -> {
                    UploadedFile file = new UploadedFile();
                    file.setName(fileDto.getName());
                    file.setUrl(fileDto.getUrl());
                    file.setRoadmapItem(roadmapItem);
                    return file;
                })
                .collect(Collectors.toList());

        roadmapItem.setFiles(files);

        trip.addRoadmapItem(roadmapItem);
        tripRepository.save(trip);
        entityManager.flush();

        return roadmapItemRepository.findTopByTripOrderByPositionDesc(trip);
    }

    @Transactional
    public RoadmapItem updateRoadmapItem(RoadmapItemDto roadmapItemDto) {
        RoadmapItem existingItem = roadmapItemRepository.findById(roadmapItemDto.getId())
                .orElseThrow(() -> new RuntimeException("Roadmap item not found"));

        existingItem.setTitle(roadmapItemDto.getTitle());
        existingItem.setDate(roadmapItemDto.getDate());
        existingItem.setNotes(roadmapItemDto.getNotes());
        existingItem.setCountry(roadmapItemDto.getCountry());
        existingItem.setCity(roadmapItemDto.getCity());
        existingItem.setAttraction(roadmapItemDto.getAttraction());

        uploadedFileRepository.deleteByRoadmapItem(existingItem);

        List<UploadedFile> newFiles = roadmapItemDto.getFiles().stream()
                .map(fileDto -> {
                    UploadedFile file = new UploadedFile();
                    file.setName(fileDto.getName());
                    file.setUrl(fileDto.getUrl());
                    file.setRoadmapItem(existingItem);
                    return file;
                })
                .collect(Collectors.toList());

        uploadedFileRepository.saveAll(newFiles);
        existingItem.getFiles().clear();
        existingItem.getFiles().addAll(newFiles);

        return roadmapItemRepository.save(existingItem);
    }

    @Transactional
    public boolean deleteRoadmapItem(Long id) {
        if (roadmapItemRepository.existsById(id)) {
            roadmapItemRepository.deleteById(id);
            return true;
        }

        return false;
    }

    public List<String> getFilterCountries() {
        return roadmapItemRepository.findDistinctCountryNamesByPublicTrips();
    }
}
