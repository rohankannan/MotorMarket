package com.cs180proj.app;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
/**
 * CS 18000 Group Project
 *
 * NewClient class sends commands to server
 * to properly send actions and open GUI
 * panels.
 *
 * @author (Rohan Kannan, Alistair Joseph,
 * Lydia Schmucker, Stephen Tushentsov) lab sec 19
 *
 * @version May 3, 2025
 */
public class NewClient implements NewClientInterface {
    private final Socket socket;
    private final ObjectOutputStream oos;
    private final ObjectInputStream ois;
    private boolean isClosed = false;

    private final Object streamLock = new Object();

    public NewClient(String host, int port) throws IOException {
        this.socket = new Socket(host, port);
        this.oos = new ObjectOutputStream(socket.getOutputStream());
        this.oos.flush();
        this.ois = new ObjectInputStream(socket.getInputStream());
        isClosed = false;
    }

    /**
     * Thread-safe method for sending commands and reading responses.
     */
    public Object sendCommand(String command, Object... args) throws IOException, ClassNotFoundException {
        synchronized (streamLock) {
            try {
                oos.writeObject(command);
                oos.flush();
                for (Object arg : args) {
                    oos.writeObject(arg);
                    oos.flush();
                }

                Object response = ois.readObject();
                return response;

            } catch (EOFException e) {
                System.err.println("Server closed connection unexpectedly (EOFException on: " + command + ")");
                throw e;
            } catch (IOException | ClassNotFoundException e) {
                System.err.println("sendCommand failed for command: " + command);
                e.printStackTrace();
                throw e;
            }
        }
    }

    /**
     * Closes the socket and marks client inactive.
     */
    public void close() throws IOException {
        synchronized (streamLock) {
            try {
                oos.writeObject("EXIT");
                oos.flush();
            } catch (Exception ignored) {}
            socket.close();
            isClosed = true;
        }
    }

    public boolean isClosed() {
        return isClosed;
    }
}

