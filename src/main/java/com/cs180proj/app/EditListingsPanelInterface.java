// imports
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
/**
 * CS 18000 Group Project
 *
 * Interface for EditListingsPanel class which contains all
 * methods and fields.
 *
 * @author (Rohan Kannan, Alistair Joseph,
 * Lydia Schmucker, Stephen Tushentsov) lab sec 19
 *
 * @version May 2, 2025
 */

 public interface EditListingsPanelInterface {

    JPanel createEditableCard(Listing listing, MainFrame mainFrame);
    void openEditing(Listing listing, MainFrame mainFrame);
    
 }
