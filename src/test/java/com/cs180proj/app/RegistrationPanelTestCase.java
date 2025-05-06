package com.cs180proj.app;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JTextField;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;

/**
 * CS 18000 Group Project
 *
 * This class contains various methods (test cases) which test
 * the functionality of RegistrationPanel.
 *
 * @author (Rohan Kannan, Alistair Joseph, Lydia Schmucker, Stephen Tushentsov) lab sec 19
 *
 * @version May 2, 2025
 *
 */
public class RegistrationPanelTestCase {
    
    // Test case for RegistrationPanel class constructor and fields
    /**
     * Tests RegistrationPanel with valid username, password, balance, and address.
     * Writes to users.txt and deletes newly added user after test.
     * When running test, make sure to click ok on the pop-up window that appears.
     */
    @Test
    public void testRegistrationPanel() throws Exception {
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
        NewClient client = f.getClient();
        RegistrationPanel rp = new RegistrationPanel(f, client);
        JTextField usernameField = null;
        JTextField passwordField = null;
        JTextField balanceField = null;
        JTextField addressField = null;
        JButton createButton = null;
        for (java.awt.Component c : rp.getComponents()) {
            if (c instanceof JTextField textField) {
                if (usernameField == null) {
                    usernameField = textField;
                } else if (passwordField == null) {
                    passwordField = textField;
                } else if (balanceField == null) {
                    balanceField = textField;
                } else if (addressField == null) {
                    addressField = textField;
                }
            } else if (c instanceof JButton button) {
                if ("Create".equals(button.getText())) {
                    createButton = button;
                }
            }
        }
        assertNotNull(usernameField);
        assertNotNull(passwordField);
        assertNotNull(balanceField);
        assertNotNull(addressField);
        assertNotNull(createButton);
        usernameField.setText("testuser");
        passwordField.setText("testpass");
        balanceField.setText("100.0");
        addressField.setText("123 Main St");
        createButton.doClick();
        Thread.sleep(200);
        assertEquals("Login", f.getCurrentPanelName());
        f.dispose();
        server.stopServer();
        serverThread.join(500);
        File usersFile = new File("src/main/java/com/cs180proj/app/data/Users.txt");
        ArrayList<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(usersFile))) {
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
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(usersFile, false))) {
            for (String line : lines) {
                bw.write(line);
                bw.newLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
