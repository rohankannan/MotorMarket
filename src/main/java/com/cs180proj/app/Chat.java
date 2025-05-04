package com.cs180proj.app;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class Chat implements Serializable {
    private static final long serialVersionUID = 1L;
    private String sender;
    private String recipient;
    private String message;
    private long timestamp; // Store timestamp as a long

    public Chat(String sender, String recipient, String message) {
        this.sender = sender;
        this.recipient = recipient;
        this.message = message;
        this.timestamp = System.currentTimeMillis(); // Set current time in milliseconds
    }

    public Chat(String sender, String recipient, String message, long timestamp) {
        this.sender = sender;
        this.recipient = recipient;
        this.message = message;
        this.timestamp = timestamp; // Set the provided timestamp
    }

    public String getSender() {
        return sender;
    }

    public String getRecipient() {
        return recipient;
    }

    public String getMessage() {
        return message;
    }

    public long getTimestamp() {
        return timestamp; // Return the timestamp
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString()
    {
        return sender +","+ recipient +","+ message +","+ timestamp;
    }

}