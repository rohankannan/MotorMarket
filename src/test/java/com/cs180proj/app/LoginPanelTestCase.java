package com.cs180proj.app;

import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
/**
 * CS 18000 Group Project
 *
 * This class contains various methods (test cases) which test
 * the functionality of LoginPanel.
 *
 * @author (Rohan Kannan, Alistair Joseph, Lydia Schmucker, Stephen Tushentsov) lab sec 19
 *
 * @version May 2, 2025
 *
 */
public class LoginPanelTestCase {
    
    // Test case for LoginPanel class constructor and fields
    /**
     * Tests LoginPanel with valid username and password.
     * If user and password aren't in database, test fails.
     */
    @Test
    public void testLoginPanelValidInfo() throws Exception {
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
        NewClient client = frame.getClient();
        LoginPanel panel = new LoginPanel(frame, client);
        JTextField usernameField = null;
        JPasswordField passwordField = null;
        JButton loginButton = null;
        for (java.awt.Component c : panel.getComponents()) {
            if (c instanceof JPasswordField) {
                passwordField = (JPasswordField) c;
            } else if (c instanceof JTextField) {
                usernameField = (JTextField) c;
            } else if (c instanceof JButton b) {
                if ("Login".equals(b.getText())) {
                    loginButton = b;
                }
            }
        }
        assertNotNull(usernameField);
        assertNotNull(passwordField);
        assertNotNull(loginButton);
        usernameField.setText("sc30");
        passwordField.setText("3ball");
        loginButton.doClick();
        Thread.sleep(200);
        User loggedIn = frame.getCurrentUser();
        assertNotNull(loggedIn);
        frame.dispose();
        server.stopServer();
        serverThread.join(500);
    }
}
