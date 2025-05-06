package com.cs180proj.app;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class Server implements ServerInterface, Serializable {

    private final Database db;
    private ServerSocket serverSocket;
    private boolean isActive;

    /**
     * Constructs a new Server instance and initializes the database.
     */

    public Server() {
        this.db = new Database();
    }

    /**
     * Starts the server and listens for incoming connections, handling them in separate threads.
     */
    public void startServer(int port) throws IOException {
        this.serverSocket = new ServerSocket(port);
        this.isActive = true;
        System.out.println("Server is running on port " + port + "...");

        try {
            while (isActive) {
                // Accept incoming client connections
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client connected: " + clientSocket);

                new Thread(new ClientHandler(clientSocket)).start();
            }
        } finally {
            if (serverSocket != null && !serverSocket.isClosed()) {
                serverSocket.close();
            }
        }
    }

    /**
     * Placeholder method for external Socket connection. Currently unused.
     */

    @Override
    public void workWithClient(Socket socket) throws IOException, ClassNotFoundException {

    }

    /**
     * Placeholder method used for testing purposes only.
     */

    @Override
    public void checkClientCommand(String command, ObjectInputStream ois, ObjectOutputStream oos) throws IOException,
            ClassNotFoundException {

    }

    /**
     * Handles communication with a client in a separate thread.
     */
    private class ClientHandler implements Runnable {
        private final Socket socket;
        private ObjectInputStream ois;
        private ObjectOutputStream oos;

        /**
         * Constructs a handler for the given client socket.
         *
         * @param socket the client's socket connection
         */

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        /**
         * Handles incoming communication from the connected client.
         * Reads commands and invokes appropriate database operations.
         */

        @Override
        public void run() {
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
            } catch (SocketException e) {
                System.out.println("Client disconnected abruptly: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Server-Client Error: " + e.getMessage());
                e.printStackTrace();
            } finally {
                try {
                    if (ois != null) ois.close();
                } catch (Exception e) {
                    System.out.println("Error closing ObjectInputStream: " + e.getMessage());
                }
                try {
                    if (oos != null) oos.close();
                } catch (Exception e) {
                    System.out.println("Error closing ObjectOutputStream: " + e.getMessage());
                }
                try {
                    if (socket != null) socket.close();
                    System.out.println("Client disconnected.\n");
                } catch (Exception e) {
                    System.out.println("Error closing socket: " + e.getMessage());
                }
            }
        }

        /**
         * Handles commands sent by the client.
         */
        private void checkClientCommand(String command, ObjectInputStream ois, ObjectOutputStream oos)
                throws IOException, ClassNotFoundException {
            switch (command) {
                case "GET_USERS" -> {
                    ArrayList<User> users = db.readUserData();
                    oos.writeObject(users);
                    oos.flush();
                }
                case "GET_LISTINGS" -> {
                    ArrayList<Listing> listings = db.readListingData();
                    ArrayList<Listing> filteredListings = new ArrayList<>();
                    String username = (String) ois.readObject();
                    for (Listing l : listings) {
                        if (!l.getSeller().equals(username) && !l.isSold()) {
                            System.out.println("added: " + l);
                            filteredListings.add(l);
                        }
                    }
                    oos.writeObject(filteredListings);
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
                case "GET_CHAT" -> {
                    long lastUpdated = (long) ois.readObject();
                    ArrayList<Chat> chats = db.readChatData("src/main/java/com/cs180proj/app/data/Chatlog.txt");
                    ArrayList<Chat> newChats = new ArrayList<>();

                    for (Chat chat : chats) {
                        if (chat.getTimestamp() > lastUpdated) {
                            newChats.add(chat);
                        }
                    }

                    oos.writeObject(newChats); // Send only new messages
                    oos.flush();
                }
                case "ADD_CHAT" -> {
                    Chat newChat = (Chat) ois.readObject();
                    db.writeChatData(newChat, "src/main/java/com/cs180proj/app/data/Chatlog.txt");
                    oos.writeObject("Chat added successfully.");
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

                case "BUY_LISTING" -> {
                    String listingID = (String) ois.readObject();
                    String buyerUsername = (String) ois.readObject();

                    Listing listing = db.getListingById(listingID);
                    if (listing == null || listing.isSold()) {
                        oos.writeObject("LISTING_NOT_FOUND");
                        break;
                    }

                    User buyer = db.getUserByUsername(buyerUsername);
                    User seller = db.getUserByUsername(listing.getSeller());

                    if (buyer.getBalance() < listing.getPrice()) {
                        oos.writeObject("INSUFFICIENT_FUNDS");
                    } else {
                        buyer.setBalance(buyer.getBalance() - listing.getPrice());
                        seller.setBalance(seller.getBalance() + listing.getPrice());

                        db.updateUser(buyer);
                        db.updateUser(seller);

                        listing.setSold(true);
                        System.out.println("SOLD: " + listing);
                        db.updateListing(listing);

                        oos.writeObject("PURCHASE_SUCCESS");
                    }
                    oos.flush();
                }
                case "UPDATE_USER" -> {
                    User updated = (User) ois.readObject();
                    db.updateUser(updated);
                    oos.writeObject("User updated successfully.");
                    oos.flush();
                }
                default -> {
                    oos.writeObject("Invalid command.");
                    oos.flush();
                }

                case "GET_MY_LISTINGS" -> {
                    ArrayList<Listing> listings = db.readListingData();
                    String username = (String) ois.readObject();

                    ArrayList<Listing> myListings = new ArrayList<>();
                    for (Listing l : listings) {
                        if (l.getSeller().equals(username)) {
                            myListings.add(l);
                        }
                    }

                    oos.writeObject(myListings);
                    oos.flush();
                }
            }
        }
    }

    /**
     * Stops the server from running.
     */
    public void stopServer() throws IOException {
        isActive = false;
        if (serverSocket != null && !serverSocket.isClosed()) {
            serverSocket.close();
        }
    }

    /**
     * Checks if the server is running or not.
     */
    public boolean isServerRunning() {
        return isActive;
    }

    /**
     * The entry point for launching Server. Starts server on port 4242.
     *
     * @param args command-line arguments (not used)
     * @throws IOException if server startup fails
     * @throws ClassNotFoundException if class deserialization fails during execution
     */

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Server server = new Server();
        server.startServer(4242);
    }
}
