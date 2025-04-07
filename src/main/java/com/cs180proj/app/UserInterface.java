package com.cs180proj.app;

import java.util.ArrayList;
/**
 * CS 18000 Group Project
 * Interface for User object which contains all
 * methods for each User object.
 *
 * @author (Rohan Kannan, Alistair Joseph, Lydia Schmucker, Stephen Tushentsov) lab sec 19
 *
 * @version April 4, 2025
 *
 */
public interface UserInterface {

    String getUsername();

    String getPassword();

    String getAddress();

    double getBalance();

    ArrayList<Listing> getListings();

    void setUsername(String username);

    void setPassword(String password);

    void setAddress(String address);

    void setBalance(double balance);

    void addListing(Listing newListing);

    void removeListing(Listing oldListing);

    void updateBalance(double price, boolean isBuyer);

    void setListings(ArrayList<Listing> listings);
}
