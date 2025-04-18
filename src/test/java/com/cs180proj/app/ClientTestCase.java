package com.cs180proj.app;
// imports
import org.junit.jupiter.api.Test;
import java.io.*;
import java.net.Socket;
import static org.junit.jupiter.api.Assertions.*;

/**
 * CS 18000 Group Project
 *
 * This class contains various methods (test cases) which test
 * the functionality of Client 
 *
 * @author (Rohan Kannan, Alistair Joseph, Lydia Schmucker, Stephen Tushentsov) lab sec 19
 *
 * @version April 10, 2025
 *
 */

public class ClientTestCase {
    /**
     * Test case to ensure that the client can connect to server.
     */
    @Test
    public void testServerConnection() {
        // testing the socket object and catching exception if the client is unable to connect
        try (Socket socket = new Socket("localhost", 4242)) {
            assertTrue(socket.isConnected(), "Client should be able to connect to server.");
        } catch (IOException e) {
            fail("Failed to connect to server: " + e.getMessage());
        }
    }

    /**
     * Test case to ensure that  GET_USERS  works correctly
     */
    @Test
    public void testGetUsers() {
        // trying these objects
        try (Socket socket = new Socket("localhost", 4242); 
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream()); 
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream())) {
            // writing the command
            oos.writeObject("GET_USERS");
            oos.flush();

            // collecting response and checking if it returns a list of users
            Object response = ois.readObject();
            assertTrue(response instanceof java.util.ArrayList, "Server should return a list of users.");
        } catch (ClassNotFoundException e) {
            fail("Error occurred in GET_USERS test: " + e.getMessage());
        } catch (IOException e) {
            fail("Error occurred in GET_USERS test: " + e.getMessage());
        }
        
    }

    /**
     * Test case to ensure that GET_LISTINGS  works correctly
     */
    @Test
    public void testGetListings() {
        // trying each object and the command
        try (Socket socket = new Socket("localhost", 4242); 
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream())) {
            // writing the command
            oos.writeObject("GET_LISTINGS");
            oos.flush();

            // collecting the response and test if it returns a list of listings
            Object response = ois.readObject();
            assertTrue(response instanceof java.util.ArrayList, "Server should return a list of listings.");
        } catch (ClassNotFoundException e) {
            fail("Error occurred in GET_LISTING test: " + e.getMessage());
        } catch (IOException e) {
            fail("Error occurred in GET_LISTING test: " + e.getMessage());
        }
    }

    /**
     * Test case to ensure that error handling is proper
     */
    @Test
    public void testInvalidCommand() {
        // trying with these objects
        try (Socket socket = new Socket("localhost", 4242);
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream())) {
            // writing command
            oos.writeObject("INVALID_COMMAND");
            oos.flush();

            // collecting response and checking if the server returns the correct message
            Object response = ois.readObject();
            assertEquals("Error: Invalid Command", response, "Server should return an error for invalid commands.");
        } catch (ClassNotFoundException e) {
            fail("Error occurred in invalid command test: " + e.getMessage());
        } catch (IOException e) {
            fail("Error occurred in invalid command test: " + e.getMessage());
        }
    }

    
}
