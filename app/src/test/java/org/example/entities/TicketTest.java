package org.example.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class TicketTest {
    private Ticket ticket;

    @BeforeEach
    public void setUp(){
        ticket = new Ticket("T123", "U456", "Banglore", "Delhi", "2025-12-10", null);
    }
    
    @Test 
    public void testInitialization(){
        assertNotNull(ticket);
        assertEquals("T123", ticket.getTicketId());
        assertEquals("U456", ticket.getUserId());
        assertEquals("Banglore", ticket.getSourceStation());
        assertEquals("Delhi", ticket.getDestinationStation());
        assertEquals("2025-12-10", ticket.getDateOfTravel());
    }

    @Test
    public void testGetterAndSetter(){
        ticket.setTicketId("T456");
        assertEquals("T456", ticket.getTicketId());

        ticket.setUserId("U123");
        assertEquals("U123", ticket.getUserId());

        ticket.setSourceStation("Pune");
        assertEquals("Pune", ticket.getSourceStation());

        ticket.setDestinationStation("Jaipur");
        assertEquals("Jaipur", ticket.getDestinationStation());

        ticket.setDateOfTravel("2025-07-24");
        assertEquals("2025-07-24", ticket.getDateOfTravel());

        Train tempTrain = new Train();
        tempTrain.setTrainId("TR987");
        ticket.setTrain(tempTrain);

        assertNotNull(ticket.getTrain());
        assertEquals("TR987", ticket.getTrain().getTrainId());
    }

    @Test
    public void testTicketValidity(){
        assertTrue(ticket.isValid());

        ticket.setTicketId(null);
        assertFalse(ticket.isValid());

        ticket.setUserId(null);
        assertFalse(ticket.isValid());

        ticket.setSourceStation(null);
        assertFalse(ticket.isValid());

        ticket.setDestinationStation(null);
        assertFalse(ticket.isValid());
    }
    
    @Test
    public void testToString(){
        Train tempTrain = new Train();
        tempTrain.setTrainId("T420");
        tempTrain.setTrainNo("024");
        ticket.setTrain(tempTrain);

        String op = ticket.toString();

        assertTrue(op.contains("ticketId=T123"));
        assertTrue(op.contains("userId=U456"));
        assertTrue(op.contains("source=Banglore"));
        assertTrue(op.contains("destination=Delhi"));
        assertTrue(op.contains("dateOfTravel=2025-12-10"));
        assertTrue(op.contains("train=T420"));
    }
}