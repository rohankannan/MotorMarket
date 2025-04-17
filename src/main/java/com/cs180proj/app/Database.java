package com.cs180proj.app;

import javax.xml.crypto.Data;
import java.io.*;
import java.util.*;

/**
 * CS 18000 Group Project
 *
 * This class holds methods and a constructor for the database object that will be used for the commerce platform using File I/O
 *
 * @author (Rohan Kannan, Alistair Joseph, Lydia Schmucker, Stephen Tushentsov) lab sec 19
 *
 * @version April 4, 2025
 *
 */
public class Database implements DatabaseInterface {


    private static final String USER_FILE = "src/main/java/com/cs180proj/app/data/Users.txt";
    private static final String LISTING_FILE = "src/main/java/com/cs180proj/app/data/Listings.txt";

    /**
     * Constructor for the Database class. This is used to initialize the database object.
     * @param user the user object to initialize the database with
     */
    @Override
    public void writeUserData(User user) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(USER_FILE, true))) {
            writer.write(user.toString());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Overloaded method to write user data to a specified file path
     * @param user the user object to write to file
     * @param filePath the path of the file to write to
     */
    @Override
    public void writeUserData(User user, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(user.toString());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * This method is used to write listing data to the default file path
     * @param listing the listing object to write to file
     */
    @Override
    public void writeListingData(Listing listing) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LISTING_FILE, true))) {
            writer.write(listing.toString());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Overloaded method to write listing data to a specified file path
     * @param listing the listing object to write to file
     * @param filePath the path of the file to write to
     */
    @Override
    public void writeListingData(Listing listing, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(listing.toString());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method reads user data from the default file path and returns an ArrayList of User objects
     * @return ArrayList<User> a list of users read from the file
     */
    @Override
    public ArrayList<User> readUserData() {
        ArrayList<User> users = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(USER_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 5) {
                    users.add(new User(parts[0], parts[1], Double.parseDouble(parts[2]), parts[3]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return users;
    }

    /**
     * Overloaded method to read user data from a specified file path and return an ArrayList of User objects
     * @param filePath the path of the file to read from
     * @return ArrayList<User> a list of users read from the specified file
     */
    @Override
    public ArrayList<User> readUserData(String filePath) {
        ArrayList<User> users = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 5) {
                    users.add(new User(parts[0], parts[1], Double.parseDouble(parts[2]), parts[3]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return users;
    }

    /**
     * This method reads listing data from the default file path and returns an ArrayList of Listing objects
     * @return ArrayList<Listing> a list of listings read from the file
     */
    @Override
    public ArrayList<Listing> readListingData() {
        ArrayList<Listing> listings = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(LISTING_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 8) {
                    listings.add(new Listing(parts[0], parts[1], parts[2], parts[3], Integer.parseInt(parts[4]),
                            Integer.parseInt(parts[5]), Double.parseDouble(parts[6]), Boolean.parseBoolean(parts[7]),
                            parts[8]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listings;
    }

    /**
     * Overloaded method to read listing data from a specified file path and return an ArrayList of Listing objects
     * @param filePath the path of the file to read from
     * @return ArrayList<Listing> a list of listings read from the specified file
     */
    @Override
    public ArrayList<Listing> readListingData(String filePath) {
        ArrayList<Listing> listings = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 8) {
                    listings.add(new Listing(parts[0], parts[1], parts[2], parts[3], Integer.parseInt(parts[4]),
                            Integer.parseInt(parts[5]), Double.parseDouble(parts[6]), Boolean.parseBoolean(parts[7]),
                            parts[8]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listings;
    }

    public static void main(String[] args) {
        User u1 = new User("ab", "1234", 0.29, "217 abc ct");
        Database db = new Database();
        db.writeUserData(u1);
    }
}
