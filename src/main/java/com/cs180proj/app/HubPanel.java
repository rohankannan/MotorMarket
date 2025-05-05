package com.cs180proj.app;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.*;

/**
 * CS 18000 Group Project
 *
 * HubPanel in GUI system which acts as
 * an intermediary panel to access other
 * subpanels in the system.
 *
 * @author (Rohan Kannan, Alistair Joseph,
 * Lydia Schmucker, Stephen Tushentsov) lab sec 19
 *
 * @version May 3, 2025
 */
public class HubPanel extends JPanel implements HubPanelInterface {
    private NewClient client;
    private JLabel welcomeLabel;

    public HubPanel(MainFrame mainFrame, NewClient client) {
        this.client = client;
        setLayout(new BorderLayout());

        welcomeLabel = new JLabel("Welcome to MotorMarket", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("SansSerif", Font.BOLD, 22));
        welcomeLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        add(welcomeLabel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new GridLayout(1, 2));

        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 10));

        JLabel featuredTitle = new JLabel("Featured Listing:");
        featuredTitle.setFont(new Font("SansSerif", Font.BOLD, 18));
        featuredTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel featuredImageLabel = new JLabel();
        featuredImageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        featuredImageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        featuredImageLabel.setPreferredSize(new Dimension(380, 250));

        JLabel captionLabel = new JLabel("", SwingConstants.CENTER);
        captionLabel.setFont(new Font("SansSerif", Font.ITALIC, 16));
        captionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        captionLabel.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));

        leftPanel.add(featuredTitle);
        leftPanel.add(Box.createVerticalStrut(10));
        leftPanel.add(featuredImageLabel);
        leftPanel.add(captionLabel);
        centerPanel.add(leftPanel);

        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setBorder(BorderFactory.createEmptyBorder(50, 20, 50, 20));

        JButton viewListingsButton = new JButton("View Listings");
        JButton addListingButton = new JButton("Add Listing");
        JButton editListingsButton = new JButton("Edit My Listings");
        JButton chatButton = new JButton("My Chats");
        JButton profileButton = new JButton("My Profile");
        JButton logoutButton = new JButton("Logout");


        Dimension btnSize = new Dimension(200, 40);
        for (JButton btn : new JButton[]{viewListingsButton, addListingButton, editListingsButton, chatButton, profileButton, logoutButton}) {
            btn.setAlignmentX(Component.CENTER_ALIGNMENT);
            btn.setMaximumSize(btnSize);
            btn.setPreferredSize(btnSize);
            rightPanel.add(Box.createVerticalStrut(15));
            rightPanel.add(btn);
        }

        centerPanel.add(rightPanel);
        add(centerPanel, BorderLayout.CENTER);

        chatButton.addActionListener(e -> {
                    mainFrame.mainPanel.add(new MessagingPanel(mainFrame, client), "Messaging");
                    mainFrame.showPanel("Messaging");

                }
                );
        viewListingsButton.addActionListener(e -> mainFrame.showPanel("Listings"));
        addListingButton.addActionListener(e -> mainFrame.showPanel("AddListing"));
        editListingsButton.addActionListener(e -> mainFrame.showPanel("EditListings"));
        profileButton.addActionListener(e -> showProfileDialog(mainFrame.getCurrentUser(), client));
        logoutButton.addActionListener(e -> {
            mainFrame.setCurrentUser(null);
            mainFrame.showPanel("Login");
        });

        this.addAncestorListener(new AncestorListenerAdapter() {
            @Override
            public void ancestorAdded(javax.swing.event.AncestorEvent event) {
                User user = mainFrame.getCurrentUser();
                welcomeLabel.setText("Welcome to MotorMarket, " + (user != null ? user.getUsername() : ""));

                try {
                    Object response = client.sendCommand("GET_LISTINGS", user.getUsername());
                    if (response instanceof ArrayList<?> listings && !listings.isEmpty()) {
                        Listing random = (Listing) listings.get(new Random().nextInt(listings.size()));
                        try {
                            ImageIcon icon = new ImageIcon(new URL(random.getPhotoURL()));
                            Image img = icon.getImage().getScaledInstance(380, 250, Image.SCALE_SMOOTH);
                            featuredImageLabel.setIcon(new ImageIcon(img));
                            featuredImageLabel.setText("");
                        } catch (Exception ex) {
                            featuredImageLabel.setIcon(null);
                            featuredImageLabel.setText("No Image Available");
                        }
                        captionLabel.setText("<html><i>" + random.getCarType() + " - " + random.getColor() + "</i></html>");
                    }
                } catch (Exception e) {
                    captionLabel.setText("No listings available.");
                }
            }
        });
    }

    private void showProfileDialog(User user, NewClient client) {
        JTextField addressField = new JTextField(user.getAddress(), 20);
        JTextField balanceField = new JTextField(String.format("%.2f", user.getBalance()), 20);

        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Username: " + user.getUsername()));
        panel.add(new JLabel("Address:"));
        panel.add(addressField);
        panel.add(new JLabel("Balance:"));
        panel.add(balanceField);

        int result = JOptionPane.showConfirmDialog(
                this, panel, "My Profile", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            try {
                String newAddress = addressField.getText().trim();
                double newBalance = Double.parseDouble(balanceField.getText().trim());

                user.setAddress(newAddress);
                user.setBalance(newBalance);

                Object response = client.sendCommand("UPDATE_USER", user);
                JOptionPane.showMessageDialog(this, "Profile updated successfully.");
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Invalid balance format.");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error updating profile: " + e.getMessage());
            }
        }
    }
}
