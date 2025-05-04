package com.cs180proj.app;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ChatPanel extends JPanel {
    private NewClient client;
    private MainFrame mainFrame;
    private String recipient;
    private User sender;
    private JTextArea chatArea;
    private SwingWorker<Void, String> chatUpdater;

    public ChatPanel(MainFrame mainFrame, NewClient client, String recipient, User sender)
    {
        this.client = client;
        this.mainFrame = mainFrame;
        this.recipient = recipient;
        this.sender = sender;
        setLayout(new BorderLayout());
        JLabel titleLabel = new JLabel("Chat with " + recipient, SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16)); // Optional: Set font style and size
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
        messageField.setPreferredSize(new Dimension((int) chatArea.getPreferredScrollableViewportSize().getWidth(), 25));
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

    private void startChatUpdater(String sender, String recipient) {
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
                            lastUpdated = chats.get(chats.size() - 1).getTimestamp(); // Update the lastUpdated timestamp
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

    @Override
    public void removeNotify() {
        super.removeNotify();
        if (chatUpdater != null) {
            chatUpdater.cancel(true);
        }
    }

}
