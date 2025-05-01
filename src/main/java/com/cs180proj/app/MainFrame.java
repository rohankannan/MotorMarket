package com.cs180proj.app;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private NewClient client;
    private User currentUser;

    public MainFrame() {
        setTitle("Car Marketplace");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        try {
            client = new NewClient("localhost", 4242);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Could not connect to server: " + e.getMessage());
            System.exit(1);
        }

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        mainPanel.add(new LoginPanel(this, client), "Login");
        mainPanel.add(new RegistrationPanel(this, client), "Register");
        mainPanel.add(new HubPanel(this, client), "Hub");
        mainPanel.add(new ListingsPanel(this, client), "Listings");

        add(mainPanel);
        cardLayout.show(mainPanel, "Login");
        setVisible(true);
    }

    public void showPanel(String name) {
        cardLayout.show(mainPanel, name);
    }

    public void setCurrentUser(User user) {
        this.currentUser = user;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public NewClient getClient() {
        return client;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainFrame::new);
    }
}
