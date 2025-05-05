package com.cs180proj.app;

import java.io.*;
import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

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
    private static final String CHAT_FILE = "src/main/java/com/cs180proj/app/data/Chatlog.txt";

    private final ReentrantLock chatLock = new ReentrantLock();

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
                if (parts.length >= 4) {
                    System.out.println("Reading user: " + Arrays.toString(parts));
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

    public void writeChatData(Chat chat, String filePath) throws IOException {
        if (chat.getSender() == null || chat.getRecipient() == null || chat.getMessage() == null) return;

        chatLock.lock();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(chat.toString());
            writer.newLine();
            writer.flush();
        } finally {
            chatLock.unlock();
        }
    }

    public ArrayList<Chat> readChatData(String filePath) throws IOException {
        ArrayList<Chat> chats = new ArrayList<>();
        chatLock.lock();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    String[] parts = line.split(",", 4);
                    if (parts.length != 4) continue;
                    String sender = parts[0];
                    String recipient = parts[1];
                    String message = parts[2];
                    long timestamp = Long.parseLong(parts[3]);
                    chats.add(new Chat(sender, recipient, message, timestamp));
                } catch (Exception e) {
                    System.err.println("Corrupt chat line: " + line);
                }
            }
        } finally {
            chatLock.unlock();
        }
        return chats;
    }

    public Listing getListingById(String id) {
        ArrayList<Listing> listings = readListingData();
        for (Listing l : listings) {
            if (l.getListingID().equals(id)) return l;
        }
        return null;
    }

    public void overwriteListingData(ArrayList<Listing> listings) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LISTING_FILE))) {
            for (Listing l : listings) {
                writer.write(l.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateListing(Listing updated) {
        ArrayList<Listing> listings = readListingData();
        for (int i = 0; i < listings.size(); i++) {
            if (listings.get(i).getListingID().equals(updated.getListingID())) {
                listings.set(i, updated);
                break;
            }
        }
        overwriteListingData(listings);
    }

    public User getUserByUsername(String username) {
        ArrayList<User> users = readUserData();
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public void updateUser(User updatedUser) {
        ArrayList<User> users = readUserData();
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUsername().equals(updatedUser.getUsername())) {
                users.set(i, updatedUser);
                break;
            }
        }
        overwriteUserData(users);
    }


    public void overwriteUserData(ArrayList<User> users) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(USER_FILE))) {
            for (User user : users) {
                writer.write(user.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
