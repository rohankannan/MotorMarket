// imports
package com.cs180proj.app;

import javax.swing.*;
import java.awt.*;
import java.util.UUID;

/**
 * CS 18000 Group Project
 *
 * Interface for Client class which contains all
 * methods and fields.
 *
 * @author (Rohan Kannan, Alistair Joseph,
 * Lydia Schmucker, Stephen Tushentsov) lab sec 19
 *
 * @version May 2, 2025
 */

 public interface AddListingPanelInterface {
    
    // methods
    void addField(String label, JTextField field, GridBagConstraints gbc, int row);

 }
