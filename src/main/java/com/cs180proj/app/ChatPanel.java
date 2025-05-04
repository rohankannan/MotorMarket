package com.cs180proj.app;

import javax.swing.*;

public class ChatPanel extends JPanel {
    public ChatPanel(MainFrame mainFrame, NewClient client)
    {
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

        });
        JButton backButton = new JButton("Back");
        add(backButton);
        backButton.addActionListener(e -> mainFrame.showPanel("Listings"));

    }
}
