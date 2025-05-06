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
 * ChatPanel class runs chat UI and contains the methods to be used to create
 * chats/messages between users
 *
 * @author (Rohan Kannan, Alistair Joseph,
 * Lydia Schmucker, Stephen Tushentsov) lab sec 19
 *
 * @version May 3, 2025
 */

public class ChatPanel extends JPanel implements ChatPanelInterface {
    private final NewClient client;
    private final MainFrame mainFrame;
    private final String recipient;
    private final User sender;
    private final JTextArea chatArea;
    private SwingWorker<Void, String> chatUpdater;

    /**
     * Constructs the ChatPanel, which provides a live chat interface between the sender and recipient.
     * Initializes the layout, chat area, message field, and buttons. Begins the chat updating thread.
     *
     * @param mainFrame the main application frame for navigation
     * @param client the client used to send and retrieve chat data
     * @param recipient the username of the person being chatted with
     * @param sender the User object representing the sender of messages
     */

    public ChatPanel(MainFrame mainFrame, NewClient client, String recipient, User sender)
    {
        this.client = client;
        this.mainFrame = mainFrame;
        this.recipient = recipient;
        this.sender = sender;
        setLayout(new BorderLayout());
        JLabel titleLabel = new JLabel("Chat with " + recipient, SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(titleLabel, BorderLayout.NORTH);

        JPanel chatContent = new JPanel();
        chatContent.setLayout(new BoxLayout(chatContent, BoxLayout.Y_AXIS));
        add(chatContent, BorderLayout.CENTER);

        chatArea = new JTextArea(20, 50);
        chatArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(chatArea);
        chatContent.add(scrollPane);

        chatArea.setLineWrap(true);
        JTextField messageField = new JTextField(50);
        messageField.setPreferredSize(new Dimension((int) chatArea.getPreferredScrollableViewportSize().getWidth(),
                25));
        chatContent.add(messageField);

        JPanel buttonPanel = new JPanel(new BorderLayout());
        JButton sendButton = new JButton("Send");
        JButton backButton = new JButton("Back");

        buttonPanel.add(backButton, BorderLayout.WEST);
        buttonPanel.add(sendButton, BorderLayout.EAST);

        add(buttonPanel, BorderLayout.SOUTH);

        sendButton.addActionListener(e -> {
            String message = messageField.getText();
            if (!message.isEmpty()) {
                Chat chat = new Chat(sender.getUsername(), recipient, message);
                try {
                    Object r = client.sendCommand("ADD_CHAT", chat);
                } catch (Exception a) {
                    a.printStackTrace();
                }
                messageField.setText("");

            }

        });

        backButton.addActionListener(e -> mainFrame.showPanel("Hub"));
        startChatUpdater(sender.getUsername(), recipient);

    }

    /**
     * Starts a background thread using SwingWorker to periodically fetch and display new chat messages.
     * Filters messages between the sender and recipient and updates the chat area.
     *
     * @param sender the username of the message sender
     * @param recipient the username of the message recipient
     */

    public void startChatUpdater(String sender, String recipient) {
        chatUpdater = new SwingWorker<>() {
            private long lastUpdated = 0; // Track the last update time

            @Override
            protected Void doInBackground() {
                while (!isCancelled()) {
                    try {
                        Object response = client.sendCommand("GET_CHAT", lastUpdated);
                        ArrayList<Chat> chats = (ArrayList<Chat>) response;

                        for (Chat chat : chats) {
                            if ((chat.getSender().equals(sender) && chat.getRecipient().equals(recipient)) ||
                                    (chat.getSender().equals(recipient) && chat.getRecipient().equals(sender))) {
                                publish(chat.getSender() + ": " + chat.getMessage());
                            }
                        }

                        if (!chats.isEmpty()) {
                            lastUpdated = chats.get(chats.size() - 1).getTimestamp(); // Update lastUpdated timestamp
                        }

                        Thread.sleep(1000); // Update every second
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                return null;
            }

            @Override
            protected void process(List<String> messages) {
                for (String message : messages) {
                    chatArea.append(message + "\n");
                }
            }
        };
        chatUpdater.execute();
    }

    /**
     * Called when the ChatPanel is removed from its container.
     * Ensures that the background chat updater thread is cleanly cancelled to free resources.
     */

    @Override
    public void removeNotify() {
        super.removeNotify();
        if (chatUpdater != null) {
            chatUpdater.cancel(true);
        }
    }

    /**
     * Returns the JTextArea component used to display the chat messages.
     *
     * @return the JTextArea used for chat display
     */

    public JTextArea getChatArea() {
        return chatArea;
    }

    /**
     * Sends a chat message manually (used outside of the send button logic).
     * Constructs a Chat object and sends it to the server using the client.
     *
     * @param message the message to send to the recipient
     */

    public void activateSendButton(String message)
    {
        if (!message.isEmpty()) {
            Chat chat = new Chat(sender.getUsername(), recipient, message);
            try {
                Object r = client.sendCommand("ADD_CHAT", chat);
            } catch (Exception a) {
                a.printStackTrace();
            }
        }
    }

}
