package com.cs180proj.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
// used to check if two references point to the same object
import static org.junit.jupiter.api.Assertions.assertSame;
import org.junit.jupiter.api.Test;
/**
 * CS 18000 Group Project
 *
 * This class contains various methods (test cases) which test
 * the functionality of MainFrame.
 *
 * @author (Rohan Kannan, Alistair Joseph, Lydia Schmucker, Stephen Tushentsov) lab sec 19
 *
 * @version May 2, 2025
 *
 */
public class MainFrameTestCase {
    
    /**  
    * Test cases for setCurrentUser method, getCurrentUser method, 
    * getClient() method, showPanel() method, and valid title check.
    * All test cases test constructor and fields of MainFrame class.
    */
    // Test case for setCurrentUser method, getCurrentUser method, constructor, and fields
    @Test
    public void testSetAndGetCurrentUser() throws Exception {
        Server server = new Server();
        Thread serverThread = new Thread(new Runnable() {
            public void run() {
                try {
                    server.startServer(4242);
                } catch (Exception e) {
                }
            }
        });
        serverThread.start();
        Thread.sleep(200);
        MainFrame f = new MainFrame();
        User u = new User("steph", "1234", 22.43, "pli843");
        f.setCurrentUser(u);
        assertSame(u, f.getCurrentUser());
        f.dispose();
        server.stopServer();
        serverThread.join(1000);
    }

    // Test case for getClient() method, constructor, and fields
    @Test
    public void testGetClient() throws Exception {
        Server server = new Server();
        Thread serverThread = new Thread(new Runnable() {
            public void run() {
                try {
                    server.startServer(4242);
                } catch (Exception e) {
                }
            }
        });
        serverThread.start();
        Thread.sleep(200);
        MainFrame f = new MainFrame();
        boolean b = true;
        if (f.getClient() == null) {
            b = false;
        }
        assertEquals(true, b);
        f.dispose();
        server.stopServer();
        serverThread.join(1000);
    }

    // Test case for showPanel() method, constructor, and fields
    @Test
    public void testShowPanel() throws Exception {
        Server server = new Server();
        Thread serverThread = new Thread(new Runnable() {
            public void run() {
                try {
                    server.startServer(4242);
                } catch (Exception e) {
                }
            }
        });
        serverThread.start();
        Thread.sleep(200);
        MainFrame f = new MainFrame();
        f.showPanel("Login");
        f.showPanel("Register");
        f.showPanel("Hub");
        f.showPanel("Listings");
        f.showPanel("AddListing");
        f.showPanel("EditListings");
        f.dispose();
        server.stopServer();
        serverThread.join(1000);
    }

    // Test case for if the title is valid, constructor, and fields
    @Test
    public void testGetTitle() throws Exception {
        Server server = new Server();
        Thread serverThread = new Thread(new Runnable() {
            public void run() {
                try {
                    server.startServer(4242);
                } catch (Exception e) {
                }
            }
        });
        serverThread.start();
        Thread.sleep(200);
        MainFrame f = new MainFrame();
        assertEquals("MotorMarket", f.getTitle());
        f.dispose();
        server.stopServer();
        serverThread.join(1000);
    }

}
