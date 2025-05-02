// imports
package com.cs180proj.app;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
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

 public interface MainFrameInterface {

    void showPanel(String name);
    void setCurrentUser(User user);
    User getCurrentUser();
    NewClient getClient();
    String getCurrentPanelName();
    
 }
