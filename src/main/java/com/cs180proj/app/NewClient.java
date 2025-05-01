package com.cs180proj.app;

import java.io.*;
import java.net.Socket;

public class NewClient {
    private Socket socket;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;

    public NewClient(String host, int port) throws IOException {
        this.socket = new Socket(host, port);
        this.oos = new ObjectOutputStream(socket.getOutputStream());
        this.ois = new ObjectInputStream(socket.getInputStream());
        this.oos.flush();
    }

    public Object sendCommand(String command, Object... args) throws IOException, ClassNotFoundException {
        oos.writeObject(command);
        oos.flush();
        for (Object arg : args) {
            oos.writeObject(arg);
            oos.flush();
        }
        return ois.readObject();
    }

    public void close() throws IOException {
        oos.writeObject("EXIT");
        oos.flush();
        socket.close();
    }
}
