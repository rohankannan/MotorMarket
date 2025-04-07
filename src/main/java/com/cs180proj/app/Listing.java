package com.cs180proj.app;

/**
 * CS 18000 Group Project
 * This class contains all methods and 
 * attributes of a Listing object. Each listing also
 * has a photoURL in order to display photos.
 *
 * @author (Rohan Kannan, Alistair Joseph, Lydia Schmucker, Stephen Tushentsov) lab sec 19
 *
 * @version April 4, 2025
 *
 */
public class Listing implements ListingInterface {

    private String listingID;        // the identification number for each listing
    private String sellerUsername;   // the username of the seller for this listing
    private String photoURL;         // the URL for a display photo
    private String carType;          // the type of car in this listing (make/model)
    private String color;            // the color of the car in this listing
    private int mileage;             // the current mileage for the car in this listing
    private int accidents;           // how many accidents this car has been in
    private double price;            // the current price of this listing
    private boolean manual;          // whether the car being sold is manual

    /**
     * constructor for Listing objects 9 parameters for each of the 9 attributes
     * of a Listing object in format: String, String, String, String, int, int,
     * double, boolean, String values: username, URL for display photo, type of
     * car(make/model), color of car, mileage, how many accidents, current
     * price, if it is manual, and unique ID number for the listing
     * @param sellerUsername the username of the seller for this listing
     * @param photoURL the URL for a display photo of the car
     * @param carType the type of car (make/model)
     * @param color the color of the car
     * @param mileage the current mileage of the car
     * @param accidents the number of accidents the car has been in
     * @param price the current price of the listing
     * @param manual whether the car is manual (true) or automatic (false)
     * @param listingID the unique ID for this listing
     */
    public Listing(String sellerUsername, String photoURL, String carType, String color, int mileage,
                   int accidents, double price, boolean manual, String listingID) {
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

    /**
     * get listing ID
     * @return listingID the unique ID for this listing
     */
    public String getListingID() {
        return listingID;
    }

    /**
     * getter method for the photo URL
     * @return photoURL the URL for the display photo of the car
     */
    public String getPhotoURL() {
        return photoURL;
    }

    /**
     * getter method for the type of car (make/model)
     * @return carType the type of car in this listing
     */
    public String getCarType() {
        return carType;
    }

    /**
     * getter method for the color of the car
     * @return color the color of the car in this listing
     */
    public String getColor() {
        return color;
    }

    /**
     * getter method for the username of the seller
     * @return sellerUsername the username of the seller for this listing
     */
    public String getSeller() {
        return sellerUsername;
    }

    /**
     * getter method for the mileage of the car
     * @return mileage the current mileage of the car in this listing
     */
    public int getMileage() {
        return mileage;
    }

    /**
     * getter method for the number of accidents the car has been in
     * @return accidents the number of accidents for the car in this listing
     */
    public int getAccidents() {
        return accidents;
    }

    /**
     * getter method for the price of the listing
     * @return price the current price of the listing
     */
    public double getPrice() {
        return price;
    }

    /**
     * getter method for whether the car is manual or automatic
     * @return manual true if the car is manual, false if it is automatic
     */
    public boolean isManual() {
        return manual;
    }

    /**
     * one parameter a String that represents the new/updated ID
     * @param id the new unique ID for this listing
     */
    public void setListingID(String id) {
        listingID = id;
    }

    /**
     * one parameter a String that represents the new/updated URL
     * @param url the new URL for the display photo of the car
     */
    public void setPhotoURL(String url) {
        photoURL = url;
    }

    /**
     * one parameter a String that represents the new/updated type of car
     * @param carType the new type of car (make/model) for this listing
     */
    public void setCarType(String carType) {
        this.carType = carType;
    }

    /**
     * one parameter a String that represents the new/updated color of the car
     * @param color the new color of the car in this listing
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * one parameter a String that represents the new/updated username of the
     * seller
     * @param sellerUsername the new username of the seller for this listing
     */
    public void setSeller(String sellerUsername) {
        this.sellerUsername = sellerUsername;
    }

    /**
     * one parameter an int that represents the new/updated mileage
     * @param mileage the new current mileage of the car in this listing
     */
    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    /**
     * one parameter an int that represents the new/updated amount of accidents
     * @param accidents the new number of accidents the car has been in for this listing
     */
    public void setAccidents(int accidents) {
        this.accidents = accidents;
    }

    /**
     * one parameter a double that represents the new/updated price
     * @param price the new current price of the listing
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * one parameter a boolean that represents the new/updated type of
     * transmission (true: manual, false: automatic)
     * @param manual the new type of transmission for this listing
     */
    public void setManual(boolean manual) {
        this.manual = manual;
    }

    /**
     * toString method returns a string representation of this Listing object
     * @return a string of all information separated by commas
     */
    @Override
    public String toString() {
        return sellerUsername + "," + photoURL + "," + carType + "," + color + ","
                + mileage + "," + accidents + "," + price + "," + manual + "," + listingID;
    }
}
