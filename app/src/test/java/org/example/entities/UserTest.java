package org.example.entities;

import java.util.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.*;

public class UserTest{
    private User user;

    @BeforeEach
    public void setUp(){
        user = new User("testUser", "testPassword", UUID.randomUUID().toString());
    }

    @Test
    public void testUserInitialization(){
        assertNotNull(user);
        assertEquals("testUser", user.getName());
        assertEquals("testPassword", user.getPassword());
        assertNotNull(user.getUserId());
    }

    @Test
    public void testSetterAndGetter(){
        user.setName("newName");
        assertEquals("newName", user.getName());

        user.setPassword("newPassword");
        assertEquals("newPassword", user.getPassword());

        user.sethashedPassword("HashedPassword");
        assertEquals("HashedPassword", user.gethashedPassword());
    }

    @Test
    public void testAddTickets(){
        Ticket t1 = new Ticket("123", "U456f", "NYC", "LA", "2025-12-10", null);
        Ticket t2 = new Ticket("T456", "U789", "Banglore", "delhi", "2025-11-15", null);

        user.getTicketsBooked().add(t1);
        user.getTicketsBooked().add(t2);

        assertEquals(2, user.getTicketsBooked().size());
    }

    @Test 
    public void testPrintTickets(){
        ByteArrayOutputStream opstream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(opstream));

        Ticket t1 = new Ticket("123", "U456f", "NYC", "LA", "2025-12-10", null);
        Ticket t2 = new Ticket("T456", "U789", "Banglore", "delhi", "2025-11-15", null);

        user.getTicketsBooked().add(t1);
        user.getTicketsBooked().add(t2);

        user.printtickets();
        System.setOut(System.out);
        String op = opstream.toString().trim();

        assertTrue(op.contains("ticketId=T123"));
        assertTrue(op.contains("userId=U456f"));
        assertTrue(op.contains("source=NYC"));
        assertTrue(op.contains("destination=LA"));

        assertTrue(op.contains("ticketId=T456"));
        assertTrue(op.contains("userId=U789"));
        assertTrue(op.contains("source=Banglore"));
        assertTrue(op.contains("destination=Delhi"));
    }
}