package com.cs180proj.app;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
/**
 * CS 18000 Group Project
 *
 * MainFrame in GUI system serves as the
 * frame which all panels go onto.
 *
 * @author (Rohan Kannan, Alistair Joseph,
 * Lydia Schmucker, Stephen Tushentsov) lab sec 19
 *
 * @version May 3, 2025
 */
public class MainFrame extends JFrame implements MainFrameInterface {
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private NewClient client;
    private User currentUser;
    private String currentPanelName;

    public MainFrame() {
        setTitle("MotorMarket");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        try {
            client = new NewClient("localhost", 4242);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Could not connect to server: "
                    + e.getMessage());
            System.exit(1);
        }

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        mainPanel.add(new LoginPanel(this, client), "Login");
        mainPanel.add(new RegistrationPanel(this, client), "Register");
        mainPanel.add(new HubPanel(this, client), "Hub");
        mainPanel.add(new ListingsPanel(this, client), "Listings");
        mainPanel.add(new AddListingPanel(this, client), "AddListing");
        mainPanel.add(new EditListingsPanel(this, client), "EditListings");
        mainPanel.add(new ChatPanel(this, client), "Chat");

        add(mainPanel);
        cardLayout.show(mainPanel, "Login");
        setVisible(true);
    }

    public void showPanel(String name) {
        cardLayout.show(mainPanel, name);
        currentPanelName = name;
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

    public String getCurrentPanelName() {
        return currentPanelName;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainFrame::new);
    }
}
