package org.example.entities;

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

    public Ticket(){}

    public Ticket(String ticketId, String userId, String sourceStation, String destinationStation, String dateOfTravel, Train train){
        this.ticketId = ticketId;
        this.userId = userId;
        this.sourceStation = sourceStation;
        this.destinationStation = destinationStation;
        this.dateOfTravel = dateOfTravel;
        this.train = train;
    }

    public boolean isValid(){
        return sourceStation != null && destinationStation != null && train != null && dateOfTravel != null;
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
                ", train=" + (train != null ? train.getTrainId() : "N/A")+
                '}';
    }
}
