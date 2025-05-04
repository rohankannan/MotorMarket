package com.cs180proj.app;

import java.io.*;
import java.util.*;

/**
 * CS 18000 Group Project
 *
 * This class holds methods and a constructor for the database object that
 * will be used for the commerce platform using File I/O
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
    public void writeListingData(Listing listing) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LISTING_FILE, true))) {
            writer.write(serializeListing(listing));
            writer.newLine();
            writer.flush();
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
                String[] parts = line.trim().split(",");
                if (parts.length >= 9) {
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
                String[] parts = line.trim().split(",");
                if (parts.length >= 9) {
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

    public void overwriteListingData(ArrayList<Listing> listings) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LISTING_FILE, false))) {
            for (Listing l : listings) {
                writer.write(l.toString());
                writer.newLine();
            }
        }
    }

    public void updateListing(Listing updated) throws IOException {
        ArrayList<Listing> all = readListingData();
        for (int i = 0; i < all.size(); i++) {
            if (all.get(i).getListingID().equals(updated.getListingID())) {
                all.set(i, updated);
                break;
            }
        }
        overwriteListingData(all);
    }

    private String serializeListing(Listing l) {
        return String.join(",",
                l.getSeller(),
                l.getPhotoURL(),
                l.getCarType(),
                l.getColor(),
                String.valueOf(l.getMileage()),
                String.valueOf(l.getAccidents()),
                String.valueOf(l.getPrice()),
                String.valueOf(l.isManual()),
                l.getListingID()
        );
    }

    public void writeChatData(Chat c, String filePath)
    {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(c.toString());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Chat> readChatData(String filePath) {
        ArrayList<Chat> chats = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            String sender = "";
            String recipient = "";
            String message = "";
            Long timestamp = 0L;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                sender = parts[0];
                recipient = parts[1];
                message = parts[2];
                timestamp = Long.parseLong(parts[3]);
                chats.add(new Chat(sender, recipient, message, timestamp));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return chats;
    }

}
