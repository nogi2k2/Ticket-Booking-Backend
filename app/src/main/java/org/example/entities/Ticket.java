package org.example.entities;

import java.util.*;
import java.time.*;
// import java.sql.Date;

import java.util.Date;
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
    private Boolean isCancelled = false;
    private Train train;

    public Ticket(String ticketId, String userId, String sourceStation, String destinationStation, String dateOfTravel, Train train){
        this.ticketId = ticketId;
        this.userId = userId;
        this.sourceStation = sourceStation;
        this.destinationStation = destinationStation;
        this.dateOfTravel = dateOfTravel;
        // this.isCancelled = isCancelled;
        this.train = train;
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

    public Boolean getisCancelled(){
        return isCancelled;
    }
    
    public void setisCancelled(Boolean isCancelled){
        this.isCancelled = isCancelled;
    }

    public Train getTrain() {
        return train;
    }
    public void setTrain(Train train) {
        this.train = train;
    }

    public String getFormattedDate(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd H:mm:ss");
        return dateFormat.format(dateOfTravel);
    }

    public long getTravelDuration(){
        return train.getArrivalTime(destinationStation).getTime() - train.getArrivalTime(sourceStation).getTime();
    }

    @Override
    public String toString(){
        return "Ticket{" +
                "ticketId='" + ticketId + '\'' +
                ", userId='" + userId + '\'' +
                ", sourceStation='" + sourceStation + '\'' +
                ", destinationStation='" + destinationStation + '\'' +
                ", dateOfTravel=" + getFormattedDate() +
                ", train=" + train +
                '}';
    }
}
