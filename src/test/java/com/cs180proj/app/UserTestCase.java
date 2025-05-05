package com.cs180proj.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
/**
 * CS 18000 Group Project
 *
 * This class contains various methods (test cases) which test
 * the functionality of the constructors, getters, setters, fields,
 * and additional methods of the User class.
 * 
 * @authors (Rohan Kannan, Alistair Joseph, Lydia Schmucker, Stephen Tushentsov) lab sec 19
 *
 * @version April 4, 2025
 *
 */
public class UserTestCase {

    // Test cases for User class getters, constructor, and fields
    // All getter test cases simultanously test the constructor and fields
    // getUsername test case
    @Test
    public void testGetUsername() {
        User user = new User("john", "pass", 1459.32, "addry");
        assertEquals("john", user.getUsername());
    }

    // getPassword test case
    @Test
    public void testGetPassword() {
        User user = new User("john", "pass", 1459.32, "addry");
        assertEquals("pass", user.getPassword());
    }

    // getBalance test case
    @Test
    public void testGetBalance() {
        User user = new User("john", "pass", 1459.32, "addry");
        assertEquals(1459.32, user.getBalance());
    }

    // getAddress test case
    @Test
    public void testGetAddress() {
        User user = new User("john", "pass", 1459.32, "addry");
        assertEquals("addry", user.getAddress());
    }

    // getListings test case
    @Test
    public void testGetListings() {
        User user = new User("john", "pass", 1459.32, "addry");
        assertEquals(0, user.getListings().size());
    }

    // Test cases for User class setters
    // setUsername test case
    @Test
    public void testSetUsername() {
        User user = new User("john", "pass", 1459.32, "addry");
        user.setUsername("jane");
        assertEquals("jane", user.getUsername());
    }

    // setPassword test case
    @Test
    public void testSetPassword() {
        User user = new User("john", "pass", 1459.32, "addry");
        user.setPassword("newpassword456");
        assertEquals("newpassword456", user.getPassword());
    }

    // setBalance test case
    @Test
    public void testSetBalance() {
        User user = new User("john", "pass", 1459.32, "addry");
        user.setBalance(2000.0);
        assertEquals(2000.0, user.getBalance());
    }

    // setAddress test case
    @Test
    public void testSetAddress() {
        User user = new User("john", "pass", 1459.32, "addry");
        user.setAddress("456 Elm St");
        assertEquals("456 Elm St", user.getAddress());
    }

    // Listing test cases
    // addListing test case
    @Test
    public void testAddListing() {
        User user = new User("john", "pass", 1459.32, "addry");
        Listing listing = new Listing("john", "link", "toyota", "red", 2500, 2, 360.89, true, "1234");
        user.addListing(listing);
        assertEquals(listing, user.getListings().get(user.getListings().size() - 1));
    }

    // removeListing test case
    @Test
    public void testRemoveListing() {
        User user = new User("john", "pass", 1459.32, "addry");
        Listing listing1 = new Listing("john", "link", "toyota", "red", 2500, 2, 360.89, true, "1234");
        Listing listing2 = new Listing("johnny", "link", "toyota", "red", 2500, 2, 360.89, true, "1234");
        user.addListing(listing1);
        user.addListing(listing2);
        user.removeListing(listing1);
        assertEquals(listing2, user.getListings().get(user.getListings().size() - 1));
    }

    // updateBalance test cases
    // updateBalance test buying
    @Test
    public void testUpdateBalanceOne() {
        User user = new User("john", "pass", 1459.32, "addry");
        user.updateBalance(100.0, true);
        assertEquals(1359.32, user.getBalance());
    }

    // updateBalance test selling
    @Test
    public void testUpdateBalanceTwo() {
        User user = new User("john", "pass", 1459.32, "addry");
        user.updateBalance(100.0, false);
        assertEquals(1559.32, user.getBalance());
    }

    // Test case for User class toString method
    @Test
    public void testToString() {
        User user = new User("john", "pass", 1459.32, "addry");
        Listing listing1 = new Listing("john", "link", "toyota", "red", 2500, 2, 360.89, true, "1234");
        Listing listing2 = new Listing("johnny", "link", "toyota", "red", 2500, 2, 360.89, true, "1234");
        user.addListing(listing1);
        user.addListing(listing2);
        String expected = "john,pass,1459.32,addry";
        assertEquals(expected, user.toString());
    }

}
