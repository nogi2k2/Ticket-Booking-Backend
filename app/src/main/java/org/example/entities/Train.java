package org.example.entities;
import java.util.*;
import java.time.*;
import java.sql.*;
// import lombok.Getter;
// import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Train {
    private String trainId;
    private String trianNo;
    private List<List<Integer>> seats;
    private HashMap<String, Time> stationTimes;
    private List<String> stations;

    public Train(String trainId, String trainNo, List<List<Integer>> seats, HashMap<String, Time> stationTimes, List<String> stations) {
        this.trainId = trainId;
        this.trianNo = trainNo;
        this.seats = seats;
        this.stationTimes = stationTimes;
        this.stations = stations;
    }

    public boolean bookSeat(int coach, int seat){
        if (coach >= seats.size() || seat >= seats.get(coach).size()) {return false;}
        if (seats.get(coach).get(seat) == 1) {return false;}
        seats.get(coach).set(seat, 1);
        return true;
    }

    public boolean cancelSeat(int coach, int seat){
        if (coach >= seats.size() || seat >= seats.get(coach).size()) {return false;}
        if (seats.get(coach).get(seat) == 0) {return false;}
        seats.get(coach).set(seat, 0);
        return true;
    }

    public List<Integer> getAvailableSeats(int coach){
        if (coach >= seats.size()) {return Collections.emptyList();}
        List<Integer> availableSeats = new ArrayList<>();
        for (int i=0; i<seats.get(coach).size(); i++){
            if (seats.get(coach).get(i) == 0) {availableSeats.add(i);}
        }
        if (availableSeats.size() == 0) {return Collections.emptyList();}
        return availableSeats;
    }

    public void printTrainSchedule() {
        System.out.println(String.format("Train No: %s | Train Id: %s", trianNo, trainId));
        for (String station: stations){
            System.out.println(String.format("Station: %s | Time: %s", station, stationTimes.get(station).toString()));
        }
    }

    public HashMap<String, Time> getStationTimes() {
        return stationTimes;
    }    

    @Override
    public String toString() {
        return String.format("Train{trainId='%s', trainNo='%s', seats=%s, stationTimes=%s, stations=%s}",
                trainId, trianNo, seats, stationTimes, stations);
    }

    public String getTrainId() {
        return trainId;
    
    public void setTrainId(String trainId) {
        this.trainId = trainId;
    }

    public String getTrianNo() {
        return trianNo;
    }
    public void setTrianNo(String trianNo) {
        this.trianNo = trianNo;
    }

    public List<List<Integer>> getSeats() {
        return seats;
    }
    public void setSeats(List<List<Integer>> seats) {
        this.seats = seats;
    }

    public void setStationTimes(HashMap<String, Time> stationTimes) {
        this.stationTimes = stationTimes;
    }
    
    public void setStations(List<String> stations) {
        this.stations = stations;
    }
    public List<String> getStations() {
        return stations;
    }
    
}

    
