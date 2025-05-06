package com.cs180proj.app;

import javax.swing.JButton;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
/**
 * CS 18000 Group Project
 *
 * This class contains various methods (test cases) which test
 * the functionality of HubPanel.
 *
 * @author (Rohan Kannan, Alistair Joseph, Lydia Schmucker, Stephen Tushentsov) lab sec 19
 *
 * @version May 2, 2025
 *
 */
public class HubPanelTestCase {
    
    // Test case for HubPanel class constructor and fields
    // Tests if clicking the "View Listings" button changes the current panel to "Listings"
    @Test
    public void testHubPanel() throws Exception {
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
        Thread.sleep(150);
        MainFrame frame = new MainFrame();
        frame.setVisible(false);
        User user = new User("sc30", "3ball", 22.43, "pli843");
        frame.setCurrentUser(user);
        HubPanel panel = new HubPanel(frame, frame.getClient());
        JButton viewListingsButton = null;
        for (java.awt.Component c1 : panel.getComponents()) {
            if (c1 instanceof javax.swing.JPanel) {
                for (java.awt.Component c2 : ((javax.swing.JPanel) c1).getComponents()) {
                    if (c2 instanceof javax.swing.JPanel) {
                        for (java.awt.Component c3 : ((javax.swing.JPanel) c2).getComponents()) {
                            if (c3 instanceof JButton button) {
                                if ("View Listings".equals(button.getText())) {
                                    viewListingsButton = button;
                                }
                            }
                        }
                    }
                }
            }
        }
        assertNotNull(viewListingsButton);
        viewListingsButton.doClick();
        Thread.sleep(200);
        assertEquals("Listings", frame.getCurrentPanelName());
        frame.dispose();
        server.stopServer();
        serverThread.join(500);
    }
}
