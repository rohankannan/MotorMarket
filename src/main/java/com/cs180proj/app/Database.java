package com.cs180proj.app;

import java.io.*;
import java.util.*;

/**
 * CS 18000 Group Project
 *
 * This class runs the database for the commerce platform using File I/O
 *
 * @authors (Rohan Kannan, Alistair Joseph, Lydia Schmucker, Stephen Tushentsov) lab sec 19
 *
 * @version April 4, 2025
 *
 */
public class Database implements DatabaseInterface {

    private static final String USER_FILE = "Users.txt";
    private static final String LISTING_FILE = "Listings.txt";

    @Override
    public void writeUserData(User user) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(USER_FILE, true))) {
            writer.write(user.toString());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void writeListingData(Listing listing) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LISTING_FILE, true))) {
            writer.write(listing.toString());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<User> readUserData() {
        ArrayList<User> users = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(USER_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 5) {
                    users.add(new User(parts[0], parts[1], Double.parseDouble(parts[2]), parts[4]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return users;
    }

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
}
