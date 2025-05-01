package com.cs180proj.app;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ListingsPanel extends JPanel {
    private JTextArea listingsArea;
    private NewClient client;

    public ListingsPanel(MainFrame mainFrame, NewClient client) {
        this.client = client;
        setLayout(new BorderLayout());

        listingsArea = new JTextArea();
        listingsArea.setEditable(false);
        JScrollPane scroll = new JScrollPane(listingsArea);
        add(scroll, BorderLayout.CENTER);

        JButton backButton = new JButton("Back");
        add(backButton, BorderLayout.SOUTH);

        backButton.addActionListener(e -> mainFrame.showPanel("Hub"));

        // 🔽 Add the AncestorListener HERE (at the end of constructor):
        this.addAncestorListener(new AncestorListenerAdapter() {
            @Override
            public void ancestorAdded(javax.swing.event.AncestorEvent event) {
                refreshListings();
            }
        });
    }

    public void refreshListings() {
        listingsArea.setText("");
        try {
            Object response = client.sendCommand("GET_LISTINGS");
            if (response instanceof ArrayList<?> listings) {
                for (Object obj : listings) {
                    listingsArea.append(obj.toString() + "\n\n");
                }
            }
        } catch (Exception e) {
            listingsArea.setText("Error fetching listings: " + e.getMessage());
        }
    }
}
