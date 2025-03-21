package org.example.services;

import org.example.entities.*;
import org.example.util.*;
import java.util.*;
import java.io.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

public class UserBookingService {
    private User user;
    private List<User> userList;
    private ObjectMapper objectMapper = new ObjectMapper();
    private static final String USERS_PATH = "C:\\Users\\Nogi2\\OneDrive\\Desktop\\bruh\\Java-Backend\\IRCTC-Backend\\app\\src\\main\\java\\org\\example\\localdb\\users.json";

    public UserBookingService(User user) throws IOException{
        this.user = user;
        loadUserList();
    }

    public UserBookingService() throws IOException{
        loadUserList();
    }

    public void loadUserList() throws IOException{
        File users = new File(USERS_PATH);
        if (users.exists()){
            userList = objectMapper.readValue(users, new TypeReference<List<User>>() {});
        }else{
            userList = new ArrayList<User>();
        }
    }

    public Boolean loginUser(String username, String password){
        Optional<User> userfound = userList.stream()
        .filter(usercheck -> usercheck.getName().equals(username) && usercheck.getPassword().equals(password))
        .findFirst();
        return userfound.isPresent();
    }

    public Boolean Signup(User newUser){
        Optional<User> existingUser = userList.stream()
        .filter(user -> user.getUserId().equals(newUser.getUserId())).findFirst();
        

        if (existingUser.isPresent()){
            System.out.println("User already present");
            return false;
        }

        try{
            newUser.sethashedPassword(UserServiceUtil.hashPassword(newUser.getPassword()));
            userList.add(newUser);
            saveUserList();
            return Boolean.TRUE;
        }catch (IOException ex){
            return Boolean.FALSE;
        }
    }

    public void saveUserList() throws IOException{
        File users = new File(USERS_PATH);
        objectMapper.writeValue(users, userList);
    }

    public List<List<Integer>> fetchSeats(Train train){
        return train.getSeats();
    }

    public void fetchBookings(){
        Optional<User> founduser = userList.stream().filter(passeduser -> {
            return passeduser.getName().equals(user.getName()) && UserServiceUtil.checkPassword(user.getPassword(), passeduser.gethashedPassword());
        }).findFirst();

        if (founduser.isPresent()){
            founduser.get().printtickets();
        }
    }

    public Boolean bookTrainSeat(Train train, int coach, int seat){
        try{
            TrainService ts = new TrainService();
            List<List<Integer>> seats = train.getSeats();
            if (coach>=0 && coach < seats.size() && seat < seats.get(coach).size()){
                if (seats.get(coach).get(seat) == 0){
                    seats.get(coach).set(seat, 1);
                    train.setSeats(seats);
                    ts.addTrain(train);
                    return true;
                }else{return false;}
            }else{return false;}
        }catch (IOException ex){
            return Boolean.FALSE;
        }
    }

    public Boolean cancelBooking(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the ticketId for cancellation: ");
        String tempId = sc.next();
        sc.close();

        if (tempId == null || tempId.isEmpty()){
            System.out.println("Invalid Ticket ID");
            return Boolean.FALSE;
        }

        boolean removed = user.getTicketsBooked().removeIf(ticket -> ticket.getTicketId().equals(tempId));

        if (removed){
            System.out.println("Ticket Id " + tempId + " is cancelled");
            return Boolean.TRUE;
        }else{
            System.out.println("Ticket with Id: " + tempId + " not found");
            return Boolean.FALSE;
        }
    }

    public List<Train> getTrains(String source, String destination){
        try{
            TrainService ts = new TrainService();
            return ts.searchTrains(source, destination);
        }catch (IOException ex){
            return new ArrayList<>();
        }
    }
}




