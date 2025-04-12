package com.cs180proj.app;

import java.io.*;
import java.net.*;
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
public class Server {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Database db = new Database();

        ServerSocket serverSocket = new ServerSocket(4242);
        System.out.println("Server is running on port 4242...");

        while (true) {
            Socket socket = serverSocket.accept();
            System.out.println("Client connected.");

            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.flush();
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

            String command = (String) ois.readObject();
            System.out.println("Received command from client: " + command);

            switch (command) {
                case "GET_USERS":
                    ArrayList<User> users = db.readUserData();
                    oos.writeObject(users);
                    oos.flush();
                    break;

                case "GET_LISTINGS":
                    ArrayList<Listing> listings = db.readListingData();
                    oos.writeObject(listings);
                    oos.flush();
                    break;

                case "ADD_USER":
                    User newUser = (User) ois.readObject();
                    db.writeUserData(newUser);
                    oos.writeObject("User added successfully.");
                    oos.flush();
                    break;

                case "ADD_LISTING":
                    Listing newListing = (Listing) ois.readObject();
                    db.writeListingData(newListing);
                    oos.writeObject("Listing added successfully.");
                    oos.flush();
                    break;

                default:
                    oos.writeObject("Invalid command.");
                    oos.flush();
                    break;
            }

            oos.close();
            ois.close();
            socket.close();
            System.out.println("Client disconnected.\n");
        }
    }
}
