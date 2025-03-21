package org.example;

import org.example.entities.*;
import org.example.services.*;

import java.io.IOException;
import java.util.*;
import java.io.*;

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

            String temp = sc.nextLine();
            opt = Integer.parseInt(temp);
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


// public class App {

//     public static void main(String[] args) {
//         System.out.println("Running Ticket Booking System:");

//         try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            
//             UserBookingService ubsobj;
//             try {
//                 ubsobj = new UserBookingService();
//             } catch (IOException e) {
//                 System.out.println(String.format("Error: %s", e));
//                 return;
//             }

//             int opt = 0;
//             while (opt != 7) {
//                 System.out.println("Menu:");
//                 System.out.println("1. Sign up");
//                 System.out.println("2. Login");
//                 System.out.println("3. Fetch Bookings");
//                 System.out.println("4. Search Trains");
//                 System.out.println("5. Book seats");
//                 System.out.println("6. Cancel Booking");
//                 System.out.println("7. Exit Menu");

//                 try {
//                     opt = Integer.parseInt(reader.readLine());
//                 } catch (NumberFormatException e) {
//                     System.out.println("Invalid input. Please enter a number.");
//                     continue;
//                 }

//                 Train trainforbooking = new Train();

//                 switch (opt) {
//                     case 1:
//                         System.out.println("Enter username:");
//                         String namesignup = reader.readLine();

//                         System.out.println("Enter Password:");
//                         String pwdsignup = reader.readLine();

//                         User user = new User(namesignup, pwdsignup, UUID.randomUUID().toString());
//                         Boolean signup = ubsobj.Signup(user);

//                         System.out.println(signup ? "User Signed up Successfully." : "User Already Exists!");
//                         break;

//                     case 2:
//                         System.out.println("Enter Username:");
//                         String userlogin = reader.readLine();

//                         System.out.println("Enter Password:");
//                         String pwdlogin = reader.readLine();

//                         Boolean login = ubsobj.loginUser(userlogin, pwdlogin);

//                         System.out.println(login ? "Logged in Successfully" : "User does not exist");
//                         break;

//                     case 3:
//                         System.out.println("Fetching Bookings");
//                         ubsobj.fetchBookings();
//                         break;

//                     case 4:
//                         System.out.println("Enter Source Station");
//                         String sourceStation = reader.readLine();

//                         System.out.println("Enter Destination Station");
//                         String destinationStation = reader.readLine();

//                         List<Train> trains = ubsobj.getTrains(sourceStation, destinationStation);
//                         for (Train train : trains) {
//                             System.out.println(String.format("Train Id: %s", train.getTrainId()));
//                             System.out.println(String.format("Train No: %s", train.getTrainNo()));

//                             for (Map.Entry<String, String> entry : train.getStationTimes().entrySet()) {
//                                 System.out.println(String.format("Station: %s\nTime: %s", entry.getKey(), entry.getValue()));
//                             }
//                         }

//                         System.out.println("Select the train for booking seats");
//                         int trainIndex = Integer.parseInt(reader.readLine());
//                         if (trainIndex >= 0 && trainIndex < trains.size()) {
//                             trainforbooking = trains.get(trainIndex);
//                         } else {
//                             System.out.println("Invalid train index.");
//                         }
//                         break;

//                     case 5:
//                         List<List<Integer>> seats = ubsobj.fetchSeats(trainforbooking);
//                         System.out.println("Displaying Seat Booking Status:");

//                         for (List<Integer> row : seats) {
//                             for (Integer col : row) {
//                                 System.out.print(col + " ");
//                             }
//                             System.out.println();
//                         }

//                         System.out.println("Enter the seat row and column:");
//                         String[] seatInput = reader.readLine().split(" ");
//                         if (seatInput.length == 2) {
//                             int seatrow = Integer.parseInt(seatInput[0]);
//                             int seatcol = Integer.parseInt(seatInput[1]);

//                             Boolean seatbookingstatus = ubsobj.bookTrainSeat(trainforbooking, seatrow, seatcol);
//                             System.out.println(seatbookingstatus ? "Seat Booked Successfully" : "Error Booking seat");
//                         } else {
//                             System.out.println("Invalid seat format. Use: row col");
//                         }
//                         break;

//                     default:
//                         System.out.println("Exiting...");
//                         break;
//                 }
//             }

//         } catch (IOException e) {
//             e.printStackTrace();
//         }
//     }
// }
