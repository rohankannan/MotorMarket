package com.cs180proj.app;

import javax.swing.*;
import java.awt.*;
import java.util.UUID;


public class AddListingPanel extends JPanel {
    public AddListingPanel(MainFrame mainFrame, NewClient client) {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JTextField urlField = new JTextField(15);
        JTextField typeField = new JTextField(15);
        JTextField colorField = new JTextField(15);
        JTextField mileageField = new JTextField(15);
        JTextField accidentsField = new JTextField(15);
        JTextField priceField = new JTextField(15);
        JCheckBox manualBox = new JCheckBox("Manual Transmission");
        String generatedID = UUID.randomUUID().toString().substring(0, 8);
        JLabel idLabel = new JLabel(generatedID);
        JButton submitButton = new JButton("Submit Listing");
        JButton backButton = new JButton("Back");

        int y = 0;
        addField("Photo URL:", urlField, gbc, y++);
        addField("Car Type:", typeField, gbc, y++);
        addField("Color:", colorField, gbc, y++);
        addField("Mileage:", mileageField, gbc, y++);
        addField("Accidents:", accidentsField, gbc, y++);
        addField("Price:", priceField, gbc, y++);
        gbc.gridx = 0; gbc.gridy = y; gbc.gridwidth = 2; add(manualBox, gbc); y++;
        gbc.gridx = 0; gbc.gridy = y; gbc.gridwidth = 1;
        add(new JLabel("Listing ID:"), gbc);
        gbc.gridx = 1;
        add(idLabel, gbc);
        y++;

        gbc.gridy = y;
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(submitButton);
        buttonPanel.add(backButton);
        add(buttonPanel, gbc);

        submitButton.addActionListener(e -> {
            try {
                Listing listing = new Listing(
                        mainFrame.getCurrentUser().getUsername(),
                        urlField.getText(),
                        typeField.getText(),
                        colorField.getText(),
                        Integer.parseInt(mileageField.getText()),
                        Integer.parseInt(accidentsField.getText()),
                        Double.parseDouble(priceField.getText()),
                        manualBox.isSelected(),
                        generatedID
                );

                Object response = client.sendCommand("ADD_LISTING", listing);
                JOptionPane.showMessageDialog(this, response.toString());
                mainFrame.showPanel("Hub");

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error creating listing: " + ex.getMessage());
            }
        });

        backButton.addActionListener(e -> mainFrame.showPanel("Hub"));
    }

    private void addField(String label, JTextField field, GridBagConstraints gbc, int row) {
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0; gbc.gridy = row; gbc.gridwidth = 1;
        add(new JLabel(label), gbc);
        gbc.gridx = 1;
        add(field, gbc);
    }
}
