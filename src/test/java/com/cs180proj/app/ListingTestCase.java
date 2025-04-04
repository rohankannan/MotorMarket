package com.cs180proj.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
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
public class ListingTestCase {

    // Test cases for Listing class getters, constructor, and fields
    // All getter test cases simultanously test the constructor and fields
    // getSeller test case
    @Test
    public void testGetSeller() {
        Listing listing = new Listing("john", "link", "toyota", "red", 2500, 2, 360.89, true, "1234");
        assertEquals("john", listing.getSeller());
    }

    // getPhotoURL test case
    @Test
    public void testGetPhotoURL() {
        Listing listing = new Listing("john", "link", "toyota", "red", 2500, 2, 360.89, true, "1234");
        assertEquals("link", listing.getPhotoURL());
    }

    // getCarType test case
    @Test
    public void testGetCarType() {
        Listing listing = new Listing("john", "link", "toyota", "red", 2500, 2, 360.89, true, "1234");
        assertEquals("toyota", listing.getCarType());
    }

    // getColor test case
    @Test
    public void testGetColor() {
        Listing listing = new Listing("john", "link", "toyota", "red", 2500, 2, 360.89, true, "1234");
        assertEquals("red", listing.getColor());
    }

    // getMileage test case
    @Test
    public void testGetMileage() {
        Listing listing = new Listing("john", "link", "toyota", "red", 2500, 2, 360.89, true, "1234");
        assertEquals(2500, listing.getMileage());
    }

    // getAccidents test case
    @Test
    public void testGetAccidents() {
        Listing listing = new Listing("john", "link", "toyota", "red", 2500, 2, 360.89, true, "1234");
        assertEquals(2, listing.getAccidents());
    }

    // getPrice test case
    @Test
    public void testGetPrice() {
        Listing listing = new Listing("john", "link", "toyota", "red", 2500, 2, 360.89, true, "1234");
        assertEquals(360.89, listing.getPrice());
    }

    // isManual test case
    @Test
    public void testIsManual() {
        Listing listing = new Listing("john", "link", "toyota", "red", 2500, 2, 360.89, true, "1234");
        assertEquals(true, listing.isManual());
    }

    // getListingID test case
    @Test
    public void testGetListingID() {
        Listing listing = new Listing("john", "link", "toyota", "red", 2500, 2, 360.89, true, "1234");
        assertEquals("1234", listing.getListingID());
    }

    // Test cases for Listing class setters
    // setSeller test case
    @Test
    public void testSetSeller() {
        Listing listing = new Listing("john", "link", "toyota", "red", 2500, 2, 360.89, true, "1234");
        listing.setSeller("jimmy");
        assertEquals("jimmy", listing.getSeller());
    }

    // setPhotoURL test case
    @Test
    public void testSetPhotoURL() {
        Listing listing = new Listing("john", "link", "toyota", "red", 2500, 2, 360.89, true, "1234");
        listing.setPhotoURL("newlink");
        assertEquals("newlink", listing.getPhotoURL());
    }

    // setCarType test case
    @Test
    public void testSetCarType() {
        Listing listing = new Listing("john", "link", "toyota", "red", 2500, 2, 360.89, true, "1234");
        listing.setCarType("honda");
        assertEquals("honda", listing.getCarType());
    }

    // setColor test case
    @Test
    public void testSetColor() {
        Listing listing = new Listing("john", "link", "toyota", "red", 2500, 2, 360.89, true, "1234");
        listing.setColor("blue");
        assertEquals("blue", listing.getColor());
    }

    // setMileage test case
    @Test
    public void testSetMileage() {
        Listing listing = new Listing("john", "link", "toyota", "red", 2500, 2, 360.89, true, "1234");
        listing.setMileage(3000);
        assertEquals(3000, listing.getMileage());
    }

    // setAccidents test case
    @Test
    public void testSetAccidents() {
        Listing listing = new Listing("john", "link", "toyota", "red", 2500, 2, 360.89, true, "1234");
        listing.setAccidents(3);
        assertEquals(3, listing.getAccidents());
    }

    // setPrice test case
    @Test
    public void testSetPrice() {
        Listing listing = new Listing("john", "link", "toyota", "red", 2500, 2, 360.89, true, "1234");
        listing.setPrice(400.00);
        assertEquals(400.00, listing.getPrice());
    }

    // setManual test case
    @Test
    public void testSetManual() {
        Listing listing = new Listing("john", "link", "toyota", "red", 2500, 2, 360.89, true, "1234");
        listing.setManual(false);
        assertEquals(false, listing.isManual());
    }

    // setListingID test case
    @Test
    public void testSetListingID() {
        Listing listing = new Listing("john", "link", "toyota", "red", 2500, 2, 360.89, true, "1234");
        listing.setListingID("5678");
        assertEquals("5678", listing.getListingID());
    }

    // Test case for Listing class toString method
    @Test
    public void testToString() {
        Listing listing = new Listing("john", "link", "toyota", "red", 2500, 2, 360.89, true, "1234");
        String expected = "john,link,toyota,red,2500,2,360.89,true,1234";
        assertEquals(expected, listing.toString());
    }

}
