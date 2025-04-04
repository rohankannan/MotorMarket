package com.cs180proj.app;

/**
 * CS 18000 Group Project
 *
 * Insert Class Description Here
 *
 * @authors (Rohan Kannan, Alistair Joseph, Lydia Schmucker, Stephen Tushentsov) lab sec 19
 *
 * @version April 4, 2025
 *
 */
import java.util.ArrayList;

public class User implements UserInterface {
    // attributes

    private String username;              // stores the unique username of this user
    private String password;              // stores the password for this user
    private double balance;               // stores this user's current balance
    private ArrayList<Listing> listings;  // stores all the current listings of this user
    private String address;               // stores the user's address for pickup

    /**
     * constructor for new User objects 4 parameters for each of the 4
     * attributes of a User object in format: String, String, double, String
     * values: username, password, balance of money, address to meet
     */
    public User(String username, String password, double balance, String address) {
        this.username = username;
        this.password = password;
        this.balance = balance;
        this.address = address;
        listings = new ArrayList<>();
    }

    // getters / setters
    // getters
    /**
     * the getter/accessor methods for User objects returns the value of each
     * attribute of a User object respectively
     */
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public double getBalance() {
        return balance;
    }

    public ArrayList<Listing> getListings() {
        return listings;
    }

    public String getAddress() {
        return address;
    }

    /**
     * setter methods to update values of respective fields one parameter each,
     * of the new value to set each field as
     */
    /**
     * one parameter a String that represents the new/updated username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * one parameter a String that represents the new/updated password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * one parameter a String that represents the new/updated address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * one parameter a String that represents the new/updated balance
     */
    public void setBalance(double balance) {
        this.balance = balance;
    }

    /**
     * one parameter a Listing object that is to be added to the end of this
     * User's current existing arraylist of Listings
     */
    public void addListing(Listing newListing) {
        // add this new Listing to the end of arraylist
        listings.add(newListing);
    }

    /**
     * one parameter a Listing object that is to be removed from anywhere this
     * User's current existing arraylist of Listings without leaving a blank
     * slot or altering any of this User's other Listings
     */
    public void removeListing(Listing oldListing) {
        // look through this User's arraylist
        // if oldListing is found, remove
        if (listings.contains(oldListing)) {
            listings.remove(oldListing);
        }
    }

    /**
     * two parameters a double to represent the price of the listing being
     * bought or sold a boolean to represent if this User is buying or selling
     * the Listing (true: buyer, false: seller)
     */
    public void updateBalance(double price, boolean isBuyer) {
        // if this User is buying, update balance by removing price from current balance
        // if this buyer is selling, update balance by adding to current balance
        if (isBuyer) {
            balance -= price;
        } else {
            balance += price;
        }
    }

    //other methods
    /**
     * toString method returns a string representation of this User object
     */
    @Override
    public String toString() {
        String listingsString = "Listings:";
        // adds all listings to String
        for (Listing listing : listings) {
            listingsString += listing.toString() + ";";
        }
        return username + "," + password + "," + balance + "," + address + "," + listingsString;
    }

}
