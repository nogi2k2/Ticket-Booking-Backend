package org.example.entities;
import java.util.*;
// import lombok.Getter;
// import lombok.Setter;
import org.mindrot.jbcrypt.BCrypt;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class User {
     private String name;
     private String password;
     private String hashedPassword;
     private List<Ticket> ticketsBooked;
     private String userId;

     public User(){}

     public User(String name, String password, String userId) {
          this.name = name;
          this.password = password;
          this.hashedPassword = "";
          this.ticketsBooked = new ArrayList<>();
          this.userId = userId;
     }

     public User(String name,
      String password,
      String hashedPassword,
      List<Ticket> ticketsBooked,
      String userId) {
          this.name = name;
          this.password = password;
          this.hashedPassword = hashedPassword;
          this.ticketsBooked = ticketsBooked;
          this.userId = userId;
     }

     public String getName() {
          return name;
     }
     public void setName(String name) {
          this.name = name;
     }

     public String getPassword() {
          return password;
     }
     public void setPassword(String password) {
          this.password = password;
     }

     public String gethashedPassword() {
          return hashedPassword;
     }
     public void sethashedPassword(String hashedPassword) {
          this.hashedPassword = hashedPassword;
     }

     public List<Ticket> getTicketsBooked() {
          return ticketsBooked;
     }
     public void setTicketsBooked(List<Ticket> ticketsBooked) {
          this.ticketsBooked = ticketsBooked;
     }

     public String getUserId() {
          return userId;
     }
     public void setUserId(String userId) {
          this.userId = userId;
     }

     public void printtickets(){
          for (Ticket ticket: ticketsBooked){
               System.out.println(ticket);
          }
     }

     @Override
     public String toString() {
          return String.format("User{name='%s', userId='%s', ticketsBooked=%s}", name, userId, ticketsBooked);
     }
}