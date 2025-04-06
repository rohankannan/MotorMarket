package com.cs180proj.app;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
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
public class Client implements Runnable {

    public static void main(String[] args) throws IOException,
            ClassNotFoundException {
// create socket on agreed upon port (and local host for this example)...
        Socket socket = new Socket("data.cs.purdue.edu", 4242);
// open input stream first, gets header from server...
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
// open output stream second, send header to server...
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        oos.flush(); // ensure data is sent to the server
// read object(s) from server...
        String s1 = (String) ois.readObject();
        System.out.printf("received from server: %s\n", s1);
// write object(s) to server...
        String s2 = s1.toUpperCase();
        oos.writeObject(s2);
        oos.flush(); // ensure data is sent to the server
        System.out.printf("sent to server: %s\n", s2);
// close streams...
        oos.close();
        ois.close();
    }

    @Override
    public void run() {

    }
}
