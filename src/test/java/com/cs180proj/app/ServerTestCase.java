package com.cs180proj.app;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
/**
 * CS 18000 Group Project
 *
 * This class contains various methods (test cases) which test
 * the functionality of Server
 *
 * @author (Rohan Kannan, Alistair Joseph, Lydia Schmucker, Stephen Tushentsov) lab sec 19
 *
 * @version April 16, 2025
 *
 */
public class ServerTestCase {
    
    /**  
    * Test case for isServerRunning() method, startServer() method, 
    * stopServer() method, constructor, and fields of Server class.
    * Server also contains private class, so sending commands is also
    * tested within this test case
    */
    @Test
    public void testIsServerRunningStartingStoppingSendCommand() throws Exception {
        Server server = new Server();
        Thread serverThread = new Thread(new Runnable() {
            public void run() {
                try {
                    server.startServer(8080);
                } catch (Exception e) {
                }
            }
        });
        serverThread.start();
        Thread.sleep(150);
        assertEquals(true, server.isServerRunning());
        Socket testSocket = new Socket("localhost", 8080);
        ObjectOutputStream testOOS = new ObjectOutputStream(testSocket.getOutputStream());
        testOOS.flush();
        ObjectInputStream testOIS = new ObjectInputStream(testSocket.getInputStream());
        testOOS.writeObject("GET_USERS");
        testOOS.flush();
        Object r = testOIS.readObject();
        assertEquals(true, r instanceof ArrayList);
        testOOS.writeObject("EXIT");
        testOOS.flush();
        testOIS.close();
        testOOS.close();
        testSocket.close();
        server.stopServer();
        serverThread.join(1000);
        assertEquals(false, server.isServerRunning());
    }
}
