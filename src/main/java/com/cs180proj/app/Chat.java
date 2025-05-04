package com.cs180proj.app;

import java.io.Serializable;

public class Chat implements Serializable {
    private String sender;
    private String recipient;
    private String message;

    public Chat(String sender, String recipient, String message) {
        this.sender = sender;
        this.recipient = recipient;
        this.message = message;
    }

    public String toString() {
        return sender + "," +  recipient + "," + message;
    }


}
