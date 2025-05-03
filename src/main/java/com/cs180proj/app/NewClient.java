package com.cs180proj.app;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class NewClient {
    private Socket socket;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;
    private boolean isClosed = false;

    public NewClient(String host, int port) throws IOException {
        this.socket = new Socket(host, port);
        this.oos = new ObjectOutputStream(socket.getOutputStream());
        this.ois = new ObjectInputStream(socket.getInputStream());
        this.oos.flush();
        isClosed = false;
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
        isClosed = true;
    }

    public boolean isClosed() {
        return isClosed;
    }
}
