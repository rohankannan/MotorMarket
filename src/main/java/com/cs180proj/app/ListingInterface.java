package com.cs180proj.app;

/**
 * CS 18000 Group Project
 *
 * Insert Class Description Here
 *
 * @authors (Rohan Kannan, Alistair Joseph, Lydia Schmucker, Stephen Tushentsov) lab sec 19
 *
 * @version April 4, 2025
 *
 */
public interface ListingInterface {

    // getters
    String getPhotoURL();

    String getCarType();

    String getColor();

    String getSeller();

    int getMileage();

    int getAccidents();

    double getPrice();

    boolean isManual();

    // setters
    void setPhotoURL(String url);

    void setCarType(String carType);

    void setColor(String color);

    void setSeller(String seller);

    void setMileage(int mileage);

    void setAccidents(int accidents);

    void setPrice(double price);

    void setManual(boolean manual);

}
