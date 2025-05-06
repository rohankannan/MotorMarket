package com.cs180proj.app;

import java.awt.*;
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
public class ListingsPanel extends JPanel implements ListingsPanelInterface {
    private final JPanel listingsContainer;
    private final JScrollPane scrollPane;
    private final NewClient client;
    private final JTextField searchField;
    private ArrayList<Listing> allListings = new ArrayList<>();
    private final MainFrame mf;

    /**
     * Constructs the ListingsPanel, which displays all available car listings.
     * Includes a search bar, scrollable listing area, and navigation back to the hub.
     *
     * @param mainFrame the main application frame for navigation and user context
     * @param client the client used for server communication to retrieve listings
     */


    public ListingsPanel(MainFrame mainFrame, NewClient client) {
        this.client = client;
        this.mf = mainFrame;
        setLayout(new BorderLayout());

        listingsContainer = new JPanel();
        listingsContainer.setLayout(new BoxLayout(listingsContainer, BoxLayout.Y_AXIS));

        scrollPane = new JScrollPane(listingsContainer);
        add(scrollPane, BorderLayout.CENTER);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> mf.showPanel("Hub"));
        add(backButton, BorderLayout.SOUTH);

        this.addAncestorListener(new AncestorListenerAdapter() {
            @Override
            public void ancestorAdded(javax.swing.event.AncestorEvent event) {
                new SwingWorker<Void, Void>() {
                    @Override
                    protected Void doInBackground() {
                        refreshListings();
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

    /**
     * Refreshes the listing display by fetching all listings from the server
     * and updating the listings container with the results.
     */

    public void refreshListings() {
        listingsContainer.removeAll();
        listingsContainer.add(new JLabel("Loading listings..."));
        listingsContainer.revalidate();
        listingsContainer.repaint();

        new SwingWorker<ArrayList<Listing>, Void>() {
            @Override
            protected ArrayList<Listing> doInBackground() {
                try {
                    Object response = client.sendCommand("GET_LISTINGS", mf.getCurrentUser().getUsername());
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

    /**
     * Displays the given list of listings by creating visual cards for each one.
     *
     * @param listings a list of listings to show in the panel
     */

    public void displayListings(ArrayList<Listing> listings) {
        listingsContainer.removeAll();
        for (Listing listing : listings) {
            listingsContainer.add(createListingCard(listing));
            listingsContainer.add(Box.createVerticalStrut(10));
        }
        listingsContainer.revalidate();
        listingsContainer.repaint();
    }

    /**
     * Creates a visual card UI component for a single listing, including image,
     * information, a message button, and optionally a buy button or sold label.
     *
     * @param listing the listing to represent visually
     * @return a JPanel representing the listing card
     */

    public JPanel createListingCard(Listing listing) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        card.setPreferredSize(new Dimension(750, 170));
        card.setMaximumSize(new Dimension(750, 170));
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
                SwingUtilities.invokeLater(() -> imageLabel.setText("No Image"));
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
                        "Transmission: " + (listing.isManual() ? "Manual" : "Automatic") + "\n" +
                        "ID: " + listing.getListingID()
        );

        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setOpaque(false);

        JButton messageButton = new JButton("Message Seller");
        messageButton.addActionListener(e -> {
            ChatPanel chatPanel = new ChatPanel(mf, client, listing.getSeller(), mf.getCurrentUser());
            mf.mainPanel.add(chatPanel, "Chat");
            mf.showPanel("Chat");
        });
        rightPanel.add(Box.createVerticalStrut(10));
        rightPanel.add(messageButton);

        if (!listing.getSeller().equals(mf.getCurrentUser().getUsername()) && !listing.isSold()) {
            JButton buyButton = new JButton("Buy Now");
            buyButton.setBackground(new Color(46, 204, 113));  // green
            buyButton.setForeground(Color.WHITE);
            buyButton.setOpaque(true);
            buyButton.setBorderPainted(false);
            buyButton.putClientProperty("JButton.buttonType", "segmented-only");
            buyButton.addActionListener(e -> {
                int result = JOptionPane.showConfirmDialog(
                        mf,
                        "Confirm purchase - " + listing.getCarType() + " - $" + String.format("%.2f",
                                listing.getPrice()) +
                                " will be deducted from your balance",
                        "Confirm Purchase",
                        JOptionPane.YES_NO_OPTION
                );

                if (result == JOptionPane.YES_OPTION) {
                    try {
                        Object res = client.sendCommand("BUY_LISTING", listing.getListingID(),
                                mf.getCurrentUser().getUsername());

                        if ("PURCHASE_SUCCESS".equals(res)) {
                            JOptionPane.showMessageDialog(mf, "Purchase successful!");

                            Object updated = client.sendCommand("LOGIN", mf.getCurrentUser().getUsername(),
                                    mf.getCurrentUser().getPassword());
                            if (updated instanceof User updatedUser) {
                                mf.setCurrentUser(updatedUser);
                            }

                            mf.refreshListingsPanel();
                        } else if ("INSUFFICIENT_FUNDS".equals(res)) {
                            JOptionPane.showMessageDialog(mf, "Insufficient funds.");
                        } else if ("LISTING_NOT_FOUND".equals(res)) {
                            JOptionPane.showMessageDialog(mf, "Listing no longer available.");
                        } else {
                            JOptionPane.showMessageDialog(mf, "Unknown error.");
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(mf, "Error during purchase: " + ex.getMessage());
                    }
                }
            });
            rightPanel.add(Box.createVerticalStrut(10));
            rightPanel.add(buyButton);
        }

        if (listing.isSold()) {
            JLabel soldLabel = new JLabel("SOLD");
            soldLabel.setForeground(Color.RED);
            soldLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
            soldLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            rightPanel.add(Box.createVerticalStrut(10));
            rightPanel.add(soldLabel);
        }

        rightPanel.add(Box.createVerticalGlue());

        card.add(imageLabel, BorderLayout.WEST);
        card.add(info, BorderLayout.CENTER);
        card.add(rightPanel, BorderLayout.EAST);

        return card;
    }

    /**
     * Filters the list of listings by a keyword and updates the display accordingly.
     * Searches within seller name, car type, color, mileage, price, and listing ID.
     *
     * @param keyword the search keyword used to filter listings
     */

    public void filterListings(String keyword) {
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
