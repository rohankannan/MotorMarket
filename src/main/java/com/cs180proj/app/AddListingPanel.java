package com.cs180proj.app;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.UUID;

import javax.swing.*;

/**
 * CS 18000 Group Project
 *
 * AddListingPanel in GUI system which allows
 * user to add listing
 *
 * @author (Rohan Kannan, Alistair Joseph,
 * Lydia Schmucker, Stephen Tushentsov) lab sec 19
 *
 * @version May 3, 2025
 */
public class AddListingPanel extends JPanel implements AddListingPanelInterface {

    private JTextField urlField = new JTextField(15);
    private JTextField typeField = new JTextField(15);
    private JTextField colorField = new JTextField(15);
    private JTextField mileageField = new JTextField(15);
    private JTextField accidentsField = new JTextField(15);
    private JTextField priceField = new JTextField(15);
    private JCheckBox manualBox = new JCheckBox("Manual Transmission");

    public AddListingPanel(MainFrame mainFrame, NewClient client) {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        urlField = new JTextField(15);
        typeField = new JTextField(15);
        colorField = new JTextField(15);
        mileageField = new JTextField(15);
        accidentsField = new JTextField(15);
        priceField = new JTextField(15);
        manualBox = new JCheckBox("Manual Transmission");
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
                mainFrame.refreshEditListingsPanel();
                SwingUtilities.invokeLater(() -> mainFrame.showPanel("Hub"));

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error creating listing: " + ex.getMessage());
            }
        });

        backButton.addActionListener(e -> mainFrame.showPanel("Hub"));

        this.addAncestorListener(new AncestorListenerAdapter() {
            @Override
            public void ancestorAdded(javax.swing.event.AncestorEvent event) {
                resetFields();
            }
        });
    }

    public void addField(String label, JTextField field, GridBagConstraints gbc, int row) {
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0; gbc.gridy = row; gbc.gridwidth = 1;
        add(new JLabel(label), gbc);
        gbc.gridx = 1;
        add(field, gbc);
    }

    public void resetFields() {
        urlField.setText("");
        typeField.setText("");
        colorField.setText("");
        mileageField.setText("");
        accidentsField.setText("");
        priceField.setText("");
        manualBox.setSelected(false);
    }
}
