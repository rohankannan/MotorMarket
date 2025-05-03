package com.cs180proj.app;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
/**
 * CS 18000 Group Project
 *
 * This class contains various methods (test cases) which test
 * the functionality of AddListingPanel.
 *
 * @author (Rohan Kannan, Alistair Joseph, Lydia Schmucker, Stephen Tushentsov) lab sec 19
 *
 * @version May 3, 2025
 *
 */
public class AddListingPanelTestCase {
    
    // Test case for AddListingPanel class constructor and fields
    /**
     * Tests AddListingPanel with valid username, password, balance, and address
     * along with valid listing information.
     * Writes to listings.txt and deletes newly added listing after test.
     * When running test, make sure to click ok on the pop-up window that appears.
     */
    @Test
    public void testAddListingPanelSubmit() throws Exception {
        Server server = new Server();
        Thread serverThread = new Thread(() -> {
            try {
                server.startServer(4242);
            } catch (Exception e) {
            }
        });
        serverThread.start();
        Thread.sleep(150);
        MainFrame f = new MainFrame();
        f.setVisible(false);
        User user = new User("sc30", "3ball", 30000.3, "30 Oakland Dr");
        f.setCurrentUser(user);
        NewClient client = f.getClient();
        AddListingPanel panel = new AddListingPanel(f, client);
        JTextField urlField = null;
        JTextField typeField = null;
        JTextField colorField = null;
        JTextField mileageField = null;
        JTextField accidentsField = null;
        JTextField priceField = null;
        JCheckBox manualBox = null;
        JButton submitButton = null, backButton = null;
        for (java.awt.Component c : panel.getComponents()) {
            if (c instanceof JTextField tf) {
                String label = ((JLabel)panel.getComponent(panel.getComponentZOrder(tf) - 1)).getText();
                switch (label) {
                    case "Photo URL:" -> urlField = tf;
                    case "Car Type:" -> typeField = tf;
                    case "Color:" -> colorField = tf;
                    case "Mileage:" -> mileageField = tf;
                    case "Accidents:" -> accidentsField = tf;
                    case "Price:" -> priceField = tf;
                }
            } else if (c instanceof JCheckBox cb) {
                manualBox = cb;
            } else if (c instanceof JPanel buttonPanel) {
                for (java.awt.Component button : buttonPanel.getComponents()) {
                    if (button instanceof JButton b) {
                        if (b.getText().contains("Submit")) 
                            submitButton = b;
                        if (b.getText().contains("Back")) 
                            backButton = b;
                    }
                }
            }
        }
        assertNotNull(urlField);
        assertNotNull(typeField);
        assertNotNull(colorField);
        assertNotNull(mileageField);
        assertNotNull(accidentsField);
        assertNotNull(priceField);
        assertNotNull(manualBox);
        assertNotNull(submitButton);
        assertNotNull(backButton);
        urlField.setText("http://test.com/car.jpg");
        typeField.setText("Sedan");
        colorField.setText("Red");
        mileageField.setText("12345");
        accidentsField.setText("0");
        priceField.setText("9999.99");
        manualBox.setSelected(true);
        submitButton.doClick();
        Thread.sleep(300);
        assertEquals("Hub", f.getCurrentPanelName());
        f.dispose();
        server.stopServer();
        serverThread.join(500);
        File listingsFile = new File("src/main/java/com/cs180proj/app/data/Listings.txt");
        ArrayList<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(listingsFile))) {
            String l = "";
            while ((l = br.readLine()) != null) {
                lines.add(l);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!lines.isEmpty()) {
            lines.remove(lines.size() - 1);
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(listingsFile, false))) {
            for (String line : lines) {
                bw.write(line);
                bw.newLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
