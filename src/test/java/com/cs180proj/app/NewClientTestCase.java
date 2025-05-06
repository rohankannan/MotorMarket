package com.cs180proj.app;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * CS 18000 Group Project
 *
 * This class contains various methods (test cases) which test
 * the functionality of NewClient.
 *
 * @author (Rohan Kannan, Alistair Joseph, Lydia Schmucker, Stephen Tushentsov) lab sec 19
 *
 * @version May 2, 2025
 *
 */
public class NewClientTestCase {
    
    /**
     * Test case for NewClient constructor, fields,
     * and all methods inside the class.
     */
    @Test
    public void testNewClient() throws Exception {
        Server server = new Server();
        Thread serverThread = new Thread(() -> {
            try {
                server.startServer(4242);
            } catch (Exception e) {
            }
        });
        serverThread.start();
        Thread.sleep(150);
        NewClient client = new NewClient("localhost", 4242);
        Object response = client.sendCommand("GET_USERS");
        assertNotNull(response);
        assertEquals(java.util.ArrayList.class, response.getClass());
        Object invalidResponse = client.sendCommand("invalid");
        assertEquals("Invalid command.", invalidResponse);
        client.close();
        assertTrue(client.isClosed());
        server.stopServer();
        serverThread.join(500);
    }
}
