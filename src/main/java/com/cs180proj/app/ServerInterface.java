package com.cs180proj.app;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
/**
 * CS 18000 Group Project
 *
 * Interface for Server class which contains all
 * methods and fields.
 *
 * @author (Rohan Kannan, Alistair Joseph,
 * Lydia Schmucker, Stephen Tushentsov) lab sec 19
 *
 * @version April 19, 2025
 */
public interface ServerInterface {
    
    void startServer(int port) throws IOException;
    
    void workWithClient(Socket socket) throws IOException, ClassNotFoundException;

    void checkClientCommand(String command, ObjectInputStream ois, 
        ObjectOutputStream oos) throws IOException, ClassNotFoundException;

    void stopServer() throws IOException;

    boolean isServerRunning();
}
