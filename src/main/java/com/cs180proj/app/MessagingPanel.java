package com.cs180proj.app;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class MessagingPanel extends JPanel {
    private NewClient client;
    private MainFrame mainFrame;

    public MessagingPanel(MainFrame mainFrame, NewClient client) {
        this.client = client;
        this.mainFrame = mainFrame;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("Messages", SwingConstants.CENTER);



        JPanel MainPanel = new JPanel();
        MainPanel.setLayout(new BoxLayout(MainPanel, BoxLayout.Y_AXIS));
        MainPanel.add(titleLabel);

        ArrayList<String> contacts = new ArrayList<>();

        try
        {
            Object r = client.sendCommand("GET_CHAT", 0L);
            ArrayList<Chat> chats = (ArrayList<Chat>) r;

            for (Chat chat : chats) {
                if (!contacts.contains(chat.getSender()) && !chat.getSender().equals(mainFrame.getCurrentUser().getUsername())) {
                    contacts.add(chat.getSender());
                }
                if (!contacts.contains(chat.getRecipient()) && !chat.getRecipient().equals(mainFrame.getCurrentUser().getUsername())) {
                    contacts.add(chat.getRecipient());
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        JScrollPane scrollPane = new JScrollPane();
        JPanel contactsPanel = new JPanel();
        contactsPanel.setLayout(new BoxLayout(contactsPanel, BoxLayout.Y_AXIS));
        for (String contact : contacts) {
            JButton contactButton = new JButton(contact);
            contactButton.addActionListener(e -> {
                ChatPanel chatPanel = new ChatPanel(mainFrame, client, contact, mainFrame.getCurrentUser());
                mainFrame.mainPanel.add(chatPanel, "Chat");
                mainFrame.showPanel("Chat");
            });
            contactButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            contactButton.setPreferredSize(new Dimension(200, 30));
            contactsPanel.add(contactButton);
        }
        JButton backButton = new JButton("Back to Hub");
        backButton.addActionListener(e -> mainFrame.showPanel("Hub"));
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.setPreferredSize(new Dimension(200, 30));

        scrollPane.setViewportView(contactsPanel);
        scrollPane.setPreferredSize(new Dimension(200, 400));
        scrollPane.setAlignmentX(Component.CENTER_ALIGNMENT);
        MainPanel.add(scrollPane);

        MainPanel.add(backButton);
        add(MainPanel);

    }
}
