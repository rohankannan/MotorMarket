package com.cs180proj.app;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * CS 18000 Group Project
 *
 * This class contains various methods (test cases) which test
 * the functionality of the write and read methods found in the Database class.
 *
 * @author (Rohan Kannan, Alistair Joseph, Lydia Schmucker, Stephen Tushentsov) lab sec 19
 *
 * @version April 4, 2025
 *
 */
public class DatabaseTestCase {

    /**
     * Test case to ensure that the writeUserData method works correctly.
     */
    @Test
    public void testWriteUserData() {
        File dbFile = new File("database_test.txt");
        try {
            if (dbFile.exists()) {
                dbFile.delete();
            }
            dbFile.createNewFile();
        } catch (IOException e) {
            System.out.println("Error creating test case database file: " + e.getMessage());
        }

        Database db = new Database();
        User u = new User("user6560", "mypassword1", 100.0, "1 Purdue Drive");
        db.writeUserData(u,dbFile.getPath());

        boolean isWritten = false;
        try (Scanner scanner = new Scanner(dbFile)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.contains("user6560") && line.contains("mypassword1") && line.contains("100.0") && line.contains("1 Purdue Drive")) {
                    isWritten = true;
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading test case database file: " + e.getMessage());
        }

        assertTrue(isWritten, "User data should be written to the database");
        dbFile.delete(); // Clean up the test file after the test case
    }

    /**
     * Test case to ensure that the writeListingData method works correctly.
     */
    @Test
    public void testWriteListingData() {
        File dbFile = new File("database_test.txt");
        try {
            if (dbFile.exists()) {
                dbFile.delete();
            }
            dbFile.createNewFile();
        } catch (IOException e) {
            System.out.println("Error creating test case database file: " + e.getMessage());
        }

        Database db = new Database();
        Listing l = new Listing("seller123", "http://example.com/photo.jpg", "Toyota Camry", "Blue", 50000,
                1, 15000.0, false, "listing123");
        db.writeListingData(l,dbFile.getPath());

        boolean isWritten = false;
        try (Scanner scanner = new Scanner(dbFile)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.contains("seller123") && line.contains("http://example.com/photo.jpg") && line.contains("Toyota Camry")
                        && line.contains("Blue") && line.contains("50000") && line.contains("1")
                        && line.contains("15000.0") && line.contains("false") && line.contains("listing123")) {
                    isWritten = true;
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading test case database file: " + e.getMessage());
        }

        assertTrue(isWritten, "Listing data should be written to the database");
        dbFile.delete(); // Clean up the test file after the test case
    }

    /**
     * Test case to ensure that the readListingData method works correctly.
     */
    @Test
    public void testReadUserData() {
        // Create a test database file
        File dbFile = new File("database_test.txt");
        try {
            if (dbFile.exists()) {
                dbFile.delete();
            }
            dbFile.createNewFile();
        } catch (IOException e) {
            System.out.println("Error creating test case database file: " + e.getMessage());
        }

        // Write a test user to the file
        Database db = new Database();
        User u = new User("user6560", "mypassword1", 100.0, "1 Purdue Drive");
        db.writeUserData(u, dbFile.getPath());

        // Read the user data back from the file
        var users = db.readUserData(dbFile.getPath());

        assertEquals(1, users.size(), "There should be one user in the database");
        assertEquals("user6560", users.get(0).getUsername(), "The username should match");
        assertEquals("mypassword1", users.get(0).getPassword(), "The password should match");
        assertEquals(100.0, users.get(0).getBalance(), 0.01, "The balance should match");
        assertEquals("1 Purdue Drive", users.get(0).getAddress(), "The address should match");

        dbFile.delete(); // Clean up the test file after the test case
    }

    /**
     * Test case to ensure that the readListingData method works correctly.
     */
    @Test
    public void testReadListingData() {
        // Create a test database file
        File dbFile = new File("database_test.txt");
        try {
            if (dbFile.exists()) {
                dbFile.delete();
            }
            dbFile.createNewFile();
        } catch (IOException e) {
            System.out.println("Error creating test case database file: " + e.getMessage());
        }

        // Write a test user to the file
        Database db = new Database();
        Listing l = new Listing("seller123", "http://example.com/photo.jpg", "Toyota Camry", "Blue", 50000,
                1, 15000.0, false, "listing123");
        db.writeListingData(l, dbFile.getPath());

        // Read the user data back from the file
        var listings = db.readListingData(dbFile.getPath());

        assertEquals(1, listings.size(), "There should be one listing in the database");
        assertEquals("seller123", listings.get(0).getSeller(), "The seller should match");
        assertEquals("http://example.com/photo.jpg", listings.get(0).getPhotoURL(), "The photo URL should match");
        assertEquals("Toyota Camry", listings.get(0).getCarType(), "The car type should match");
        assertEquals("Blue", listings.get(0).getColor(), "The color should match");
        assertEquals(50000, listings.get(0).getMileage(), "The mileage should match");
        assertEquals(1, listings.get(0).getAccidents(), "The number of accidents should match");
        assertEquals(15000.0, listings.get(0).getPrice(), 0.01, "The price should match");
        assertEquals(false, listings.get(0).isManual(), "The manual status should match");
        assertEquals("listing123", listings.get(0).getListingID(), "The listing ID should match");
        // Clean up the test file after the test case
        dbFile.delete(); // Clean up the test file after the test case
    }

}
