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


}
