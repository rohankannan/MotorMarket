package com.cs180proj.app;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * CS 18000 Group Project
 *
 * Client app for interacting with the car listing server
 *
 * @author (Rohan Kannan, Alistair Joseph,
 * Lydia Schmucker, Stephen Tushentsov) lab sec 19
 *
 * @version April 4, 2025
 */

public class Client implements ClientInterface {

    // Socket object used to connect to the server
    private Socket socket;

    /**
     * Method used to start the client and connect to the server
     * @param port the port the client will run on
     */
    public void startClient(int port) throws IOException {
        this.socket = new Socket("localhost", port);
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        try {
            oos = new ObjectOutputStream(socket.getOutputStream());
            ois = new ObjectInputStream(socket.getInputStream());
            Scanner scanner = new Scanner(System.in);
            oos.flush();
            System.out.println("Connected to server.");
            while (true) {
                printMenu();
                String choice = scanner.nextLine();
                checkCommand(choice, ois, oos, scanner);
            }
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Client error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (ois != null) {
                ois.close();
            }
            if (oos != null) {
                oos.close();
            }
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
        }
    }

    /**
     * Method used to check which command the user has chosen and call the appropriate method
     * @param choice the command chosen by the user
     * @param ois the ObjectInputStream used to read data from the server
     * @param oos the ObjectOutputStream used to write data to the server
     * @param scanner the Scanner object used to read user input
     */
    public void checkCommand(String choice, ObjectInputStream ois,
                             ObjectOutputStream oos, Scanner scanner) throws IOException, ClassNotFoundException {
        switch (choice) {
            case "1":
                getUsers(oos, ois);
                break;
            case "2":
                getListings(oos, ois);
                break;
            case "3":
                addUser(scanner, oos, ois);
                break;
            case "4":
                addListing(scanner, oos, ois);
                break;
            case "5":
                exit(oos);
                break;
            default:
                System.out.println("Invalid input. Try again.");
        }
    }

    /**
     * Method that prints all the commands available to the user
     */
    public void printMenu() {
        System.out.println("\nChoose a command:");
        System.out.println("1. GET_USERS");
        System.out.println("2. GET_LISTINGS");
        System.out.println("3. ADD_USER");
        System.out.println("4. ADD_LISTING");
        System.out.println("5. EXIT");
    }

    /**
     * Method used to get all users from the server
     * @param oos the ObjectOutputStream used to write data to the server
     * @param ois the ObjectInputStream used to read data from the server
     */
    public void getUsers(ObjectOutputStream oos, ObjectInputStream ois) throws IOException, ClassNotFoundException {
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
    }

    /**
     * Method used to get all listings from the server
     * @param oos the ObjectOutputStream used to write data to the server
     * @param ois the ObjectInputStream used to read data from the server
     */
    public void getListings(ObjectOutputStream oos, ObjectInputStream ois) throws IOException, ClassNotFoundException {
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
    }

    /**
     * Method used to add a new user to the server
     * @param scanner the Scanner object used to read user input
     * @param oos the ObjectOutputStream used to write data to the server
     * @param ois the ObjectInputStream used to read data from the server
     */
    public void addUser(Scanner scanner, ObjectOutputStream oos,
                        ObjectInputStream ois) throws IOException, ClassNotFoundException {
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
        try {
            oos.writeObject(newUser);
        } catch (Exception e) {
            System.out.println("Unable to write user, Exception: " + e);
        }
        oos.flush();

        try {
            String userResponse = (String) ois.readObject();
            System.out.println("Server: " + userResponse);
        } catch (EOFException eof) {
            System.out.println("No confirmation from server.");
        }
    }

    /**
     * Method used to add a new listing to the server
     * @param scanner the Scanner object used to read user input
     * @param oos the ObjectOutputStream used to write data to the server
     * @param ois the ObjectInputStream used to read data from the server
     */
    public void addListing(Scanner scanner, ObjectOutputStream oos,
                           ObjectInputStream ois) throws IOException, ClassNotFoundException {
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
        try {
            oos.writeObject(newListing);
        } catch (Exception e) {
            System.out.println("Didn't write Listing - Exception: " + e);
        }
        oos.flush();

        try {
            String listingResponse = (String) ois.readObject();
            System.out.println("Server: " + listingResponse);
        } catch (EOFException eof) {
            System.out.println("No confirmation from server.");
        }
    }

    /**
     * Method used to exit the client
     * @param oos the ObjectOutputStream used to write data to the server
     */
    public void exit(ObjectOutputStream oos) throws IOException {
        System.out.println("Exiting...");
        oos.writeObject("EXIT");
        oos.flush();
    }

    /**
     * Main method runs client on port 4242
     */
    public static void main(String[] args) {
        Client client = new Client();
        try {
            client.startClient(4242);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}