package org.example.entities;

import java.util.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.text.SimpleDateFormat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonIgnoreProperties(ignoreUnknown=true)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Ticket {
    private String ticketId;
    private String userId;
    private String sourceStation;
    private String destinationStation;
    private String dateOfTravel;
    private Train train;
    private boolean isCancelled;

    public Ticket(String ticketId, String userId, String sourceStation, String destinationStation, String dateOfTravel, Train train){
        this.ticketId = UUID.randomUUID().toString();
        this.userId = userId;
        this.sourceStation = sourceStation;
        this.destinationStation = destinationStation;
        this.dateOfTravel = dateOfTravel;
        this.isCancelled = false;
        this.train = train;
    }

    public boolean isValid(){
        return sourceStation != null && destinationStation != null && train != null && dateOfTravel != null;
    }

    public long getJourneyDuration(){
        if (train != null && train.getStationTimes().containsKey(sourceStation) && train.getStationTimes().containsKey(destinationStation)){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            LocalTime departureTime = LocalTime.parse(train.getStationTimes().get(sourceStation).toString(), formatter);
            LocalTime ArrivalTime = LocalTime.parse(train.getStationTimes().get(destinationStation).toString(), formatter);
            return Duration.between(departureTime, ArrivalTime).toMinutes();
        }
        return -1;
    }

    public void cancelTicket(){
        this.isCancelled = true;
    }

    public boolean isCancelled(){
        return isCancelled;
    }

    public String getTicketId() {
        return ticketId;
    }
    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSourceStation() {
        return sourceStation;
    }
    public void setSourceStation(String sourceStation) {
        this.sourceStation = sourceStation;
    }

    public String getDestinationStation() {
        return destinationStation;
    }
    public void setDestinationStation(String destinationStation) {
        this.destinationStation = destinationStation;
    }

    public String getDateOfTravel() {
        return dateOfTravel;
    }
    public void setDateOfTravel(String dateOfTravel) {
        this.dateOfTravel = dateOfTravel;
    }

    public Train getTrain() {
        return train;
    }
    public void setTrain(Train train) {
        this.train = train;
    }

    @Override
    public String toString(){
        return "Ticket{" +
                "ticketId='" + ticketId + '\'' +
                ", userId='" + userId + '\'' +
                ", source='" + sourceStation + '\'' +
                ", destination='" + destinationStation + '\'' +
                ", dateOfTravel='" + dateOfTravel + '\'' +
                ", train=" + train.getTrainId() +
                ", isCancelled=" + isCancelled +
                '}';
    }
}
