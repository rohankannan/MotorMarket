package com.cs180proj.app;

import javax.swing.*;
import java.io.IOException;

public class ChatPanel extends JPanel {
    private NewClient client;
    private MainFrame mainFrame;
    private String recipient;
    private User sender;

    public ChatPanel(MainFrame mainFrame, NewClient client, String recipient, User sender)
    {
        this.client = client;
        this.mainFrame = mainFrame;
        this.recipient = recipient;
        this.sender = sender;

        JTextArea chatArea = new JTextArea(20, 50);
        chatArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(chatArea);
        add(scrollPane);

        JTextField messageField = new JTextField(50);
        add(messageField);

        JButton sendButton = new JButton("Send");
        add(sendButton);

        sendButton.addActionListener(e -> {
            String message = messageField.getText();
            if (!message.isEmpty()) {
                Chat chat = new Chat(sender.getUsername(), recipient, message);
                try {
                    Object r = client.sendCommand("ADD_CHAT", chat);
                } catch (Exception a) {
                    a.printStackTrace();
                }

            }

        });
        JButton backButton = new JButton("Back");
        add(backButton);
        backButton.addActionListener(e -> mainFrame.showPanel("Listings"));

    }
}
