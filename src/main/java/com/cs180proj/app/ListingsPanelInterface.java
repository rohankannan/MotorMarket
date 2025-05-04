// imports 
package com.cs180proj.app;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.ArrayList;
/**
 * CS 18000 Group Project
 *
 * Interface for ListingsPanel class which contains all
 * methods and fields.
 *
 * @author (Rohan Kannan, Alistair Joseph,
 * Lydia Schmucker, Stephen Tushentsov) lab sec 19
 *
 * @version May 2, 2025
 */

public interface ListingsPanelInterface {

    void refreshListings();
    void displayListings(ArrayList<Listing> listings);
    JPanel createListingCard(Listing listing);
    void filterListings(String keyword);

}
