package com.cs180proj.app;

import java.util.ArrayList;
/**
 * CS 18000 Group Project
 * This class contains all methods and 
 * attributes of a User object. Each User
 * has an individual set of listings.
 *
 * @author (Rohan Kannan, Alistair Joseph, Lydia Schmucker, Stephen Tushentsov) lab sec 19
 *
 * @version April 4, 2025
 *
 */

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
     * @param username the unique username of this user
     * @param password the password for this user
     * @param balance   the initial balance of this user (how much money they have)
     * @param address   the address of this user for pickup
     */
    public User(String username, String password, double balance, String address) {
        this.username = username;
        this.password = password;
        this.balance = balance;
        this.address = address;
        listings = new ArrayList<>();
    }


    /**
     * getter method for the username of this User
     * @return the username of this User
     */
    public String getUsername() {
        return username;
    }

    /**
     * getter method for the password of this User
     * @return the password of this User
     */
    public String getPassword() {
        return password;
    }

    /**
     * getter method for the balance of this User
     * @return the current balance of this User
     */
    public double getBalance() {
        return balance;
    }

    /**
     * getter method for the listings of this User
     * @return the arraylist of Listings associated with this User
     */
    public ArrayList<Listing> getListings() {
        return listings;
    }

    /**
     * getter method for the address of this User
     * @return the address of this User
     */
    public String getAddress() {
        return address;
    }


    /**
     * one parameter a String that represents the new/updated username
     * @param username the new username to set for this User
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * one parameter a String that represents the new/updated password
     * @param password the new password to set for this User
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * one parameter a String that represents the new/updated address
     * @param address the new address to set for this User
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * one parameter a String that represents the new/updated balance
     * @param balance the new balance to set for this User
     */
    public void setBalance(double balance) {
        this.balance = balance;
    }

    /**
     * one parameter a Listing object that is to be added to the end of this
     * User's current existing arraylist of Listings
     * @param newListing the Listing object to be added to this User's listings
     */
    public void addListing(Listing newListing) {
        // add this new Listing to the end of arraylist
        listings.add(newListing);
    }

    /**
     * one parameter a Listing object that is to be removed from anywhere this
     * User's current existing arraylist of Listings without leaving a blank
     * slot or altering any of this User's other Listings
     * @param oldListing the Listing object to be removed from this User's listings
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
     * @param price the price of the listing being bought or sold
     * @param isBuyer a boolean to represent if this User is buying (true) or selling (false)
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

    /**
     * setter method to set the listings arraylist directly
     * @param listings the new arraylist of listings to set
     */
    @Override
    public void setListings(ArrayList<Listing> listings) {
        // setter method to set the listings arraylist directly
        this.listings = listings;
    }

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
