package com.cs180proj.app;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
/**
 * CS 18000 Group Project
 *
 * Interface for ClientManager class which contains all
 * methods and fields.
 *
 * @author (Rohan Kannan, Alistair Joseph,
 * Lydia Schmucker, Stephen Tushentsov) lab sec 19
 *
 * @version May 4, 2025
 */
public interface ClientManagerInterface {
    void sendMessage(String messageToSend);
    void removeClientManager();
    void closeEverything(Socket socket, BufferedReader br, BufferedWriter bw);
  
}
