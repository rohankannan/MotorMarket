package com.cs180proj.app;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
/**
 * CS 18000 Group Project
 *
 * RegistrationPanel in GUI system allows for
 * users to create a new account and add it to
 * the collective user database file.
 *
 * @author (Rohan Kannan, Alistair Joseph,
 * Lydia Schmucker, Stephen Tushentsov) lab sec 19
 *
 * @version May 3, 2025
 */

public class RegistrationPanel extends JPanel {

    /**
     * Constructs the Registration, which allows a user to create a new account
     *
     * @param mainFrame the main application frame for navigation and user context
     * @param client the client used for server communication to retrieve listings
     */

    public RegistrationPanel(MainFrame mainFrame, NewClient client) {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JTextField usernameField = new JTextField(15);
        JTextField passwordField = new JTextField(15);
        JTextField balanceField = new JTextField(15);
        JTextField addressField = new JTextField(15);
        JButton createButton = new JButton("Create");
        JButton backButton = new JButton("Back");

        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0; gbc.gridy = 0; add(new JLabel("Username:"), gbc);
        gbc.gridx = 1; add(usernameField, gbc);
        gbc.gridx = 0; gbc.gridy = 1; add(new JLabel("Password:"), gbc);
        gbc.gridx = 1; add(passwordField, gbc);
        gbc.gridx = 0; gbc.gridy = 2; add(new JLabel("Balance:"), gbc);
        gbc.gridx = 1; add(balanceField, gbc);
        gbc.gridx = 0; gbc.gridy = 3; add(new JLabel("Address:"), gbc);
        gbc.gridx = 1; add(addressField, gbc);
        gbc.gridx = 0; gbc.gridy = 4; add(createButton, gbc);
        gbc.gridx = 1; add(backButton, gbc);

        createButton.addActionListener(e -> {
            try {
                String username = usernameField.getText();
                String password = passwordField.getText();
                double balance = Double.parseDouble(balanceField.getText());
                String address = addressField.getText();

                User newUser = new User(username, password, balance, address);
                Object response = client.sendCommand("ADD_USER", newUser);
                JOptionPane.showMessageDialog(this, response.toString());
                mainFrame.showPanel("Login");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        });

        backButton.addActionListener(e -> mainFrame.showPanel("Login"));
    }
}
