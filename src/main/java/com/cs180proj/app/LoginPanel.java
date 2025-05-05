package com.cs180proj.app;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
/**
 * CS 18000 Group Project
 *
 * LoginPanel in GUI system which allows
 * user to log into their account or create
 * a new one if they don't have one
 *
 * @author (Rohan Kannan, Alistair Joseph,
 * Lydia Schmucker, Stephen Tushentsov) lab sec 19
 *
 * @version May 3, 2025
 */
public class LoginPanel extends JPanel {
    public LoginPanel(MainFrame mainFrame, NewClient client) {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JTextField usernameField = new JTextField(15);
        JPasswordField passwordField = new JPasswordField(15);
        JButton loginButton = new JButton("Login");
        JButton registerButton = new JButton("Create Account");

        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0; gbc.gridy = 0; add(new JLabel("Username:"), gbc);
        gbc.gridx = 1; add(usernameField, gbc);
        gbc.gridx = 0; gbc.gridy = 1; add(new JLabel("Password:"), gbc);
        gbc.gridx = 1; add(passwordField, gbc);
        gbc.gridx = 0; gbc.gridy = 2; add(loginButton, gbc);
        gbc.gridx = 1; add(registerButton, gbc);

        loginButton.addActionListener(e -> {
            String username = usernameField.getText().trim();
            String password = new String(passwordField.getPassword()).trim();

            try {
                Object response = client.sendCommand("LOGIN", username, password);

                if (response instanceof User user) {
                    mainFrame.setCurrentUser(user);
                    mainFrame.showPanel("Hub");
                } else if (response instanceof String str && str.equals("FAIL")) {
                    JOptionPane.showMessageDialog(this, "Invalid credentials.");
                } else {
                    JOptionPane.showMessageDialog(this, "Unexpected response from server: " + response);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Login error: " + ex.getMessage());
                ex.printStackTrace();
            }
        });

        registerButton.addActionListener(e -> mainFrame.showPanel("Register"));
    }
}
