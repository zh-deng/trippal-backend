package com.trippal.trippal_backend.seed;

import com.trippal.trippal_backend.model.RoadmapItem;
import com.trippal.trippal_backend.model.Trip;
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
            Trip trip2 = new Trip("Trip 2", newUser);
            Trip trip3 = new Trip("Trip 3", newUser);
            Trip trip4 = new Trip("Trip 4", newUser);
            Trip trip5 = new Trip("Trip 5", newUser);
            Trip trip6 = new Trip("Trip 6", newUser);
            Trip trip7 = new Trip("Trip 7", newUser);
            Trip trip8 = new Trip("Trip 8", newUser);
            Trip trip9 = new Trip("Trip 9", newUser);
            Trip trip10 = new Trip("Trip 10", newUser);
            Trip trip11 = new Trip("Trip 11", newUser);
            Trip trip12 = new Trip("Trip 12", newUser);
            Trip trip13 = new Trip("Trip 13", newUser);
            Trip trip14 = new Trip("Trip 14", newUser);
            Trip trip15 = new Trip("Trip 15", newUser);

            RoadmapItem roadmapItem1_1 = new RoadmapItem("Germany Munich Marienplatz", Date.from(LocalDate.parse("2024-05-31")
                    .atStartOfDay(ZoneId.systemDefault())
                    .toInstant()), "Notes 1.1", new Country("Germany", 10.5, 10.5), new City("Munich", 10.5, 10.5),
                    new Attraction("Marienplatz", 10.5, 10.5), trip1);
            trip1.addRoadmapItem(roadmapItem1_1);
            RoadmapItem roadmapItem1_2 = new RoadmapItem("France Paris Eifeltower", Date.from(LocalDate.parse("2024-05-31")
                    .atStartOfDay(ZoneId.systemDefault())
                    .toInstant()), "Notes 1.2", new Country("France", 10.5, 10.5), new City("Paris", 10.5, 10.5),
                    new Attraction("Eifeltower", 10.5, 10.5), trip1);
            trip1.addRoadmapItem(roadmapItem1_2);
            RoadmapItem roadmapItem1_3 = new RoadmapItem("China Beijing Forbidden City", Date.from(LocalDate.parse("2024-05-31")
                    .atStartOfDay(ZoneId.systemDefault())
                    .toInstant()), "Notes 1.3", new Country("China", 10.5, 10.5), new City("Beijing", 10.5, 10.5),
                    new Attraction("Forbidden City", 10.5, 10.5), trip1);
            trip1.addRoadmapItem(roadmapItem1_3);
            RoadmapItem roadmapItem1_4 = new RoadmapItem("Italy Rome Colloseum", Date.from(LocalDate.parse("2024-05-31")
                    .atStartOfDay(ZoneId.systemDefault())
                    .toInstant()), "Notes 1.4", new Country("Italy", 10.5, 10.5), new City("Rome", 10.5, 10.5),
                    new Attraction("Colloseum", 10.5, 10.5), trip1);
            trip1.addRoadmapItem(roadmapItem1_4);
            RoadmapItem roadmapItem1_5 = new RoadmapItem("Spain Barcelona Sagrada Familia", Date.from(LocalDate.parse("2024-05-31")
                    .atStartOfDay(ZoneId.systemDefault())
                    .toInstant()), "Notes 1.5", new Country("Spain", 10.5, 10.5), new City("Barcelona", 10.5, 10.5),
                    new Attraction("Sagrada Familia", 10.5, 10.5), trip1);
            trip1.addRoadmapItem(roadmapItem1_5);

            RoadmapItem roadmapItem2_1 = new RoadmapItem("Germany Munich Marienplatz", Date.from(LocalDate.parse("2024-05-31")
                    .atStartOfDay(ZoneId.systemDefault())
                    .toInstant()), "Notes 2.1", new Country("Germany", 10.5, 10.5), new City("Munich", 10.5, 10.5),
                    new Attraction("Marienplatz", 10.5, 10.5), trip2);
            trip2.addRoadmapItem(roadmapItem2_1);

            RoadmapItem roadmapItem3_1 = new RoadmapItem("France Paris Eifeltower", Date.from(LocalDate.parse("2024-05-31")
                    .atStartOfDay(ZoneId.systemDefault())
                    .toInstant()), "Notes 3.1", new Country("France", 10.5, 10.5), new City("Paris", 10.5, 10.5),
                    new Attraction("Eifeltower", 10.5, 10.5), trip3);
            trip3.addRoadmapItem(roadmapItem3_1);
            RoadmapItem roadmapItem3_2 = new RoadmapItem("Italy Rome Colloseum", Date.from(LocalDate.parse("2024-05-31")
                    .atStartOfDay(ZoneId.systemDefault())
                    .toInstant()), "Notes 3.2", new Country("Italy", 10.5, 10.5), new City("Rome", 10.5, 10.5),
                    new Attraction("Colloseum", 10.5, 10.5), trip3);
            trip3.addRoadmapItem(roadmapItem3_2);

            RoadmapItem roadmapItem4_1 = new RoadmapItem("France Lyon Luvre", Date.from(LocalDate.parse("2024-05-31")
                    .atStartOfDay(ZoneId.systemDefault())
                    .toInstant()), "Notes 4.1", new Country("France", 10.5, 10.5), new City("Lyon", 10.5, 10.5),
                    new Attraction("Luvre", 10.5, 10.5), trip4);
            trip4.addRoadmapItem(roadmapItem4_1);

            RoadmapItem roadmapItem5_1 = new RoadmapItem("Germany Munich Marienplatz", Date.from(LocalDate.parse("2024-05-31")
                    .atStartOfDay(ZoneId.systemDefault())
                    .toInstant()), "Notes 5.1", new Country("Germany", 10.5, 10.5), new City("Munich", 10.5, 10.5),
                    new Attraction("Marienplatz", 10.5, 10.5), trip5);
            trip5.addRoadmapItem(roadmapItem5_1);
            RoadmapItem roadmapItem5_2 = new RoadmapItem("Italy Pisa Pisa Tower", Date.from(LocalDate.parse("2024-05-31")
                    .atStartOfDay(ZoneId.systemDefault())
                    .toInstant()), "Notes 5.2", new Country("Italy", 10.5, 10.5), new City("Pisa", 10.5, 10.5),
                    new Attraction("Pisa Tower", 10.5, 10.5), trip5);
            trip5.addRoadmapItem(roadmapItem5_2);
            RoadmapItem roadmapItem5_3 = new RoadmapItem("England London Buckingham Palace", Date.from(LocalDate.parse("2024-05-31")
                    .atStartOfDay(ZoneId.systemDefault())
                    .toInstant()), "Notes 5.3", new Country("England", 10.5, 10.5), new City("London", 10.5, 10.5),
                    new Attraction("Buckingham Palace", 10.5, 10.5), trip5);
            trip5.addRoadmapItem(roadmapItem5_3);

            RoadmapItem roadmapItem6_1 = new RoadmapItem("Germany Munich Marienplatz", Date.from(LocalDate.parse("2024-05-31")
                    .atStartOfDay(ZoneId.systemDefault())
                    .toInstant()), "Notes 6.1", new Country("Germany", 10.5, 10.5), new City("Munich", 10.5, 10.5),
                    new Attraction("Marienplatz", 10.5, 10.5), trip6);
            trip6.addRoadmapItem(roadmapItem6_1);

            RoadmapItem roadmapItem7_1 = new RoadmapItem("Italy Pisa Pisa Tower", Date.from(LocalDate.parse("2024-05-31")
                    .atStartOfDay(ZoneId.systemDefault())
                    .toInstant()), "Notes 7.1", new Country("Italy", 10.5, 10.5), new City("Pisa", 10.5, 10.5),
                    new Attraction("Pisa Tower", 10.5, 10.5), trip7);
            trip7.addRoadmapItem(roadmapItem7_1);
            RoadmapItem roadmapItem7_2 = new RoadmapItem("England London Buckingham Palace", Date.from(LocalDate.parse("2024-05-31")
                    .atStartOfDay(ZoneId.systemDefault())
                    .toInstant()), "Notes 7.2", new Country("England", 10.5, 10.5), new City("London", 10.5, 10.5),
                    new Attraction("Buckingham Palace", 10.5, 10.5), trip7);
            trip7.addRoadmapItem(roadmapItem7_1);

            RoadmapItem roadmapItem8_1 = new RoadmapItem("Germany Munich Marienplatz", Date.from(LocalDate.parse("2024-05-31")
                    .atStartOfDay(ZoneId.systemDefault())
                    .toInstant()), "Notes 8.1", new Country("Germany", 10.5, 10.5), new City("Munich", 10.5, 10.5),
                    new Attraction("Marienplatz", 10.5, 10.5), trip8);
            trip8.addRoadmapItem(roadmapItem8_1);

            RoadmapItem roadmapItem9_1 = new RoadmapItem("Germany Munich Marienplatz", Date.from(LocalDate.parse("2024-05-31")
                    .atStartOfDay(ZoneId.systemDefault())
                    .toInstant()), "Notes 9.1", new Country("Germany", 10.5, 10.5), new City("Munich", 10.5, 10.5),
                    new Attraction("Marienplatz", 10.5, 10.5), trip9);
            trip9.addRoadmapItem(roadmapItem9_1);
            RoadmapItem roadmapItem9_2 = new RoadmapItem("Italy Rome Colloseum", Date.from(LocalDate.parse("2024-05-31")
                    .atStartOfDay(ZoneId.systemDefault())
                    .toInstant()), "Notes 9.2", new Country("Italy", 10.5, 10.5), new City("Rome", 10.5, 10.5),
                    new Attraction("Colloseum", 10.5, 10.5), trip9);
            trip9.addRoadmapItem(roadmapItem9_2);
            RoadmapItem roadmapItem9_3 = new RoadmapItem("China Beijing Forbidden City", Date.from(LocalDate.parse("2024-05-31")
                    .atStartOfDay(ZoneId.systemDefault())
                    .toInstant()), "Notes 9.3", new Country("China", 10.5, 10.5), new City("Beijing", 10.5, 10.5),
                    new Attraction("Forbidden City", 10.5, 10.5), trip9);
            trip9.addRoadmapItem(roadmapItem9_3);
            RoadmapItem roadmapItem9_4 = new RoadmapItem("France Paris Eifeltower", Date.from(LocalDate.parse("2024-05-31")
                    .atStartOfDay(ZoneId.systemDefault())
                    .toInstant()), "Notes 9.4", new Country("France", 10.5, 10.5), new City("Paris", 10.5, 10.5),
                    new Attraction("Eifeltower", 10.5, 10.5), trip9);
            trip9.addRoadmapItem(roadmapItem9_4);

            RoadmapItem roadmapItem10_1 = new RoadmapItem("Germany Berlin Bundestag", Date.from(LocalDate.parse("2024-05-31")
                    .atStartOfDay(ZoneId.systemDefault())
                    .toInstant()), "Notes 10.1", new Country("Germany", 10.5, 10.5), new City("Berlin", 10.5, 10.5),
                    new Attraction("Bundestag", 10.5, 10.5), trip10);
            trip10.addRoadmapItem(roadmapItem10_1);

            RoadmapItem roadmapItem11_1 = new RoadmapItem("Italy Pisa Pisa Tower", Date.from(LocalDate.parse("2024-05-31")
                    .atStartOfDay(ZoneId.systemDefault())
                    .toInstant()), "Notes 11.1", new Country("Italy", 10.5, 10.5), new City("Pisa", 10.5, 10.5),
                    new Attraction("Pisa Tower", 10.5, 10.5), trip11);
            trip11.addRoadmapItem(roadmapItem11_1);
            RoadmapItem roadmapItem11_2 = new RoadmapItem("France Lyon Luvre", Date.from(LocalDate.parse("2024-05-31")
                    .atStartOfDay(ZoneId.systemDefault())
                    .toInstant()), "Notes 11.2", new Country("France", 10.5, 10.5), new City("Lyon", 10.5, 10.5),
                    new Attraction("Luvre", 10.5, 10.5), trip11);
            trip11.addRoadmapItem(roadmapItem11_2);

            RoadmapItem roadmapItem12_1 = new RoadmapItem("China Shanghai Shanghai Tower", Date.from(LocalDate.parse("2024-05-31")
                    .atStartOfDay(ZoneId.systemDefault())
                    .toInstant()), "Notes 12.1", new Country("China", 10.5, 10.5), new City("Shanghai", 10.5, 10.5),
                    new Attraction("Shanghai Tower", 10.5, 10.5), trip12);
            trip12.addRoadmapItem(roadmapItem12_1);

            RoadmapItem roadmapItem13_1 = new RoadmapItem("Germany Munich Marienplatz", Date.from(LocalDate.parse("2024-05-31")
                    .atStartOfDay(ZoneId.systemDefault())
                    .toInstant()), "Notes 13.1", new Country("Germany", 10.5, 10.5), new City("Munich", 10.5, 10.5),
                    new Attraction("Marienplatz", 10.5, 10.5), trip13);
            trip13.addRoadmapItem(roadmapItem13_1);
            RoadmapItem roadmapItem13_2 = new RoadmapItem("Italy Rome Colloseum", Date.from(LocalDate.parse("2024-05-31")
                    .atStartOfDay(ZoneId.systemDefault())
                    .toInstant()), "Notes 13.2", new Country("Italy", 10.5, 10.5), new City("Rome", 10.5, 10.5),
                    new Attraction("Colloseum", 10.5, 10.5), trip13);
            trip13.addRoadmapItem(roadmapItem13_2);
            RoadmapItem roadmapItem13_3 = new RoadmapItem("Spain Barcelona Sagrada Familia", Date.from(LocalDate.parse("2024-05-31")
                    .atStartOfDay(ZoneId.systemDefault())
                    .toInstant()), "Notes 13.3", new Country("Spain", 10.5, 10.5), new City("Barcelona", 10.5, 10.5),
                    new Attraction("Sagrada Familia", 10.5, 10.5), trip13);
            trip13.addRoadmapItem(roadmapItem13_3);

            RoadmapItem roadmapItem14_1 = new RoadmapItem("Germany Berlin Bundestag", Date.from(LocalDate.parse("2024-05-31")
                    .atStartOfDay(ZoneId.systemDefault())
                    .toInstant()), "Notes 14.1", new Country("Germany", 10.5, 10.5), new City("Berlin", 10.5, 10.5),
                    new Attraction("Bundestag", 10.5, 10.5), trip14);
            trip14.addRoadmapItem(roadmapItem14_1);

            RoadmapItem roadmapItem15_1 = new RoadmapItem("Spain Barcelona Sagrada Familia", Date.from(LocalDate.parse("2024-05-31")
                    .atStartOfDay(ZoneId.systemDefault())
                    .toInstant()), "Notes 15.1", new Country("Spain", 10.5, 10.5), new City("Barcelona", 10.5, 10.5),
                    new Attraction("Sagrada Familia", 10.5, 10.5), trip15);
            trip15.addRoadmapItem(roadmapItem15_1);
            RoadmapItem roadmapItem15_2 = new RoadmapItem("China Beijing Forbidden City", Date.from(LocalDate.parse("2024-05-31")
                    .atStartOfDay(ZoneId.systemDefault())
                    .toInstant()), "Notes 15.2", new Country("China", 10.5, 10.5), new City("Beijing", 10.5, 10.5),
                    new Attraction("Forbidden City", 10.5, 10.5), trip15);
            trip15.addRoadmapItem(roadmapItem15_2);

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
