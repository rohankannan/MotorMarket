package com.cs180proj.app;
public interface DatabaseInterface {
    String userfile = "userdata.txt";
    String listingfile = "listingdata.txt";

    void writeUserData(); // method to write user data to file
    void writeListingData(); // method to write listing data to file
    User[] readUserData(); // method to read user data from file and return array of User objects
    Listing[] readListingData(); // method to read listing data from file and return array of Listing objects
}
