package com.cs180proj.app;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
/**
 * CS 18000 Group Project
 *
 * ListingsPanel in GUI system which allows
 * user to view all avaliable listings
 *
 * @author (Rohan Kannan, Alistair Joseph,
 * Lydia Schmucker, Stephen Tushentsov) lab sec 19
 *
 * @version May 3, 2025
 */
public class ListingsPanel extends JPanel {
    private JPanel listingsContainer;
    private JScrollPane scrollPane;
    private NewClient client;
    private JTextField searchField;
    private ArrayList<Listing> allListings = new ArrayList<>();

    public ListingsPanel(MainFrame mainFrame, NewClient client) {
        this.client = client;

        setLayout(new BorderLayout());

        listingsContainer = new JPanel();
        listingsContainer.setLayout(new BoxLayout(listingsContainer, BoxLayout.Y_AXIS));

        scrollPane = new JScrollPane(listingsContainer);
        add(scrollPane, BorderLayout.CENTER);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> mainFrame.showPanel("Hub"));
        add(backButton, BorderLayout.SOUTH);

        this.addAncestorListener(new AncestorListenerAdapter() {
            @Override
            public void ancestorAdded(javax.swing.event.AncestorEvent event) {
                new SwingWorker<Void, Void>() {
                    @Override
                    protected Void doInBackground() {
                        refreshListings(mainFrame);
                        return null;
                    }
                }.execute();
            }
        });

        JPanel searchPanel = new JPanel(new BorderLayout());
        searchField = new JTextField("Search by keyword here");
        searchField.setForeground(Color.GRAY);

        searchField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (searchField.getText().equals("Search by keyword here")) {
                    searchField.setText("");
                    searchField.setForeground(Color.BLACK);
                }
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (searchField.getText().isEmpty()) {
                    searchField.setForeground(Color.GRAY);
                    searchField.setText("Search by keyword here");
                }
            }
        });

        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(e -> filterListings(searchField.getText()));

        searchPanel.add(searchField, BorderLayout.CENTER);
        searchPanel.add(searchButton, BorderLayout.EAST);
        add(searchPanel, BorderLayout.NORTH);
    }

    public void refreshListings(MainFrame mainFrame) {
        listingsContainer.removeAll();
        listingsContainer.add(new JLabel("Loading listings..."));
        listingsContainer.revalidate();
        listingsContainer.repaint();

        new SwingWorker<ArrayList<Listing>, Void>() {
            @Override
            protected ArrayList<Listing> doInBackground() {
                try {
                    Object response = client.sendCommand("GET_LISTINGS");
                    if (response instanceof ArrayList<?> rawList) {
                        ArrayList<Listing> result = new ArrayList<>();
                        for (Object o : rawList) {
                            if (o instanceof Listing l) result.add(l);
                        }
                        return result;
                    }
                } catch (Exception ignored) {}
                return new ArrayList<>();
            }

            @Override
            protected void done() {
                try {
                    allListings = get();
                    displayListings(allListings);
                } catch (Exception e) {
                    listingsContainer.removeAll();
                    listingsContainer.add(new JLabel("Error loading listings."));
                    listingsContainer.revalidate();
                    listingsContainer.repaint();
                }
            }
        }.execute();
    }

    private void displayListings(ArrayList<Listing> listings) {
        listingsContainer.removeAll();
        for (Listing listing : listings) {
            listingsContainer.add(createListingCard(listing, null));
            listingsContainer.add(Box.createVerticalStrut(10));
        }
        listingsContainer.revalidate();
        listingsContainer.repaint();
    }

    private JPanel createListingCard(Listing listing, MainFrame mainFrame) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        card.setPreferredSize(new Dimension(750, 150));
        card.setMaximumSize(new Dimension(750, 150));
        card.setBackground(Color.WHITE);

        JLabel imageLabel = new JLabel("Loading...");
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        imageLabel.setPreferredSize(new Dimension(280, 140));

        new Thread(() -> {
            try {
                ImageIcon icon = new ImageIcon(new URL(listing.getPhotoURL()));
                Image img = icon.getImage().getScaledInstance(280, 140, Image.SCALE_SMOOTH);
                SwingUtilities.invokeLater(() -> {
                    imageLabel.setText("");
                    imageLabel.setIcon(new ImageIcon(img));
                });
            } catch (Exception e) {
                SwingUtilities.invokeLater(() -> {
                    imageLabel.setText("No Image");
                });
            }
        }).start();

        JTextArea info = new JTextArea();
        info.setEditable(false);
        info.setBackground(Color.WHITE);
        info.setText(
                "Seller: " + listing.getSeller() + "\n" +
                        "Type: " + listing.getCarType() + "\n" +
                        "Color: " + listing.getColor() + "\n" +
                        "Mileage: " + listing.getMileage() + "\n" +
                        "Accidents: " + listing.getAccidents() + "\n" +
                        "Price: $" + String.format("%.2f", listing.getPrice()) + "\n" +
                        "Manual: " + listing.isManual() + "\n" +
                        "ID: " + listing.getListingID()
        );

        JButton messageButton = new JButton("Message Seller");
        messageButton.putClientProperty("JButton.buttonType", "segmented-only");
        messageButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this,
                    "Messaging feature coming soon for seller: " + listing.getSeller());
        });

        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.add(Box.createVerticalGlue());
        rightPanel.add(messageButton);
        rightPanel.add(Box.createVerticalGlue());

        card.add(imageLabel, BorderLayout.WEST);
        card.add(info, BorderLayout.CENTER);
        card.add(rightPanel, BorderLayout.EAST);

        return card;
    }


    private void filterListings(String keyword) {
        if (keyword == null || keyword.trim().isEmpty() || keyword.equals("Search by keyword here")) {
            displayListings(allListings);
            return;
        }

        String lowerKeyword = keyword.toLowerCase();
        ArrayList<Listing> filtered = new ArrayList<>();

        for (Listing l : allListings) {
            if (l.getSeller().toLowerCase().contains(lowerKeyword) ||
                    l.getCarType().toLowerCase().contains(lowerKeyword) ||
                    l.getColor().toLowerCase().contains(lowerKeyword) ||
                    String.valueOf(l.getMileage()).contains(lowerKeyword) ||
                    String.valueOf(l.getPrice()).contains(lowerKeyword) ||
                    l.getListingID().toLowerCase().contains(lowerKeyword)) {
                filtered.add(l);
            }
        }

        displayListings(filtered);
    }
}
