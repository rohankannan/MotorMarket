//imports 
package com.cs180proj.app;

import java.io.*;
import java.net.Socket;
/**
 * CS 18000 Group Project
 *
 * Interface for NewClient class which contains all
 * methods and fields.
 *
 * @author (Rohan Kannan, Alistair Joseph,
 * Lydia Schmucker, Stephen Tushentsov) lab sec 19
 *
 * @version May 2, 2025
 */

 public interface NewClientInterface {

    Object sendCommand(String command, Object... args) throws IOException, ClassNotFoundException;
    void close() throws IOException;
    boolean isClosed();
    
 }
