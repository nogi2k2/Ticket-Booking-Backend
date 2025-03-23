package org.example.entities;

import java.util.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class TrainTest{
    private Train train;

    @BeforeEach
    public void setUp(){
        train = new Train();
        train.setTrainId("TR123");
        train.setTrainNo("12345");

        List<List<Integer>> seats = new ArrayList<>();
        for (int i=0; i<4; i++){
            List<Integer> row = new ArrayList<>(Arrays.asList(0,0,0,0,0,0));
            seats.add(row);
        }
        train.setSeats(seats);

        HashMap<String, String> stationTimes = new HashMap<>();
        stationTimes.put("Bangalore", "13:50:00");
        stationTimes.put("Jaipur", "18:30:00");
        stationTimes.put("Delhi", "22:00:00");
        train.setStationTimes(stationTimes);
        
        List<String> stations = Arrays.asList("Banglore", "Jaipur", "Delhi");
        train.setStations(stations);

    }

    @Test
    public void testInitialization(){
        assertNotNull(train);
        assertEquals("TR123", train.getTrainId());
        assertEquals("12345", train.getTrainId());
        assertEquals(2, train.getStationTimes().size());
        assertEquals(2, train.getStations().size());
    }

    @Test
    public void testGetterAndSetter(){
        train.setTrainId("TR420");
        assertEquals("TR420", train.getTrainId());

        train.setTrainNo("456123");
        assertEquals("456123", train.getTrainNo());

        List<String> newStations = Arrays.asList("Pune", "Hydrebad");
        train.setStations(newStations);
        assertEquals(2, train. getStations());

        HashMap<String, String> newStationTimes = new HashMap<>();
        newStationTimes.put("Pune", "12:00:00");
        train.setStationTimes(newStationTimes);
        assertEquals(1, train.getStationTimes().size());
    }

    @Test
    public void testSeatBooking(){
        List<List<Integer>> initseats = train.getSeats();
        for (List<Integer> row: initseats){
            for (int seat: row){
                assertEquals(0, seat);
            }
        }

        train.getSeats().get(0).set(1,1);
        assertEquals(1, train.getSeats().get(0).get(1));

        train.getSeats().get(2).set(4,1);
        assertEquals(1, train.getSeats().get(2).get(4));
    }

    @Test
    public void testPrintSchedule(){
        ByteArrayOutputStream as = new ByteArrayOutputStream();
        System.setOut(new PrintStream(as));

        train.printSchedule();
        System.setOut(System.out);
        String op = as.toString().trim();

        assertTrue(op.contains("Station: Banglore"));
        assertTrue(op.contains("Time: 13:50:00"));  
        assertTrue(op.contains("Station: Jaipur"));
        assertTrue(op.contains("Time: 13:50:00"));
        assertTrue(op.contains("Station: Delhi"));
        assertTrue(op.contains("Time: 22:00:00"));
    }
}
