package com.trippal.trippal_backend.util;

import com.trippal.trippal_backend.model.RoadmapItem;
import com.trippal.trippal_backend.model.Trip;
import com.trippal.trippal_backend.model.UploadedFile;
import com.trippal.trippal_backend.model.UserInfo;
import com.trippal.trippal_backend.model.embeddable.Attraction;
import com.trippal.trippal_backend.model.embeddable.City;
import com.trippal.trippal_backend.model.embeddable.Country;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TripCloner {

    public Trip deepCopyTrip(Trip original, UserInfo newOwner) {
        Trip copy = new Trip();
        copy.setTitle(original.getTitle());

        List<RoadmapItem> copiedRoadmapItems = original.getRoadmapItems().stream()
                .map(roadmapItem -> deepCopyRoadmapItem(roadmapItem, copy))
                .toList();

        copy.setRoadmapItems(copiedRoadmapItems);

        newOwner.addTrip(copy);

        return copy;
    }

    private RoadmapItem deepCopyRoadmapItem(RoadmapItem original, Trip newTrip) {
        RoadmapItem copy = new RoadmapItem();
        copy.setTitle(original.getTitle());
        copy.setDate(original.getDate());
        copy.setNotes(original.getNotes());

        List<UploadedFile> copiedFiles = original.getFiles().stream()
                .map(file -> deepCopyUploadedFile(file, copy))
                .toList();

        copy.setFiles(copiedFiles);
        copy.setCountry(deepCopyCountry(original.getCountry()));
        copy.setCity(deepCopyCity(original.getCity()));
        copy.setAttraction(deepCopyAttraction(original.getAttraction()));

        newTrip.addRoadmapItem(copy);

        return copy;
    }

    private UploadedFile deepCopyUploadedFile(UploadedFile original, RoadmapItem newRoadmapItem) {
        UploadedFile copy = new UploadedFile();
        copy.setName(original.getName());
        copy.setUrl(original.getUrl());

        newRoadmapItem.addFile(copy);

        return copy;
    }

    private Country deepCopyCountry(Country original) {
        Country copy = new Country();
        copy.setName(original.getName());
        copy.setLatitude(original.getLatitude());
        copy.setLongitude(original.getLongitude());
        copy.setCountryCode(original.getCountryCode());

        return copy;
    }

    private City deepCopyCity(City original) {
        City copy = new City();
        copy.setName(original.getName());
        copy.setLatitude(original.getLatitude());
        copy.setLongitude(original.getLongitude());

        return copy;
    }

    private Attraction deepCopyAttraction(Attraction original) {
        Attraction copy = new Attraction();
        copy.setName(original.getName());
        copy.setLatitude(original.getLatitude());
        copy.setLongitude(original.getLongitude());

        return copy;
    }
}

