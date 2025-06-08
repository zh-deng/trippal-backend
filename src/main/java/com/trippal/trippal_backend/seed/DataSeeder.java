package com.trippal.trippal_backend.seed;

import com.trippal.trippal_backend.model.RoadmapItem;
import com.trippal.trippal_backend.model.Trip;
import com.trippal.trippal_backend.model.TripComment;
import com.trippal.trippal_backend.model.UserInfo;
import com.trippal.trippal_backend.model.embeddable.Attraction;
import com.trippal.trippal_backend.model.embeddable.City;
import com.trippal.trippal_backend.model.embeddable.Country;
import com.trippal.trippal_backend.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Component
public class DataSeeder implements CommandLineRunner {

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Override
    public void run(String... args) throws Exception {
        if (userInfoRepository.count() == 0) {

            UserInfo newUser = new UserInfo("John Doe", "john@gmail.com", "secret", "User");

            Trip trip1 = new Trip("Trip 1", newUser);
            trip1.setPublic(true);
            Trip trip2 = new Trip("Trip 2", newUser);
            trip2.setPublic(true);
            Trip trip3 = new Trip("Trip 3", newUser);
            trip3.setPublic(true);
            Trip trip4 = new Trip("Trip 4", newUser);
            trip4.setPublic(true);
            Trip trip5 = new Trip("Trip 5", newUser);
            trip5.setPublic(true);
            Trip trip6 = new Trip("Trip 6", newUser);
            trip6.setPublic(true);
            Trip trip7 = new Trip("Trip 7", newUser);
            trip7.setPublic(true);
            Trip trip8 = new Trip("Trip 8", newUser);
            trip8.setPublic(true);
            Trip trip9 = new Trip("Trip 9", newUser);
            trip9.setPublic(true);
            Trip trip10 = new Trip("Trip 10", newUser);
            trip10.setPublic(true);
            Trip trip11 = new Trip("Trip 11", newUser);
            trip11.setPublic(true);
            Trip trip12 = new Trip("Trip 12", newUser);
            trip12.setPublic(true);
            Trip trip13 = new Trip("Trip 13", newUser);
            trip13.setPublic(true);
            Trip trip14 = new Trip("Trip 14", newUser);
            trip14.setPublic(true);
            Trip trip15 = new Trip("Trip 15", newUser);
            trip15.setPublic(true);

            RoadmapItem roadmapItem1_1 = new RoadmapItem("Germany Munich Marienplatz", Date.from(LocalDate.parse("2024-05-31")
                    .atStartOfDay(ZoneId.systemDefault())
                    .toInstant()), "Notes 1.1", new Country("Germany", 10.5, 10.5, "DE"), new City("Munich", 10.5, 10.5),
                    new Attraction("Marienplatz", 10.5, 10.5), trip1);
            trip1.addRoadmapItem(roadmapItem1_1);
            RoadmapItem roadmapItem1_2 = new RoadmapItem("France Paris Eifeltower", Date.from(LocalDate.parse("2024-05-31")
                    .atStartOfDay(ZoneId.systemDefault())
                    .toInstant()), "Notes 1.2", new Country("France", 10.5, 10.5, "FR"), new City("Paris", 10.5, 10.5),
                    new Attraction("Eifeltower", 10.5, 10.5), trip1);
            trip1.addRoadmapItem(roadmapItem1_2);
            RoadmapItem roadmapItem1_3 = new RoadmapItem("China Beijing Forbidden City", Date.from(LocalDate.parse("2024-05-31")
                    .atStartOfDay(ZoneId.systemDefault())
                    .toInstant()), "Notes 1.3", new Country("China", 10.5, 10.5, "CN"), new City("Beijing", 10.5, 10.5),
                    new Attraction("Forbidden City", 10.5, 10.5), trip1);
            trip1.addRoadmapItem(roadmapItem1_3);
            RoadmapItem roadmapItem1_4 = new RoadmapItem("Italy Rome Colloseum", Date.from(LocalDate.parse("2024-05-31")
                    .atStartOfDay(ZoneId.systemDefault())
                    .toInstant()), "Notes 1.4", new Country("Italy", 10.5, 10.5, "IT"), new City("Rome", 10.5, 10.5),
                    new Attraction("Colloseum", 10.5, 10.5), trip1);
            trip1.addRoadmapItem(roadmapItem1_4);
            RoadmapItem roadmapItem1_5 = new RoadmapItem("Spain Barcelona Sagrada Familia", Date.from(LocalDate.parse("2024-05-31")
                    .atStartOfDay(ZoneId.systemDefault())
                    .toInstant()), "Notes 1.5", new Country("Spain", 10.5, 10.5, "ES"), new City("Barcelona", 10.5, 10.5),
                    new Attraction("Sagrada Familia", 10.5, 10.5), trip1);
            trip1.addRoadmapItem(roadmapItem1_5);

            RoadmapItem roadmapItem2_1 = new RoadmapItem("Germany Munich Marienplatz", Date.from(LocalDate.parse("2024-05-31")
                    .atStartOfDay(ZoneId.systemDefault())
                    .toInstant()), "Notes 2.1", new Country("Germany", 10.5, 10.5, "DE"), new City("Munich", 10.5, 10.5),
                    new Attraction("Marienplatz", 10.5, 10.5), trip2);
            trip2.addRoadmapItem(roadmapItem2_1);

            RoadmapItem roadmapItem3_1 = new RoadmapItem("France Paris Eifeltower", Date.from(LocalDate.parse("2024-05-31")
                    .atStartOfDay(ZoneId.systemDefault())
                    .toInstant()), "Notes 3.1", new Country("France", 10.5, 10.5, "FR"), new City("Paris", 10.5, 10.5),
                    new Attraction("Eifeltower", 10.5, 10.5), trip3);
            trip3.addRoadmapItem(roadmapItem3_1);
            RoadmapItem roadmapItem3_2 = new RoadmapItem("Italy Rome Colloseum", Date.from(LocalDate.parse("2024-05-31")
                    .atStartOfDay(ZoneId.systemDefault())
                    .toInstant()), "Notes 3.2", new Country("Italy", 10.5, 10.5, "IT"), new City("Rome", 10.5, 10.5),
                    new Attraction("Colloseum", 10.5, 10.5), trip3);
            trip3.addRoadmapItem(roadmapItem3_2);

            RoadmapItem roadmapItem4_1 = new RoadmapItem("France Lyon Luvre", Date.from(LocalDate.parse("2024-05-31")
                    .atStartOfDay(ZoneId.systemDefault())
                    .toInstant()), "Notes 4.1", new Country("France", 10.5, 10.5, "FR"), new City("Lyon", 10.5, 10.5),
                    new Attraction("Luvre", 10.5, 10.5), trip4);
            trip4.addRoadmapItem(roadmapItem4_1);

            RoadmapItem roadmapItem5_1 = new RoadmapItem("Germany Munich Marienplatz", Date.from(LocalDate.parse("2024-05-31")
                    .atStartOfDay(ZoneId.systemDefault())
                    .toInstant()), "Notes 5.1", new Country("Germany", 10.5, 10.5, "DE"), new City("Munich", 10.5, 10.5),
                    new Attraction("Marienplatz", 10.5, 10.5), trip5);
            trip5.addRoadmapItem(roadmapItem5_1);
            RoadmapItem roadmapItem5_2 = new RoadmapItem("Italy Pisa Pisa Tower", Date.from(LocalDate.parse("2024-05-31")
                    .atStartOfDay(ZoneId.systemDefault())
                    .toInstant()), "Notes 5.2", new Country("Italy", 10.5, 10.5, "IT"), new City("Pisa", 10.5, 10.5),
                    new Attraction("Pisa Tower", 10.5, 10.5), trip5);
            trip5.addRoadmapItem(roadmapItem5_2);
            RoadmapItem roadmapItem5_3 = new RoadmapItem("England London Buckingham Palace", Date.from(LocalDate.parse("2024-05-31")
                    .atStartOfDay(ZoneId.systemDefault())
                    .toInstant()), "Notes 5.3", new Country("England", 10.5, 10.5, "ES"), new City("London", 10.5, 10.5),
                    new Attraction("Buckingham Palace", 10.5, 10.5), trip5);
            trip5.addRoadmapItem(roadmapItem5_3);

            RoadmapItem roadmapItem6_1 = new RoadmapItem("Germany Munich Marienplatz", Date.from(LocalDate.parse("2024-05-31")
                    .atStartOfDay(ZoneId.systemDefault())
                    .toInstant()), "Notes 6.1", new Country("Germany", 10.5, 10.5, "DE"), new City("Munich", 10.5, 10.5),
                    new Attraction("Marienplatz", 10.5, 10.5), trip6);
            trip6.addRoadmapItem(roadmapItem6_1);

            RoadmapItem roadmapItem7_1 = new RoadmapItem("Italy Pisa Pisa Tower", Date.from(LocalDate.parse("2024-05-31")
                    .atStartOfDay(ZoneId.systemDefault())
                    .toInstant()), "Notes 7.1", new Country("Italy", 10.5, 10.5, "IT"), new City("Pisa", 10.5, 10.5),
                    new Attraction("Pisa Tower", 10.5, 10.5), trip7);
            trip7.addRoadmapItem(roadmapItem7_1);
            RoadmapItem roadmapItem7_2 = new RoadmapItem("England London Buckingham Palace", Date.from(LocalDate.parse("2024-05-31")
                    .atStartOfDay(ZoneId.systemDefault())
                    .toInstant()), "Notes 7.2", new Country("England", 10.5, 10.5, "ES"), new City("London", 10.5, 10.5),
                    new Attraction("Buckingham Palace", 10.5, 10.5), trip7);
            trip7.addRoadmapItem(roadmapItem7_1);

            RoadmapItem roadmapItem8_1 = new RoadmapItem("Germany Munich Marienplatz", Date.from(LocalDate.parse("2024-05-31")
                    .atStartOfDay(ZoneId.systemDefault())
                    .toInstant()), "Notes 8.1", new Country("Germany", 10.5, 10.5, "DE"), new City("Munich", 10.5, 10.5),
                    new Attraction("Marienplatz", 10.5, 10.5), trip8);
            trip8.addRoadmapItem(roadmapItem8_1);

            RoadmapItem roadmapItem9_1 = new RoadmapItem("Germany Munich Marienplatz", Date.from(LocalDate.parse("2024-05-31")
                    .atStartOfDay(ZoneId.systemDefault())
                    .toInstant()), "Notes 9.1", new Country("Germany", 10.5, 10.5, "DE"), new City("Munich", 10.5, 10.5),
                    new Attraction("Marienplatz", 10.5, 10.5), trip9);
            trip9.addRoadmapItem(roadmapItem9_1);
            RoadmapItem roadmapItem9_2 = new RoadmapItem("Italy Rome Colloseum", Date.from(LocalDate.parse("2024-05-31")
                    .atStartOfDay(ZoneId.systemDefault())
                    .toInstant()), "Notes 9.2", new Country("Italy", 10.5, 10.5, "IT"), new City("Rome", 10.5, 10.5),
                    new Attraction("Colloseum", 10.5, 10.5), trip9);
            trip9.addRoadmapItem(roadmapItem9_2);
            RoadmapItem roadmapItem9_3 = new RoadmapItem("China Beijing Forbidden City", Date.from(LocalDate.parse("2024-05-31")
                    .atStartOfDay(ZoneId.systemDefault())
                    .toInstant()), "Notes 9.3", new Country("China", 10.5, 10.5, "CN"), new City("Beijing", 10.5, 10.5),
                    new Attraction("Forbidden City", 10.5, 10.5), trip9);
            trip9.addRoadmapItem(roadmapItem9_3);
            RoadmapItem roadmapItem9_4 = new RoadmapItem("France Paris Eifeltower", Date.from(LocalDate.parse("2024-05-31")
                    .atStartOfDay(ZoneId.systemDefault())
                    .toInstant()), "Notes 9.4", new Country("France", 10.5, 10.5, "FR"), new City("Paris", 10.5, 10.5),
                    new Attraction("Eifeltower", 10.5, 10.5), trip9);
            trip9.addRoadmapItem(roadmapItem9_4);

            RoadmapItem roadmapItem10_1 = new RoadmapItem("Germany Berlin Bundestag", Date.from(LocalDate.parse("2024-05-31")
                    .atStartOfDay(ZoneId.systemDefault())
                    .toInstant()), "Notes 10.1", new Country("Germany", 10.5, 10.5, "DE"), new City("Berlin", 10.5, 10.5),
                    new Attraction("Bundestag", 10.5, 10.5), trip10);
            trip10.addRoadmapItem(roadmapItem10_1);

            RoadmapItem roadmapItem11_1 = new RoadmapItem("Italy Pisa Pisa Tower", Date.from(LocalDate.parse("2024-05-31")
                    .atStartOfDay(ZoneId.systemDefault())
                    .toInstant()), "Notes 11.1", new Country("Italy", 10.5, 10.5, "IT"), new City("Pisa", 10.5, 10.5),
                    new Attraction("Pisa Tower", 10.5, 10.5), trip11);
            trip11.addRoadmapItem(roadmapItem11_1);
            RoadmapItem roadmapItem11_2 = new RoadmapItem("France Lyon Luvre", Date.from(LocalDate.parse("2024-05-31")
                    .atStartOfDay(ZoneId.systemDefault())
                    .toInstant()), "Notes 11.2", new Country("France", 10.5, 10.5, "FR"), new City("Lyon", 10.5, 10.5),
                    new Attraction("Luvre", 10.5, 10.5), trip11);
            trip11.addRoadmapItem(roadmapItem11_2);

            RoadmapItem roadmapItem12_1 = new RoadmapItem("China Shanghai Shanghai Tower", Date.from(LocalDate.parse("2024-05-31")
                    .atStartOfDay(ZoneId.systemDefault())
                    .toInstant()), "Notes 12.1", new Country("China", 10.5, 10.5, "CN"), new City("Shanghai", 10.5, 10.5),
                    new Attraction("Shanghai Tower", 10.5, 10.5), trip12);
            trip12.addRoadmapItem(roadmapItem12_1);

            RoadmapItem roadmapItem13_1 = new RoadmapItem("Germany Munich Marienplatz", Date.from(LocalDate.parse("2024-05-31")
                    .atStartOfDay(ZoneId.systemDefault())
                    .toInstant()), "Notes 13.1", new Country("Germany", 10.5, 10.5, "DE"), new City("Munich", 10.5, 10.5),
                    new Attraction("Marienplatz", 10.5, 10.5), trip13);
            trip13.addRoadmapItem(roadmapItem13_1);
            RoadmapItem roadmapItem13_2 = new RoadmapItem("Italy Rome Colloseum", Date.from(LocalDate.parse("2024-05-31")
                    .atStartOfDay(ZoneId.systemDefault())
                    .toInstant()), "Notes 13.2", new Country("Italy", 10.5, 10.5, "IT"), new City("Rome", 10.5, 10.5),
                    new Attraction("Colloseum", 10.5, 10.5), trip13);
            trip13.addRoadmapItem(roadmapItem13_2);
            RoadmapItem roadmapItem13_3 = new RoadmapItem("Spain Barcelona Sagrada Familia", Date.from(LocalDate.parse("2024-05-31")
                    .atStartOfDay(ZoneId.systemDefault())
                    .toInstant()), "Notes 13.3", new Country("Spain", 10.5, 10.5, "ES"), new City("Barcelona", 10.5, 10.5),
                    new Attraction("Sagrada Familia", 10.5, 10.5), trip13);
            trip13.addRoadmapItem(roadmapItem13_3);

            RoadmapItem roadmapItem14_1 = new RoadmapItem("Germany Berlin Bundestag", Date.from(LocalDate.parse("2024-05-31")
                    .atStartOfDay(ZoneId.systemDefault())
                    .toInstant()), "Notes 14.1", new Country("Germany", 10.5, 10.5, "DE"), new City("Berlin", 10.5, 10.5),
                    new Attraction("Bundestag", 10.5, 10.5), trip14);
            trip14.addRoadmapItem(roadmapItem14_1);

            RoadmapItem roadmapItem15_1 = new RoadmapItem("Spain Barcelona Sagrada Familia", Date.from(LocalDate.parse("2024-05-31")
                    .atStartOfDay(ZoneId.systemDefault())
                    .toInstant()), "Notes 15.1", new Country("Spain", 10.5, 10.5, "ES"), new City("Barcelona", 10.5, 10.5),
                    new Attraction("Sagrada Familia", 10.5, 10.5), trip15);
            trip15.addRoadmapItem(roadmapItem15_1);
            RoadmapItem roadmapItem15_2 = new RoadmapItem("China Beijing Forbidden City", Date.from(LocalDate.parse("2024-05-31")
                    .atStartOfDay(ZoneId.systemDefault())
                    .toInstant()), "Notes 15.2", new Country("China", 10.5, 10.5, "CN"), new City("Beijing", 10.5, 10.5),
                    new Attraction("Forbidden City", 10.5, 10.5), trip15);
            trip15.addRoadmapItem(roadmapItem15_2);

            TripComment tripComment1_1 = new TripComment("Some locations were a bit underwhelming.", 1L, trip1);
            TripComment tripComment1_2 = new TripComment("Not what I expected, but it was interesting.", 1L, trip1);
            trip1.addComment(tripComment1_1);
            trip1.addComment(tripComment1_2);

            TripComment tripComment2_1 = new TripComment("Could have been better, but still enjoyed parts of it.", 1L, trip2);
            TripComment tripComment2_2 = new TripComment("The itinerary was well planned and exciting.", 1L, trip2);
            trip2.addComment(tripComment2_1);
            trip2.addComment(tripComment2_2);

            TripComment tripComment3_1 = new TripComment("Amazing experience, would love to go again!", 1L, trip3);
            TripComment tripComment3_2 = new TripComment("Truly breathtaking views and culture.", 1L, trip3);
            TripComment tripComment3_3 = new TripComment("Some locations were a bit underwhelming.", 1L, trip3);
            TripComment tripComment3_4 = new TripComment("Not what I expected, but it was interesting.", 1L, trip3);
            TripComment tripComment3_5 = new TripComment("Some locations were a bit underwhelming.", 1L, trip3);
            trip3.addComment(tripComment3_1);
            trip3.addComment(tripComment3_2);
            trip3.addComment(tripComment3_3);
            trip3.addComment(tripComment3_4);
            trip3.addComment(tripComment3_5);

            TripComment tripComment4_1 = new TripComment("Truly breathtaking views and culture.", 1L, trip4);
            TripComment tripComment4_2 = new TripComment("Could have been better, but still enjoyed parts of it.", 1L, trip4);
            trip4.addComment(tripComment4_1);
            trip4.addComment(tripComment4_2);

            TripComment tripComment5_1 = new TripComment("Could have been better, but still enjoyed parts of it.", 1L, trip5);
            trip5.addComment(tripComment5_1);

            TripComment tripComment6_1 = new TripComment("Some locations were a bit underwhelming.", 1L, trip6);
            trip6.addComment(tripComment6_1);

            TripComment tripComment7_1 = new TripComment("Some locations were a bit underwhelming.", 1L, trip7);
            TripComment tripComment7_2 = new TripComment("A few hiccups but still memorable.", 1L, trip7);
            TripComment tripComment7_3 = new TripComment("Could have been better, but still enjoyed parts of it.", 1L, trip7);
            TripComment tripComment7_4 = new TripComment("Had a great time exploring with this plan.", 1L, trip7);
            trip7.addComment(tripComment7_1);
            trip7.addComment(tripComment7_2);
            trip7.addComment(tripComment7_3);
            trip7.addComment(tripComment7_4);

            TripComment tripComment8_1 = new TripComment("Not what I expected, but it was interesting.", 1L, trip8);
            TripComment tripComment8_2 = new TripComment("Not what I expected, but it was interesting.", 1L, trip8);
            TripComment tripComment8_3 = new TripComment("Some locations were a bit underwhelming.", 1L, trip8);
            trip8.addComment(tripComment8_1);
            trip8.addComment(tripComment8_2);
            trip8.addComment(tripComment8_3);

            TripComment tripComment9_1 = new TripComment("Had a great time exploring with this plan.", 1L, trip9);
            TripComment tripComment9_2 = new TripComment("Some locations were a bit underwhelming.", 1L, trip9);
            TripComment tripComment9_3 = new TripComment("The itinerary was well planned and exciting.", 1L, trip9);
            TripComment tripComment9_4 = new TripComment("Had a great time exploring with this plan.", 1L, trip9);
            trip9.addComment(tripComment9_1);
            trip9.addComment(tripComment9_2);
            trip9.addComment(tripComment9_3);
            trip9.addComment(tripComment9_4);

            TripComment tripComment10_1 = new TripComment("Not what I expected, but it was interesting.", 1L, trip10);
            TripComment tripComment10_2 = new TripComment("Could have been better, but still enjoyed parts of it.", 1L, trip10);
            trip10.addComment(tripComment10_1);
            trip10.addComment(tripComment10_2);

            TripComment tripComment11_1 = new TripComment("Had a great time exploring with this plan.", 1L, trip11);
            TripComment tripComment11_2 = new TripComment("Pretty good overall, had some minor issues.", 1L, trip11);
            TripComment tripComment11_3 = new TripComment("Pretty good overall, had some minor issues.", 1L, trip11);
            TripComment tripComment11_4 = new TripComment("Had a great time exploring with this plan.", 1L, trip11);
            trip11.addComment(tripComment11_1);
            trip11.addComment(tripComment11_2);
            trip11.addComment(tripComment11_3);
            trip11.addComment(tripComment11_4);

            TripComment tripComment12_1 = new TripComment("Not what I expected, but it was interesting.", 1L, trip12);
            TripComment tripComment12_2 = new TripComment("Some locations were a bit underwhelming.", 1L, trip12);
            TripComment tripComment12_3 = new TripComment("Pretty good overall, had some minor issues.", 1L, trip12);
            TripComment tripComment12_4 = new TripComment("Not what I expected, but it was interesting.", 1L, trip12);
            trip12.addComment(tripComment12_1);
            trip12.addComment(tripComment12_2);
            trip12.addComment(tripComment12_3);
            trip12.addComment(tripComment12_4);

            TripComment tripComment13_1 = new TripComment("A few hiccups but still memorable.", 1L, trip13);
            TripComment tripComment13_2 = new TripComment("A must-visit! Unforgettable journey.", 1L, trip13);
            TripComment tripComment13_3 = new TripComment("Could have been better, but still enjoyed parts of it.", 1L, trip13);
            trip13.addComment(tripComment13_1);
            trip13.addComment(tripComment13_2);
            trip13.addComment(tripComment13_3);

            TripComment tripComment14_1 = new TripComment("Pretty good overall, had some minor issues.", 1L, trip14);
            TripComment tripComment14_2 = new TripComment("A must-visit! Unforgettable journey.", 1L, trip14);
            trip14.addComment(tripComment14_1);
            trip14.addComment(tripComment14_2);

            TripComment tripComment15_1 = new TripComment("Some locations were a bit underwhelming.", 1L, trip15);
            TripComment tripComment15_2 = new TripComment("Some locations were a bit underwhelming.", 1L, trip15);
            TripComment tripComment15_3 = new TripComment("Amazing experience, would love to go again!", 1L, trip15);
            trip15.addComment(tripComment15_1);
            trip15.addComment(tripComment15_2);
            trip15.addComment(tripComment15_3);


            newUser.addTrip(trip1);
            newUser.addTrip(trip2);
            newUser.addTrip(trip3);
            newUser.addTrip(trip4);
            newUser.addTrip(trip5);
            newUser.addTrip(trip6);
            newUser.addTrip(trip7);
            newUser.addTrip(trip8);
            newUser.addTrip(trip9);
            newUser.addTrip(trip10);
            newUser.addTrip(trip11);
            newUser.addTrip(trip12);
            newUser.addTrip(trip13);
            newUser.addTrip(trip14);
            newUser.addTrip(trip15);

            userInfoRepository.save(newUser);
        }
    }
}
