package com.cs180proj.app;

import java.util.ArrayList;

public interface UserInterface {

    // getters
    String getUsername();

    String getPassword();

    String getAddress();

    double getBalance();

    ArrayList<Listing> getListings();

    // setters
    void setUsername(String username);

    void setPassword(String password);

    void setAddress(String address);

    void setBalance(double balance);

    // update arraylist by adding a new listing 
    void addListing(Listing newListing);

    // remove a listing from the arraylist of listings
    void removeListing(Listing oldListing);

    // updates the balance based on a purchase
    // parameters are the price of the listing and if it is buying or selling
    // true for a buyer, false for a seller
    void updateBalance(double price, boolean isBuyer);

}
