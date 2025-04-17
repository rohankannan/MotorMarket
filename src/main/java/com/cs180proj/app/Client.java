package com.cs180proj.app;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.*;

/**
 * CS 18000 Group Project
 *
 * Client app for interacting with the car listing server
 *
 * @authors (Rohan Kannan, Alistair Joseph,
 * Lydia Schmucker, Stephen Tushentsov) lab sec 19
 *
 * @version April 4, 2025
 */

public class Client {

    public static void main(String[] args) {
        try (
                Socket socket = new Socket("localhost", 4242);
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                Scanner scanner = new Scanner(System.in)
        ) {
            oos.flush();

            System.out.println("Connected to server.");

            while (true) {
                System.out.println("\nChoose a command:");
                System.out.println("1. GET_USERS");
                System.out.println("2. GET_LISTINGS");
                System.out.println("3. ADD_USER");
                System.out.println("4. ADD_LISTING");
                System.out.println("5. EXIT");

                String choice = scanner.nextLine();

                switch (choice) {
                    case "1":
                        oos.writeObject("GET_USERS");
                        oos.flush();

                        try {
                            ArrayList<User> users = (ArrayList<User>) ois.readObject();
                            System.out.println("Users:");
                            for (User user : users) {
                                System.out.println(user);
                            }
                        } catch (EOFException eof) {
                            System.out.println("No users found or error receiving data.");
                        }
                        break;

                    case "2":
                        oos.writeObject("GET_LISTINGS");
                        oos.flush();

                        try {
                            ArrayList<Listing> listings = (ArrayList<Listing>) ois.readObject();
                            System.out.println("Listings:");
                            for (Listing listing : listings) {
                                System.out.println(listing);
                            }
                        } catch (EOFException eof) {
                            System.out.println("No listings found or error receiving data.");
                        }
                        break;

                    case "3":
                        oos.writeObject("ADD_USER");
                        oos.flush();

                        System.out.println("Enter new user details:");
                        System.out.print("Username: ");
                        String username = scanner.nextLine();
                        System.out.print("Password: ");
                        String password = scanner.nextLine();
                        System.out.print("Balance: ");
                        double balance = Double.parseDouble(scanner.nextLine());
                        System.out.print("Address: ");
                        String address = scanner.nextLine();

                        User newUser = new User(username, password, balance, address);
                        oos.writeObject(newUser);
                        oos.flush();

                        try {
                            String userResponse = (String) ois.readObject();
                            System.out.println("Server: " + userResponse);
                        } catch (EOFException eof) {
                            System.out.println("No confirmation from server.");
                        }
                        break;

                    case "4":
                        oos.writeObject("ADD_LISTING");
                        oos.flush();

                        System.out.println("Enter new listing details:");
                        System.out.print("Seller Username: ");
                        String seller = scanner.nextLine();
                        System.out.print("Photo URL: ");
                        String url = scanner.nextLine();
                        System.out.print("Car Type: ");
                        String type = scanner.nextLine();
                        System.out.print("Color: ");
                        String color = scanner.nextLine();
                        System.out.print("Mileage: ");
                        int mileage = Integer.parseInt(scanner.nextLine());
                        System.out.print("Accidents: ");
                        int accidents = Integer.parseInt(scanner.nextLine());
                        System.out.print("Price: ");
                        double price = Double.parseDouble(scanner.nextLine());
                        System.out.print("Is Manual (true/false): ");
                        boolean manual = Boolean.parseBoolean(scanner.nextLine());
                        System.out.print("Listing ID: ");
                        String id = scanner.nextLine();

                        Listing newListing = new Listing(seller, url, type, color, mileage, accidents, price, manual, id);
                        oos.writeObject(newListing);
                        oos.flush();

                        try {
                            String listingResponse = (String) ois.readObject();
                            System.out.println("Server: " + listingResponse);
                        } catch (EOFException eof) {
                            System.out.println("No confirmation from server.");
                        }
                        break;

                    case "5":
                        System.out.println("Exiting...");
                        oos.writeObject("EXIT");
                        oos.flush();
                        return;

                    default:
                        System.out.println("Invalid input. Try again.");
                }
            }

        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Client error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
