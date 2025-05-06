package com.cs180proj.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

public class ChatPanelTestCase {
    @Test
    public void testSendButton() {
        Database mockDatabase = new Database();
        ArrayList<Chat> chatList = new ArrayList<>();
        try{
            chatList = mockDatabase.readChatData("src/main/java/com/cs180proj/app/data/Chatlog.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }


        Server mockServer = new Server();
        Thread serverThread = new Thread(new Runnable() {
            public void run() {
                try {
                    mockServer.startServer(4242);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        serverThread.start();
        try {
            Thread.sleep(150);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {

            NewClient mockClient = new NewClient("localhost", 4242);
            MainFrame mockMainFrame = new MainFrame();
            User mockSender =  new User("sender", "password", 50,
                    "1 Mitch Daniels Boulevard" );
            User mockRecipient = new User("recipient", "password", 50,
                    "1 Mitch Daniels Boulevard" );
            ChatPanel senderChatPanel = new ChatPanel(mockMainFrame, mockClient, mockRecipient.getUsername(),
                    mockSender);
            ChatPanel recipientChatPanel = new ChatPanel(mockMainFrame, mockClient, mockSender.getUsername(),
                    mockRecipient);
            senderChatPanel.activateSendButton("Hello");
            Thread.sleep(2000);
            assertEquals(mockSender.getUsername() + ": Hello\n", senderChatPanel.getChatArea().getText());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        try {
            mockServer.stopServer();
        } catch (Exception e) {
            e.printStackTrace();
        }
        mockDatabase.overwriteChatData(chatList);
    }

}
