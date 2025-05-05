package com.cs180proj.app;

import java.io.IOException;
import java.util.ArrayList;

/**
 * CS 18000 Group Project
 *
 * Interface for Database class which contains all
 * methods and fields.
 *
 * @author (Rohan Kannan, Alistair Joseph, Lydia Schmucker, Stephen Tushentsov) lab sec 19
 *
 * @version April 4, 2025
 *
 */
public interface DatabaseInterface {

    String USER_FILE = "data/Users.txt";
    String LISTING_FILE = "data/Listings.txt";

    void writeUserData(User user); // method to write user data to file
    void writeUserData(User user, String filePath); // overload method to write user data to a specified file path

    void writeListingData(Listing listing) throws IOException; // method to write listing data to file
    void writeListingData(Listing listing, String filePath);

    ArrayList<User> readUserData(); // method to read user data from file and return array of User objects
    ArrayList<User> readUserData(String filePath); 
    // overload method to read user data from a specified file path and return array of User objects

    ArrayList<Listing> readListingData(); // method to read listing data from file and return array of Listing objects

}
