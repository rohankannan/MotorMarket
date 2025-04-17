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
 * Insert Class Description Here
 *
 * @authors (Rohan Kannan, Alistair Joseph, Lydia Schmucker, Stephen Tushentsov) lab sec 19
 *
 * @version April 4, 2025
 *
 */
public class Server implements Serializable {

    private Database db;
    private ServerSocket serverSocket;
    private boolean isActive;

    public Server() {
        this.db = new Database();
    }

    public void startServer(int port) throws IOException {
        this.serverSocket = new ServerSocket(port);
        this.isActive = true;
        System.out.println("Server is running on port " + port + "...");
        while (isActive) {
            workWithClient(serverSocket.accept());
        }
    }

    public void workWithClient(Socket socket) {
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        try {    
            oos = new ObjectOutputStream(socket.getOutputStream());
            oos.flush();
            ois = new ObjectInputStream(socket.getInputStream());
            System.out.println("Client connected.");
            String command = (String) ois.readObject();
            System.out.println("Received command from client: " + command);
            checkClientCommand(command, ois, oos);
        } catch (Exception e) {
            System.out.println("Server-Client Error: " + e.getMessage());
        } finally {
            try {
                if (ois != null) {
                    ois.close();
                }
            } catch (Exception e) {
                System.out.println("Error closing ObjectInputStream: " + e.getMessage());
            }
            try {
                if (oos != null) {
                    oos.close();
                }
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

    public void checkClientCommand(String command, ObjectInputStream ois, ObjectOutputStream oos) throws IOException, ClassNotFoundException{
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
    }

    public void stopServer() throws IOException {
        isActive = false;
        if (serverSocket != null) {
            serverSocket.close();
        }
    }

    public boolean isServerRunning() {
        return isActive;
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Server server = new Server();
        server.startServer(4242);
    }
}
