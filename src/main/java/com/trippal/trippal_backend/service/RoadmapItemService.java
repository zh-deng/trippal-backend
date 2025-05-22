package com.trippal.trippal_backend.service;

import com.trippal.trippal_backend.dtos.RoadmapItemDto;
import com.trippal.trippal_backend.model.RoadmapItem;
import com.trippal.trippal_backend.model.Trip;
import com.trippal.trippal_backend.model.UploadedFile;
import com.trippal.trippal_backend.model.UserInfo;
import com.trippal.trippal_backend.repository.RoadmapItemRepository;
import com.trippal.trippal_backend.repository.TripRepository;
import com.trippal.trippal_backend.repository.UploadedFileRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoadmapItemService {

    private final RoadmapItemRepository roadmapItemRepository;
    private final TripRepository tripRepository;
    private final UploadedFileRepository uploadedFileRepository;

    public RoadmapItemService(RoadmapItemRepository roadmapItemRepository, TripRepository tripRepository, UploadedFileRepository uploadedFileRepository) {
        this.roadmapItemRepository = roadmapItemRepository;
        this.tripRepository = tripRepository;
        this.uploadedFileRepository = uploadedFileRepository;
    }

    public RoadmapItem getRoadmapItemById(Long id) {
        return roadmapItemRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Roadmap item not found with id: " + id));
    };

    public RoadmapItem createRoadmapItem(RoadmapItemDto roadmapItemDto) {
        Trip trip = tripRepository.findById(roadmapItemDto.getTripId()).orElseThrow(() -> new RuntimeException("Trip not found"));

        RoadmapItem roadmapItem = new RoadmapItem(roadmapItemDto.getTitle(), roadmapItemDto.getDate(), roadmapItemDto.getNotes(), roadmapItemDto.getCountry(), roadmapItemDto.getCity(), roadmapItemDto.getAttraction(), trip);

        RoadmapItem savedItem = roadmapItemRepository.save(roadmapItem);

        List<UploadedFile> files = roadmapItem.getFiles().stream()
                .map(fileDto -> {
                    UploadedFile file = new UploadedFile();
                    file.setName(fileDto.getName());
                    file.setUrl(fileDto.getUrl());
                    file.setRoadmapItem(savedItem);
                    return file;
                })
                .collect(Collectors.toList());

        uploadedFileRepository.saveAll(files);
        savedItem.setFiles(files);

        return savedItem;
    }

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

    public boolean deleteRoadmapItem(Long id) {
        Optional<RoadmapItem> roadmapItemOpt = roadmapItemRepository.findById(id);
        if (roadmapItemOpt.isPresent()) {
            roadmapItemRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
