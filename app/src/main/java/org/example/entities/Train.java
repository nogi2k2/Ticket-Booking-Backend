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
    private String trainNo;
    private List<List<Integer>> seats;
    private HashMap<String, Time> stationTimes;
    private List<String> stations;

    public Train(String trainId, String trainNo, List<List<Integer>> seats, HashMap<String, Time> stationTimes, List<String> stations) {
        this.trainId = trainId;
        this.trainNo = trainNo;
        this.seats = seats;
        this.stationTimes = stationTimes;
        this.stations = stations;
    }
    
    public HashMap<String, Time> getStationTimes() {
        return stationTimes;
    }

    @Override
    public String toString() {
        return String.format("Train{trainId='%s', trainNo='%s', seats=%s, stationTimes=%s, stations=%s}",
                trainId, trainNo, seats, stationTimes, stations);
    }
    
    public String getTrainId() {
        return trainId;
    }
    
    public void setTrainId(String trainId) {
        this.trainId = trainId;
    }

    public String getTrainNo() {
        return trainNo;
    }
    public void setTrainNo(String trainNo) {
        this.trainNo = trainNo;
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


    
