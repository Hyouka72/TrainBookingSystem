package com.example.TrainBookingSystem.services;

import com.example.TrainBookingSystem.entities.Train;
import com.example.TrainBookingSystem.entities.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class TrainService {

    private Train train;

    private List<Train> trainList;

    public TrainService() throws IOException {
        loadTrains();
    }

    private static final ObjectMapper objectMapper = new ObjectMapper();

    private static final String TRAINS_PATH = "src/main/java/com/example/TrainBookingSystem/LocalDb/users.json";


    public List<Train> loadTrains() throws IOException {
        File trains = new File(TRAINS_PATH);
        return trainList = objectMapper.readValue(trains, new TypeReference<List<Train>>() { //deserialization
        });
    }


    public List<Train> searchTrains(String source, String dest) {

        List<Train> foundtrain = trainList.stream().filter(train1 -> {

            List<String> AllStations = train1.getStations();

            if( AllStations.contains(source) && AllStations.contains(dest) ) {

                int indexOfSource = source.indexOf(source);
                int indexOfDest = dest.indexOf(dest);

                // this only ensures that it is found
                return indexOfSource < indexOfDest;
            }
            //not found
        return false;

            //the founded data are sent to the found train as list
        }).toList();

        return foundtrain;
    }
}
