package com.cs180proj.app;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class ListingTestCase {

    //assertEquals(true, listing.isManual());
    //assertEquals("1234", listing.getListingID());

    @Test
    public void testGetSeller() {
        Listing listing = new Listing("john", "link", "toyota", "red", 2500, 2, 360.89, true, "1234");
        assertEquals("john", listing.getSeller());
    }

    @Test
    public void testGetPhotoURL() {
        Listing listing = new Listing("john", "link", "toyota", "red", 2500, 2, 360.89, true, "1234");
        assertEquals("link", listing.getPhotoURL());
    }

    @Test
    public void testGetCarType() {
        Listing listing = new Listing("john", "link", "toyota", "red", 2500, 2, 360.89, true, "1234");
        assertEquals("toyota", listing.getCarType());
    }

    @Test
    public void testGetColor() {
        Listing listing = new Listing("john", "link", "toyota", "red", 2500, 2, 360.89, true, "1234");
        assertEquals("red", listing.getColor());
    }

    @Test
    public void testGetMileage() {
        Listing listing = new Listing("john", "link", "toyota", "red", 2500, 2, 360.89, true, "1234");
        assertEquals(2500, listing.getMileage());
    }

    @Test
    public void testGetAccidents() {
        Listing listing = new Listing("john", "link", "toyota", "red", 2500, 2, 360.89, true, "1234");
        assertEquals(2, listing.getAccidents());
    }

    @Test
    public void testGetPrice() {
        Listing listing = new Listing("john", "link", "toyota", "red", 2500, 2, 360.89, true, "1234");
        assertEquals(360.89, listing.getPrice());
    }

}