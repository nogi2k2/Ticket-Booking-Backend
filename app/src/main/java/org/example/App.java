package org.example;

import org.example.entities.*;
import org.example.services.*;

import java.io.IOException;
import java.util.*;

public class App {

    public static void main(String[] args) {
        System.out.println("Running Ticket Booking System:");
        Scanner sc = new Scanner(System.in);
        int opt = 0;
        UserBookingService ubsobj;
        try{
            ubsobj = new UserBookingService();
        }catch (IOException e){
            System.out.println(String.format("Error: %s", e));
            return;
        }
       opt = Integer.parseInt(sc.nextLine());
        while (opt != 7){
            System.out.println("Menu:");
            System.out.println("1. Sign up");
            System.out.println("2. Login");
            System.out.println("3. Fetch Bookings");
            System.out.println("4. Search Trains");
            System.out.println("5. Book seats");
            System.out.println("6. Cancel Booking");
            System.out.println("Exit Menu");
            String temp;
            while (sc.hasNextLine()){
                temp = sc.nextLine();
                opt = Integer.parseInt(temp);
            }
            opt = Integer.parseInt(sc.nextLine());
            Train trainforbooking = new Train();
            
            switch (opt){
                case 1:
                    System.out.println("Enter username:");
                    String namesignup = sc.nextLine();
                    System.out.println("Enter Password:");
                    String pwdsignup = sc.nextLine();
                    User user = new User(namesignup, pwdsignup, UUID.randomUUID().toString());
                    Boolean signup = ubsobj.Signup(user);
                    if (signup.equals(Boolean.TRUE)){System.out.println("User Signed up Successfully.");}
                    else{System.out.println("User Already Exists!");};
                    break;
                case 2:
                    System.out.println("Enter Username:");
                    String userlogin = sc.nextLine();
                    System.out.println("Enter Password:");
                    String pwdlogin = sc.nextLine();
                    Boolean login = ubsobj.loginUser(userlogin, pwdlogin);
                    if (login.equals(Boolean.TRUE)){System.out.println("Logged in Succesfully");}
                    else{System.out.println("User does not exist");}
                    break;
                case 3:
                    System.out.println("Fetching Bookings");
                    ubsobj.fetchBookings(); 
                    break;
                case 4:
                    System.out.println("Enter Source Station");
                    String sourceStation = sc.nextLine();
                    System.out.println("Enter Destination Station");
                    String destinationStation = sc.nextLine();
                    List<Train> trains = ubsobj.getTrains(sourceStation, destinationStation);
                    for (Train train: trains){
                        System.out.println(String.format("Train Id: %s", train.getTrainId()));
                        System.out.println(String.format("Train No: %s", train.getTrainNo()));
                        for (Map.Entry<String, String> entry: train.getStationTimes().entrySet()){
                            System.out.println(String.format("Station: %s\nTime: %s", entry.getKey(), entry.getValue()));
                        }
                    }
                    System.out.println("Select the train for booking seats");
                    trainforbooking = trains.get(Integer.parseInt(sc.nextLine()));
                    break;
                case 5:
                    List<List<Integer>> seats = ubsobj.fetchSeats(trainforbooking);
                    System.out.println("Displaying Seat Booking Status:");
                    for (List<Integer> row: seats){
                        for (Integer col: row){
                            System.out.println(col + " ");
                        }
                        System.out.println();
                    }

                    System.out.println("Enter the seat row and column:");
                    temp = sc.nextLine();
                    int seatrow = Integer.parseInt(temp);
                    temp = sc.nextLine();
                    int seatcol = Integer.parseInt(temp);
                    Boolean seatbookingstatus = ubsobj.bookTrainSeat(trainforbooking, seatrow, seatcol);
                    if (seatbookingstatus.equals(Boolean.TRUE)){System.out.println("Seat Booked Succesfully");}
                    else{System.out.println("Error Booking seat");}
                    break;
                default:
                    break;
            }
        }
    }
}