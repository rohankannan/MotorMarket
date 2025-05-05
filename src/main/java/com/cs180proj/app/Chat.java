package com.cs180proj.app;

import java.io.Serializable;
/**
 * CS 18000 Group Project
 *
 * Chat class holds the methods and constructors to be used to create
 * chats/messages between users
 *
 * @author (Rohan Kannan, Alistair Joseph,
 * Lydia Schmucker, Stephen Tushentsov) lab sec 19
 *
 * @version May 3, 2025
 */

public class Chat implements Serializable, ChatInterface {
    private static final long serialVersionUID = 1L; //uid
    private String sender; // store sender as a String
    private String recipient; // store recipient as a String
    private String message; // store message as a String
    private long timestamp; // Store timestamp as a long

    /**
    * Constructor for class Chat, initializes each field
    * @param sender sender of a chat 
    * @param recipient recipient of a chat
    * @param message the message being sent
    */
    public Chat(String sender, String recipient, String message) {
        this.sender = sender;
        this.recipient = recipient;
        this.message = message;
        this.timestamp = System.currentTimeMillis(); // Set current time in milliseconds
    }

    /**
    * Another constructor for class Chat, initializes each field
    * @param sender sender of a chat 
    * @param recipient recipient of a chat
    * @param message the message being sent
    * @param timestamp the timestamp of the chat
    */
    public Chat(String sender, String recipient, String message, long timestamp) {
        this.sender = sender;
        this.recipient = recipient;
        this.message = message;
        this.timestamp = timestamp; // Set the provided timestamp
    }

    /**
    * getter method for the sender field
    * @return String value of sender
    */
    public String getSender() {
        return sender;
    }

    /**
    * getter method for the recipient field
    * @return String value of recipient
    */
    public String getRecipient() {
        return recipient;
    }

    /**
    * getter method for the message field
    * @return String value of message
    */
    public String getMessage() {
        return message;
    }

    /**
    * getter method for the timestamp field
    * @return long value of timestamp
    */
    public long getTimestamp() {
        return timestamp; // Return the timestamp
    }

    /**
    * setter method for the timestamp field
    * @param timestamp the new long value to set timestamp as
    */
    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    /**
    * toString method
    * @return a string representation of this Chat
    */
    @Override
    public String toString()
    {
        return sender +"\t"+ recipient +"\t"+ message +"\t"+ timestamp;
    }

}
