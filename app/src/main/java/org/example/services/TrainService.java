package org.example.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.entities.Train;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.io.*;

public class TrainService {
    private List<Train> trainList;
    private ObjectMapper objectMapper = new ObjectMapper();
    private static final String TRAIN_DB_PATH = "C:\\Users\\Nogi2\\OneDrive\\Desktop\\bruh\\Java-Backend\\IRCTC-Backend\\app\\src\\main\\java\\org\\example\\localdb\\trains.json";

    public TrainService() throws IOException {
        File trains = new File(TRAIN_DB_PATH);
        trainList = objectMapper.readValue(trains, new TypeReference<List<Train>>() {});
    }

    public List<Train> getTrainList(){
        return trainList;
    }

    public List<Train> searchTrains(String source, String destination){
        return trainList.stream().filter(train -> validTrain(train, source, destination)).collect(Collectors.toList());
    }

    public Boolean addTrain(Train newTrain){
        Optional<Train> existing = trainList.stream()
        .filter(train -> train.getTrainId().equalsIgnoreCase(newTrain.getTrainId()))
        .findFirst();

        if (existing.isPresent()){
            updateTrain(newTrain);
            System.out.println("Train already exists!");
            return Boolean.FALSE;
        }else{
            trainList.add(newTrain);
            System.out.println("Train added Succesfully!");
            saveTrainList();
            return Boolean.TRUE;
        }
    }

    public Boolean updateTrain(Train newTrain){
        OptionalInt index = IntStream.range(0, trainList.size())
        .filter(i -> trainList.get(i).getTrainId().equalsIgnoreCase(newTrain.getTrainId()))
        .findFirst();

        if (index.isPresent()){
            trainList.set(index.getAsInt(), newTrain);
            saveTrainList();
            System.out.println("Updated Train Succesfully!");
            return Boolean.TRUE;
        }else{
            addTrain(newTrain);
            System.out.println("Train does\'nt exist, adding train.");
            return Boolean.FALSE;
        }
    }

    private boolean validTrain(Train train, String source, String destination){
        List<String> stationOrder = train.getStations();
        int sind = stationOrder.indexOf(source.toLowerCase());
        int dind = stationOrder.indexOf(destination.toLowerCase());
        return (sind != -1) && (dind!=-1) && sind < dind;
    }

    private void saveTrainList(){
        try{
            objectMapper.writeValue(new File(TRAIN_DB_PATH), trainList);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}