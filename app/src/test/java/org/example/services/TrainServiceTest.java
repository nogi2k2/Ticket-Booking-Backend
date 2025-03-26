package org.example.services;

import java.util.*;
import java.io.*;

import org.example.entities.Train;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TrainServiceTest {
    private TrainService tsobj;
    
    @BeforeEach
    public void setUp() throws IOException{
        tsobj = Mockito.spy(new TrainService());
        List<Train> mockTrainList = new ArrayList<>();

        Train t1 = new Train();
        t1.setTrainId("TR001");
        t1.setTrainNo("420");
        t1.setStations(List.of("Banglore", "Pune", "Delhi"));

        Train t2 = new Train();
        t2.setTrainId("TR002");
        t2.setTrainNo("024");
        t2.setStations(List.of("Kolkata", "Jabalpur", "Hydrebad"));
        
        mockTrainList.add(t1);
        mockTrainList.add(t2);

        doReturn(mockTrainList).when(tsobj).getTrainList();
    }

    @Test
    public void testGetTrainList(){
        List<Train> trains = tsobj.getTrainList();
        assertNotNull(trains);
        assertEquals(2, trains.size());

        assertEquals("TR001", trains.get(0).getTrainId());
        assertEquals("420", trains.get(0).getTrainNo());
        assertEquals(List.of("Banglore", "Pune", "Delhi"), trains.get(0).getStations());

        assertEquals("TR002", trains.get(1).getTrainId());
        assertEquals("024", trains.get(1).getTrainNo());
        assertEquals(List.of("Kolkata", "Jabalpur", "Hydrebad"), trains.get(1).getStations());
    }

    @Test
    public void testSearchTrains_ValidRoute(){
        List<Train> tempTrainList = tsobj.getTrainList();
        Train t1 = tempTrainList.get(0);
        List<Train> searchResults = tsobj.searchTrains("Banglore", "Pune");

        assertNotNull(searchResults);
        assertEquals(1, searchResults.size());
        assertEquals(t1.getTrainId(), searchResults.get(0).getTrainId());
        assertEquals(t1.getTrainNo(), searchResults.get(0).getTrainNo());
    }

    @Test
    public void testSearchTrains_InvalidRoute(){
        List<Train> searchResults = tsobj.searchTrains("Jammu", "KanyaKumari");
        assertNotNull(searchResults);
        assertEquals(0, searchResults.size());
    }

    @Test
    public void testAddTrain(){
        Train tempTrain = new Train();
        tempTrain.setTrainId("TR002");
        tempTrain.setTrainNo("204");
        tempTrain.setStations(List.of("Goa", "Manglore", "Kottayam"));

        boolean trainAdded = tsobj.addTrain(tempTrain);
        assertTrue(trainAdded);
    }

    @Test
    public void testUpdateTrain(){
        Train tempTrain = new Train();
        tempTrain.setTrainId("TR001");
        tempTrain.setTrainNo("231");
        tempTrain.setStations(List.of("Pune", "Banglore", "Delhi"));

        boolean trainUpdated = tsobj.updateTrain(tempTrain);
        assertTrue(trainUpdated);
    }
}
