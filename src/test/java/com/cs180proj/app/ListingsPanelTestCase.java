package com.cs180proj.app;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
/**
 * CS 18000 Group Project
 *
 * This class contains various methods (test cases) which test
 * the functionality of ListingsPanel.
 *
 * @author (Rohan Kannan, Alistair Joseph, Lydia Schmucker, Stephen Tushentsov) lab sec 19
 *
 * @version May 2, 2025
 *
 */
public class ListingsPanelTestCase {
    
    /**  
    * Test cases for all helper methods, constructor, and fields of ListingsPanel class.
    * All test cases test the constructor and fields of ListingsPanel class.
    */
    // Test case for ListingsPanel class constructor, fields, and helper methods
    @Test
    public void testHelpers() throws Exception {
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
        MainFrame f = new MainFrame();
        f.setVisible(false);
        ListingsPanel lp = new ListingsPanel(f, f.getClient());
        JButton backButton = null;
        JButton searchButton = null;
        JTextField searchField = null;
        JScrollPane scrollPane = null;
        for (java.awt.Component c : lp.getComponents()) {
            if (c instanceof JButton) {
                JButton btn = (JButton) c;
                if ("Back".equals(btn.getText())) {
                    backButton = btn;
                } else if ("Search".equals(btn.getText())) {
                    searchButton = btn;
                }
            } else if (c instanceof JScrollPane) {
                scrollPane = (JScrollPane) c;
            } else if (c instanceof JPanel) {
                JPanel p = (JPanel) c;
                for (java.awt.Component c2 : p.getComponents()) {
                    if (c2 instanceof JButton) {
                        JButton button = (JButton) c2;
                        if ("Back".equals(button.getText())) {
                            backButton = button;
                        } else if ("Search".equals(button.getText())) {
                            searchButton = button;
                        }
                    } else if (c2 instanceof JTextField) {
                        searchField = (JTextField) c2;
                    } else if (c2 instanceof JScrollPane) {
                        scrollPane = (JScrollPane) c2;
                    }
                }
            }
        }
        assertNotNull(backButton);
        assertNotNull(searchButton);
        assertNotNull(searchField);
        assertNotNull(scrollPane);
        searchField.setText("sc30");
        searchButton.doClick();
        Thread.sleep(200);
        backButton.doClick();
        Thread.sleep(200);
        assertEquals("Hub", f.getCurrentPanelName());
        f.dispose();
        server.stopServer();
        serverThread.join(500);
    }

    // Test case for refreshListings() method, assumes there is always at least one listing in the database
    @Test
    public void testRefreshListings() throws Exception {
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
        MainFrame f = new MainFrame();
        f.setVisible(false);
        ListingsPanel lp = new ListingsPanel(f, f.getClient());
        lp.refreshListings();
        java.lang.reflect.Field cf = ListingsPanel.class.getDeclaredField("listingsContainer");
        cf.setAccessible(true);
        JPanel listingsContainer = (JPanel) cf.get(lp);
        assertNotNull(listingsContainer);
        f.dispose();
        server.stopServer();
        serverThread.join(500);
    }
}
