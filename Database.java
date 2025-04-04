package com.cs180proj.app;
public class Database implements DatabaseInterface{

    @Override
    public void writeUserData() {

    }

    @Override
    public void writeListingData() {

    }

    @Override
    public User[] readUserData() {
        return new User[0];
    }

    @Override
    public Listing[] readListingData() {
        return new Listing[0];
    }
}
