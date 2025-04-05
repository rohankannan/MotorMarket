package com.cs180proj.app;

/**
 * CS 18000 Group Project
 *
 * This class contains all methods and 
 * attributes of a Listing object. Each listing also
 * has a photoURL in order to display photos.
 *
 * @authors (Rohan Kannan, Alistair Joseph, Lydia Schmucker, Stephen Tushentsov) lab sec 19
 *
 * @version April 4, 2025
 *
 */
public class Listing implements ListingInterface {
    // fields

    private String listingID;        // the identification number for each listing
    private String sellerUsername;   // the username of the seller for this listing
    private String photoURL;         // the URL for a display photo
    private String carType;          // the type of car in this listing (make/model)
    private String color;            // the color of the car in this listing
    private int mileage;             // the current mileage for the car in this listing
    private int accidents;           // how many accidents this car has been in
    private double price;            // the current price of this listing
    private boolean manual;          // whether or not the car being sold is manual

    /**
     * constructor for Listing objects 9 parameters for each of the 9 attributes
     * of a Listing object in format: String, String, String, String, int, int,
     * double, boolean, String values: username, URL for display photo, type of
     * car(make/model), color of car, mileage, how many accidents, current
     * price, if it is manual, and unique ID number for the listing
     */
    public Listing(String sellerUsername, String photoURL, String carType, String color, int mileage, int accidents, double price, boolean manual, String listingID) {
        this.sellerUsername = sellerUsername;
        this.photoURL = photoURL;
        this.carType = carType;
        this.color = color;
        this.mileage = mileage;
        this.accidents = accidents;
        this.price = price;
        this.manual = manual;
        this.listingID = listingID;
    }

    // getters
    /**
     * the getter/accessor methods for Listing objects returns the value of each
     * attribute of a Listing object respectively
     */
    public String getListingID() {
        return listingID;
    }

    public String getPhotoURL() {
        return photoURL;
    }

    public String getCarType() {
        return carType;
    }

    public String getColor() {
        return color;
    }

    public String getSeller() {
        return sellerUsername;
    }

    public int getMileage() {
        return mileage;
    }

    public int getAccidents() {
        return accidents;
    }

    public double getPrice() {
        return price;
    }

    public boolean isManual() {
        return manual;
    }

    // setters
    /**
     * the setter methods for Listing objects updates/alters the value of each
     * attribute of a Listing object respectively one parameter: the new value
     * that the variable is being set to
     */
    /**
     * one parameter a String that represents the new/updated ID
     */
    public void setListingID(String id) {
        listingID = id;
    }

    /**
     * one parameter a String that represents the new/updated URL
     */
    public void setPhotoURL(String url) {
        photoURL = url;
    }

    /**
     * one parameter a String that represents the new/updated type of car
     */
    public void setCarType(String carType) {
        this.carType = carType;
    }

    /**
     * one parameter a String that represents the new/updated color of the car
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * one parameter a String that represents the new/updated username of the
     * seller
     */
    public void setSeller(String sellerUsername) {
        this.sellerUsername = sellerUsername;
    }

    /**
     * one parameter an int that represents the new/updated mileage
     */
    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    /**
     * one parameter an int that represents the new/updated amount of accidents
     */
    public void setAccidents(int accidents) {
        this.accidents = accidents;
    }

    /**
     * one parameter a double that represents the new/updated price
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * one parameter a boolean that represents the new/updated type of
     * transmission (true: manual, false: automatic)
     */
    public void setManual(boolean manual) {
        this.manual = manual;
    }

    //other methods
    /**
     * toString method returns a string representation of this Listing object
     */
    @Override
    public String toString() {
        return sellerUsername + "," + photoURL + "," + carType + "," + color + ","
                + mileage + "," + accidents + "," + price + "," + manual + "," + listingID;
    }
}
