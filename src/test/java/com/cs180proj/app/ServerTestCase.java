package com.cs180proj.app;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
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
    * Test cases for isServerRunning() method, startServer() method, 
    * stopServer() method, constructor, and fields of Server class.
    */
    // Test case for isServerRunning() method, startServer() method, stopServer() method, constructor, and fields
    @Test
    public void testIsServerRunningStartingStopping() throws Exception {
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
        assertEquals(true, server.isServerRunning(), "Server should not be running after stopServer() is called.");
        server.stopServer();
        serverThread.join(1000);
        assertEquals(false, server.isServerRunning(), "Server should not be running after stopServer() is called.");
    }

    /**  
    * Test cases for checkClientCommand() & workWithClient() methods
    */
    // Test case for checkClientCommand() method, uses the GET_USERS command
    @Test
    public void testCheckClientCommand() throws Exception {
        Server server = new Server();
        ByteArrayOutputStream testOut = new ByteArrayOutputStream();
        ObjectOutputStream testOOS = new ObjectOutputStream(testOut);
        testOOS.writeObject("test");
        testOOS.flush();
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(byteOut);
        ByteArrayInputStream byteIn = new ByteArrayInputStream(testOut.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(byteIn);
        server.checkClientCommand("GET_USERS", ois, oos);
        oos.flush();
        ObjectInputStream rs = new ObjectInputStream(new ByteArrayInputStream(byteOut.toByteArray()));
        Object r = rs.readObject();
        assertEquals(true, r instanceof ArrayList);
    }

    // Test case for workWithClient() method, also tests exitability 
    @Test
    public void testWorkWithClient() throws Exception {
        Server server = new Server();
        ServerSocket serverSocket = new ServerSocket(8080);
        int currentPort = serverSocket.getLocalPort();
        Thread serverThread = new Thread(new Runnable() {
            public void run() {
                try {
                    Socket socket = serverSocket.accept();
                    server.workWithClient(socket);
                } catch (Exception e) {
                }
            }
        });
        serverThread.start();
        Socket socketTwo = new Socket("localhost", currentPort);
        ObjectOutputStream oos = new ObjectOutputStream(socketTwo.getOutputStream());
        oos.flush();
        ObjectInputStream ois = new ObjectInputStream(socketTwo.getInputStream());
        oos.writeObject("EXIT");
        oos.flush();
        socketTwo.close();
        serverSocket.close();
        serverThread.join(1000);
        assertEquals(true, socketTwo.isClosed() && !serverThread.isAlive());
    }

}
