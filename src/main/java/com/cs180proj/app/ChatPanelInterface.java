package com.cs180proj.app;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
/**
 * CS 18000 Group Project
 *
 * Interface for ChatPanel class which contains all
 * methods and fields.
 *
 * @author (Rohan Kannan, Alistair Joseph,
 * Lydia Schmucker, Stephen Tushentsov) lab sec 19
 *
 * @version May 2, 2025
 */
public interface ChatPanelInterface {
    void startChatUpdater(String sender, String recipient);
}
