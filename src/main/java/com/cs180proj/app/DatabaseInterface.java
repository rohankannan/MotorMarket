package com.cs180proj.app;

import java.util.ArrayList;

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
public interface DatabaseInterface {

    String USER_FILE = "data/Users.txt";
    String listingfile = "data/Listings.txt";

    void writeUserData(User user); // method to write user data to file
    void writeUserData(User user, String filePath); // overload method to write user data to a specified file path

    void writeListingData(Listing listing); // method to write listing data to file

    ArrayList<User> readUserData(); // method to read user data from file and return array of User objects

    ArrayList<Listing> readListingData(); // method to read listing data from file and return array of Listing objects
}
