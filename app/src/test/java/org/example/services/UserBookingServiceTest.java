package org.example.services;

import org.example.entities.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.io.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class UserBookingServiceTest {
    private UserBookingService usbobj;

    @BeforeEach
    public void setup() throws IOException{
        usbobj = new UserBookingService();
        usbobj.userList = new ArrayList<>();
    }

    @Test
    public void testSignUp(){
        User tempUser = new User("tempusername", "temppassword", UUID.randomUUID().toString());
        boolean tempsignup = usbobj.Signup(tempUser);

        assertTrue(tempsignup);
        assertEquals(1, usbobj.userList.size());
    }

    @Test
    public void testLogin(){
        User tempUser = new User("username", "password", UUID.randomUUID().toString());
        usbobj.Signup(tempUser);

        boolean templogin1 = usbobj.loginUser("username", "password");
        assertTrue(templogin1);

        boolean templogin2 = usbobj.loginUser("username", "wrongpassword");
        assertFalse(templogin2);

        boolean templogin3 = usbobj.loginUser("unknown", "unknown");
        assertFalse(templogin3);
    }

    @Test
    public void testSeatBooking(){
        Train tempTrain = new Train();
        tempTrain.setTrainId("TR101");

        List<List<Integer>> tempSeats = new ArrayList<>();
        for (int i=0; i<4; i++){
            List<Integer> row = new ArrayList<>(List.of(0,0,0,0,0,0));
            tempSeats.add(row);
        }
        tempTrain.setSeats(tempSeats);

        boolean seatbooking = usbobj.bookTrainSeat(tempTrain, 2, 2);
        assertTrue(seatbooking);
    }

    @Test
    public void testSeatCancellation(){
        User tempUser = new User("testusn", "testpwd", UUID.randomUUID().toString());
        List<Ticket> tempTicketsBooked = new ArrayList<>();
        usbobj.user = tempUser;

        Ticket t1 = new Ticket("T001", usbobj.user.getUserId(), "NYC", "LA", "2025-12-10", null);
        Ticket t2 = new Ticket("T002", usbobj.user.getUserId(), "Boston", "Chicago", "2025-11-15", null);

        tempTicketsBooked.add(t1);
        tempTicketsBooked.add(t2);

        usbobj.user.setTicketsBooked(tempTicketsBooked);


        boolean ticketcancellation = usbobj.cancelBooking();
        assertTrue(ticketcancellation);
    }

    @Test 
    public void testFetchBookings(){
        User tempUser = new User("tempusn", "temppwd", UUID.randomUUID().toString());
        usbobj.user = tempUser;
    
        Ticket t1 = new Ticket("T001", usbobj.user.getUserId(), "Banlgore", "Delhi", "24-07-2025", null);
        usbobj.user.getTicketsBooked().add(t1);

        ByteArrayOutputStream opstream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(opstream));

        usbobj.fetchBookings();
        System.setOut(System.out);

        String op = opstream.toString().trim();
        assertTrue(op.contains("ticketId=T001"));
        assertTrue(op.contains("source=Banglore"));
        assertTrue(op.contains("destination=Delhi"));
        assertTrue(op.contains("dateOfTravel=24-07-2025"));
    }
}
