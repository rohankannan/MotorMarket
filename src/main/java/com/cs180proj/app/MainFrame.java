package com.cs180proj.app;

import java.awt.*;

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
    private final CardLayout cardLayout;
    public JPanel mainPanel;
    private NewClient client;
    private User currentUser;
    private String currentPanelName;

    /**
     * Constructs the MainFrame which acts as the primary window for the application.
     * Initializes all panels and connects to the server using NewClient.
     */

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

        add(mainPanel);
        cardLayout.show(mainPanel, "Login");
        setVisible(true);
    }

    /**
     * Switches the currently visible panel to the one associated with the given name.
     *
     * @param name the name of the panel to display
     */

    public void showPanel(String name) {
        cardLayout.show(mainPanel, name);
        currentPanelName = name;
    }

    /**
     * Sets the currently logged-in user for session tracking.
     *
     * @param user the User object representing the current user
     */

    public void setCurrentUser(User user) {
        this.currentUser = user;
    }

    /**
     * Retrieves the currently logged-in user.
     *
     * @return the current user
     */

    public User getCurrentUser() {
        return currentUser;
    }

    /**
     * Gets the instance of NewClient used for server communication.
     *
     * @return the NewClient instance
     */

    public NewClient getClient() {
        return client;
    }

    /**
     * Retrieves the name of the currently displayed panel.
     *
     * @return the current panel name
     */

    public String getCurrentPanelName() {
        return currentPanelName;
    }

    /**
     * Returns a panel by name from the main panel container, if found.
     *
     * @param name the name of the panel
     * @return the JPanel with the matching name, or null if not found
     */

    public JPanel getPanel(String name) {
        for (Component comp : mainPanel.getComponents()) {
            if (comp instanceof JPanel panel && name.equals(panel.getName())) {
                return panel;
            }
        }
        return null;
    }

    /**
     * Refreshes the ListingsPanel by replacing it with a new instance.
     * Then switches the view to the Listings panel.
     */

    public void refreshListingsPanel() {
        ListingsPanel newPanel = new ListingsPanel(this, client);
        mainPanel.add(newPanel, "Listings");
        showPanel("Listings");
    }

    /**
     * Refreshes the EditListingsPanel by removing and re-adding it.
     * Then switches the view to the EditListings panel.
     */

    public void refreshEditListingsPanel() {
        mainPanel.remove(getPanel("EditListings"));
        EditListingsPanel refreshed = new EditListingsPanel(this, client);
        refreshed.setName("EditListings");
        mainPanel.add(refreshed, "EditListings");
        showPanel("EditListings");
    }

    /**
     * Main method that launches the app by creating MainFrame.
     *
     * @param args command-line args
     */

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainFrame::new);
    }
}
