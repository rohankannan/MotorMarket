package com.cs180proj.app;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * CS 18000 Group Project
 *
 * Class contains all methods and attributes of a server object.
 * Also holds the main method to run the server on port 4242.
 *
 * @author (Rohan Kannan, Alistair Joseph, Lydia Schmucker, Stephen Tushentsov) lab sec 19
 *
 * @version April 4, 2025
 *
 */
public class Server implements ServerInterface, Serializable {

    /**
     * db - database object used to read and write data to the database
     * serverSocket - server socket object used to listen for incoming connections
     * isActive - boolean used to check if the server is running or not
     */
    private Database db;
    private ServerSocket serverSocket;
    private boolean isActive;

    /**
     * Constructor for server class used to initialize server object
     */
    public Server() {
        this.db = new Database();
    }

    /**
     * Method used to start the server and listen for incoming connections
     * @param port the port the server will run on
     */
    public void startServer(int port) throws IOException {
        this.serverSocket = new ServerSocket(port);
        this.isActive = true;
        System.out.println("Server is running on port " + port + "...");
        try {    
            while (isActive) {
                try {
                    workWithClient(serverSocket.accept());
                } catch (IOException e) {
                    if (!isActive) {
                        break;
                    }
                }
            }
        } finally {
            if (serverSocket != null && !serverSocket.isClosed()) {
                serverSocket.close();
            }
        }
    }

    /**
     * This method is used to connect the client and the server
     * and handle the communication between them
     * @param socket the client socket to communicate with
     */
    public void workWithClient(Socket socket) {
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        try {
            oos = new ObjectOutputStream(socket.getOutputStream());
            oos.flush();
            ois = new ObjectInputStream(socket.getInputStream());
            System.out.println("Client connected.");
            while (true) {
                String command = (String) ois.readObject();
                System.out.println("Received command from client: " + command);
                if (command.equals("EXIT")) {
                    System.out.println("Client requested to exit.");
                    break;
                }
                checkClientCommand(command, ois, oos);
            }
        } catch (Exception e) {
            System.out.println("Server-Client Error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (ois != null) 
                    ois.close();
            } catch (Exception e) {
                System.out.println("Error closing ObjectInputStream: " + e.getMessage());
            }

            try {
                if (oos != null) 
                    oos.close();
            } catch (Exception e) {
                System.out.println("Error closing ObjectOutputStream: " + e.getMessage());
            }
            try {
                if (socket != null) {
                    socket.close();
                    System.out.println("Client disconnected.\n");
                }
            } catch (Exception e) {
                System.out.println("Error closing socket: " + e.getMessage());
            }
        }
    }

    /**
     * This method is used to check the command received from the client
     * and perform the corresponding action
     * @param command the command string recieived from the client
     * @param ois the ObjectInputStream to read data from the client
     * @param oos the ObjectOutputStream to send data to the client
     */
    public void checkClientCommand(String command, ObjectInputStream ois, ObjectOutputStream oos)
            throws IOException, ClassNotFoundException {
        switch (command) {
            case "GET_USERS" -> {
                ArrayList<User> users = db.readUserData();
                oos.writeObject(users);
                oos.flush();
            }
            case "GET_LISTINGS" -> {
                ArrayList<Listing> listings = db.readListingData();
                oos.writeObject(listings);
                oos.flush();
            }
            case "ADD_USER" -> {
                User newUser = (User) ois.readObject();
                db.writeUserData(newUser);
                oos.writeObject("User added successfully.");
                oos.flush();
            }
            case "ADD_LISTING" -> {
                Listing newListing = (Listing) ois.readObject();
                db.writeListingData(newListing);
                oos.writeObject("Listing added successfully.");
                oos.flush();
            }
            case "UPDATE_LISTING" -> {
                Listing updated = (Listing) ois.readObject();
                db.updateListing(updated);
                oos.writeObject("Listing updated successfully.");
                oos.flush();
            }
            case "LOGIN" -> {
                String username = (String) ois.readObject();
                String password = (String) ois.readObject();

                ArrayList<User> users = db.readUserData();
                User matched = null;

                for (User u : users) {
                    if (u.getUsername().equals(username) && u.getPassword().equals(password)) {
                        matched = u;
                        break;
                    }
                }

                if (matched != null) {
                    oos.writeObject(matched);
                } else {
                    oos.writeObject("FAIL");
                }
                oos.flush();
            }
            default -> {
                oos.writeObject("Invalid command.");
                oos.flush();
            }
        }
    }


    /**
     * This method is used to stop the server from running
     */
    public void stopServer() throws IOException {
        isActive = false;
        if (serverSocket != null && !serverSocket.isClosed()) {
            serverSocket.close();
        }
    }

    /**
     * This method is used to check if the server is running or not
     * @return true if the server is running, false otherwise
     */
    public boolean isServerRunning() {
        return isActive;
    }

    /**
     * Main method runs server on port 4242
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Server server = new Server();
        server.startServer(4242);
    }
}
