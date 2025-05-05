package com.cs180proj.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
/**
 * CS 18000 Group Project
 *
 * This class contains various methods (test cases) which test
 * the functionality of the constructors, getters, setters, fields,
 * and additional methods of the Chat class.
 *
 * @authors (Rohan Kannan, Alistair Joseph, Lydia Schmucker, Stephen Tushentsov) lab sec 19
 *
 * @version May 5, 2025
 *
 */
public class ChatTestCase {

    // Test cases for Chat class getters, constructor, and fields
    // All getter test cases simultaneously test the constructor and fields
    
    // getSender test case, tests both constructors
    @Test
    public void testGetSender() {
        Chat chat = new Chat("john", "jane", "Hello there!", 1234567890L);
        assertEquals("john", chat.getSender());
        Chat chat2 = new Chat("jane", "john", "Hello");
        assertEquals("jane", chat2.getSender());
    }

    // getRecipient test case
    @Test
    public void testGetRecipient() {
        Chat chat = new Chat("john", "jane", "Hello there!", 1234567890L);
        assertEquals("jane", chat.getRecipient());
    }

    // getMessage test case
    @Test
    public void testGetMessage() {
        Chat chat = new Chat("john", "jane", "Hello there!", 1234567890L);
        assertEquals("Hello there!", chat.getMessage());
    }

    // getTimestamp test case
    @Test
    public void testGetTimestamp() {
        Chat chat = new Chat("john", "jane", "Hello there!", 1234567890L);
        assertEquals(1234567890L, chat.getTimestamp());
    }

    // Test cases for Chat class setters
    // setTimestamp test case
    @Test
    public void testSetTimestamp() {
        Chat chat = new Chat("john", "jane", "Hello there!", 1234567890L);
        chat.setTimestamp(9876543210L);
        assertEquals(9876543210L, chat.getTimestamp());
    }

    // Test case for Chat class toString method
    @Test
    public void testToString() {
        Chat chat = new Chat("john", "jane", "Hello there!", 1234567890L);
        String expected = "john\tjane\tHello there!\t1234567890";
        assertEquals(expected, chat.toString());
    }
}