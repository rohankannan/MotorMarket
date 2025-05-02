package com.cs180proj.app;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.ArrayList;

public class EditListingsPanel extends JPanel {
    private NewClient client;

    public EditListingsPanel(MainFrame mainFrame, NewClient client) {
        this.client = client;
        setLayout(new BorderLayout());

        JPanel listingsContainer = new JPanel();
        listingsContainer.setLayout(new BoxLayout(listingsContainer, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(listingsContainer);
        add(scrollPane, BorderLayout.CENTER);

        JButton backButton = new JButton("Back to Hub");
        backButton.addActionListener(e -> mainFrame.showPanel("Hub"));
        add(backButton, BorderLayout.SOUTH);

        this.addAncestorListener(new AncestorListenerAdapter() {
            @Override
            public void ancestorAdded(javax.swing.event.AncestorEvent event) {
                listingsContainer.removeAll();
                try {
                    Object response = client.sendCommand("GET_LISTINGS");
                    if (response instanceof ArrayList<?> listings) {
                        for (Object obj : listings) {
                            if (obj instanceof Listing l &&
                                    l.getSeller().equals(mainFrame.getCurrentUser().getUsername())) {
                                listingsContainer.add(createEditableCard(l, mainFrame));
                                listingsContainer.add(Box.createVerticalStrut(10));
                            }
                        }
                    }
                } catch (Exception e) {
                    listingsContainer.add(new JLabel("Error loading listings: " + e.getMessage()));
                }
                listingsContainer.revalidate();
                listingsContainer.repaint();
            }
        });
    }

    private JPanel createEditableCard(Listing listing, MainFrame mainFrame) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        card.setPreferredSize(new Dimension(750, 150));
        card.setMaximumSize(new Dimension(750, 150));
        card.setBackground(Color.WHITE);

        JLabel imageLabel = new JLabel();
        try {
            ImageIcon icon = new ImageIcon(new URL(listing.getPhotoURL()));
            Image img = icon.getImage().getScaledInstance(180, 120, Image.SCALE_SMOOTH);
            imageLabel.setIcon(new ImageIcon(img));
        } catch (Exception e) {
            imageLabel.setText("No Image");
            imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
            imageLabel.setPreferredSize(new Dimension(180, 120));
        }

        JTextArea info = new JTextArea(
                "ID: " + listing.getListingID() + "\n" +
                        "Type: " + listing.getCarType() + "\n" +
                        "Color: " + listing.getColor() + "\n" +
                        "Price: $" + String.format("%.2f", listing.getPrice())
        );
        info.setEditable(false);
        info.setBackground(Color.WHITE);

        JButton editButton = new JButton("Edit");
        editButton.addActionListener(e -> openEditing(listing, mainFrame));

        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.add(info, BorderLayout.CENTER);
        rightPanel.add(editButton, BorderLayout.SOUTH);

        card.add(imageLabel, BorderLayout.WEST);
        card.add(rightPanel, BorderLayout.CENTER);
        return card;
    }

    private void openEditing(Listing listing, MainFrame mainFrame) {
        JTextField urlField = new JTextField(listing.getPhotoURL());
        JTextField typeField = new JTextField(listing.getCarType());
        JTextField colorField = new JTextField(listing.getColor());
        JTextField priceField = new JTextField(String.format("%.2f", listing.getPrice()));

        Object[] fields = {
                "Image URL:", urlField,
                "Car Type:", typeField,
                "Color:", colorField,
                "Price:", priceField
        };

        int result = JOptionPane.showConfirmDialog(this, fields,
                "Edit Listing: " + listing.getListingID(), JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            try {
                listing.setPhotoURL(urlField.getText());
                listing.setCarType(typeField.getText());
                listing.setColor(colorField.getText());
                listing.setPrice(Double.parseDouble(priceField.getText()));

                client.sendCommand("UPDATE_LISTING", listing);
                JOptionPane.showMessageDialog(this, "Listing updated.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error updating listing: " + ex.getMessage());
            }
        }
    }
}
