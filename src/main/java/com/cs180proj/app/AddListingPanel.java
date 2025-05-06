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

    private JTextField urlField = new JTextField(15); // text field to enter a photo URL
    private JTextField typeField = new JTextField(15); // text field to enter type of car
    private JTextField colorField = new JTextField(15); // text field to enter car's color
    private JTextField mileageField = new JTextField(15); // text field to enter car's mileage
    private JTextField accidentsField = new JTextField(15); // text field to enter the number of accidents 
    private JTextField priceField = new JTextField(15); // text field to enter the car's price
    private JCheckBox manualBox = new JCheckBox("Manual Transmission"); // checkbox to enter if the car is manual

    /**
    * Constructor for class AddListingPanel
    * @param mainFrame MainFrame object being used, 
    * @param client NewClient object that is adding the listing
    */
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

            } catch (NullPointerException npe) {
                System.out.println("Added Item.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error creating listing: " +
                        ex.getMessage());
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

    /**
    * A method to add a field, given constraints
    * @param label is the label for the JLabel, 
    * @param field is the JTextField added, 
    * @param gbc is the GridBagConstraints object for adding the field, 
    * @param row is an int that defines the gbc grid's y value
    * @return void
    */
    public void addField(String label, JTextField field, GridBagConstraints gbc, int row) {
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0; gbc.gridy = row; gbc.gridwidth = 1;
        add(new JLabel(label), gbc);
        gbc.gridx = 1;
        add(field, gbc);
    }

    /**
    * A method which resets the fields by setting the text of each to empty and the boolean to false
    * @return void
    */
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
