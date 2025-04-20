package com.cs180proj.app;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;
/**
 * CS 18000 Group Project
 *
 * Interface for Client class which contains all
 * methods and fields.
 *
 * @authors (Rohan Kannan, Alistair Joseph,
 * Lydia Schmucker, Stephen Tushentsov) lab sec 19
 *
 * @version April 19, 2025
 */
public interface ClientInterface {
    
    void startClient(int port) throws IOException;
    void checkCommand(String choice, ObjectInputStream ois, ObjectOutputStream oos, Scanner scanner) throws IOException, ClassNotFoundException;
    
    void printMenu();
    
    void getUsers(ObjectOutputStream oos, ObjectInputStream ois) throws IOException, ClassNotFoundException;
    void getListings(ObjectOutputStream oos, ObjectInputStream ois) throws IOException, ClassNotFoundException;

    void addUser(Scanner scanner, ObjectOutputStream oos, ObjectInputStream ois) throws IOException, ClassNotFoundException;
    void addListing(Scanner scanner, ObjectOutputStream oos, ObjectInputStream ois) throws IOException, ClassNotFoundException;

    void exit(ObjectOutputStream oos) throws IOException;
}
