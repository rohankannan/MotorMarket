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
 * the functionality of the constructors, getters, setters, fields,
 * and additional methods of the Listing class.
 *
 * @authors (Rohan Kannan, Alistair Joseph, Lydia Schmucker, Stephen Tushentsov) lab sec 19
 *
 * @version April 4, 2025
 *
 */
public class DatabaseTestCase {

    // Test cases for Listing class getters, constructor, and fields
    // All getter test cases simultanously test the constructor and fields
    // getSeller test case
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

}
