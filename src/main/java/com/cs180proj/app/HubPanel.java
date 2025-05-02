package com.cs180proj.app;

import javax.swing.*;
import java.awt.*;

public class HubPanel extends JPanel {

    public HubPanel(MainFrame mainFrame, NewClient client) {
        setLayout(new BorderLayout());

        JLabel welcomeLabel = new JLabel("", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Helvetica", Font.BOLD, 24));
        add(welcomeLabel, BorderLayout.NORTH);

        JButton viewListingsButton = new JButton("Browse Listings");
        JButton addListingButton = new JButton("Post Listing");
        JButton logoutButton = new JButton("Logout");
        JButton editButton = new JButton("Edit My Listings");

        viewListingsButton.setPreferredSize(new Dimension(200, 40));
        addListingButton.setPreferredSize(new Dimension(200, 40));
        logoutButton.setPreferredSize(new Dimension(200, 40));
        editButton.setPreferredSize(new Dimension(200, 40));


        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(50, 0, 50, 0));
        buttonPanel.add(Box.createVerticalStrut(20));
        buttonPanel.add(centerAlign(viewListingsButton));
        buttonPanel.add(Box.createVerticalStrut(20));
        buttonPanel.add(centerAlign(addListingButton));
        buttonPanel.add(Box.createVerticalStrut(20));
        buttonPanel.add(centerAlign(editButton));
        buttonPanel.add(centerAlign(logoutButton));

        add(buttonPanel, BorderLayout.CENTER);

        viewListingsButton.addActionListener(e -> mainFrame.showPanel("Listings"));
        addListingButton.addActionListener(e -> mainFrame.showPanel("AddListing"));
        editButton.addActionListener(e -> mainFrame.showPanel("EditListings"));



        logoutButton.addActionListener(e -> {
            mainFrame.setCurrentUser(null);
            mainFrame.showPanel("Login");
        });

        this.addAncestorListener(new AncestorListenerAdapter() {
            @Override
            public void ancestorAdded(javax.swing.event.AncestorEvent event) {
                User user = mainFrame.getCurrentUser();
                if (user != null) {
                    welcomeLabel.setText("Welcome, " + user.getUsername() + "!");
                }
            }
        });
    }

    private JPanel centerAlign(JComponent comp) {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER));
        panel.add(comp);
        return panel;
    }
}
