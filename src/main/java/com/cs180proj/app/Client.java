package com.cs180proj.app;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.*;

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

public class Client {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Socket socket = new Socket("localhost", 4242);
        System.out.println("Connected to server.");

        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        oos.flush(); // must flush before ObjectInputStream
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

        Scanner scanner = new Scanner(System.in);

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
                    ArrayList<User> users = (ArrayList<User>) ois.readObject();
                    System.out.println("Users:");
                    for (User user : users) {
                        System.out.println(user);
                    }
                    break;

                case "2":
                    oos.writeObject("GET_LISTINGS");
                    oos.flush();
                    ArrayList<Listing> listings = (ArrayList<Listing>) ois.readObject();
                    System.out.println("Listings:");
                    for (Listing listing : listings) {
                        System.out.println(listing);
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
                    System.out.println(newUser);
                    oos.writeObject(newUser);
                    oos.flush();

                    String userResponse = (String) ois.readObject();
                    System.out.println("Server: " + userResponse);
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

                    String listingResponse = (String) ois.readObject();
                    System.out.println("Server: " + listingResponse);
                    break;

                case "5":
                    System.out.println("Exiting...");
                    oos.close();
                    ois.close();
                    socket.close();
                    return;

                default:
                    System.out.println("Invalid input. Try again.");
            }
        }
    }
}
