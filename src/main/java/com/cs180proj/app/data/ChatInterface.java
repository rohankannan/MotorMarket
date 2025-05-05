package com.cs180proj.app;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
/**
 * CS 18000 Group Project
 *
 * Interface for Chat class which contains all
 * methods and fields.
 *
 * @author (Rohan Kannan, Alistair Joseph,
 * Lydia Schmucker, Stephen Tushentsov) lab sec 19
 *
 * @version May 2, 2025
 */
public interface ChatInterface {
    String getSender();
    String getRecipient();
    String getMessage();
    long getTimestamp();
    void setTimestamp(long timestamp);
}
