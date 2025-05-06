package com.cs180proj.app;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ClientManager implements Runnable, ClientManagerInterface {

    public static ArrayList<ClientManager> clientManagers = new ArrayList<>();
    private Socket socket;
    private BufferedReader br;
    private BufferedWriter bw;
    private String clientUsername;

    /**
     * Constructs a ClientManager to handle communication with a specific client socket.
     * Initializes I/O streams, reads the username, and adds the client to the shared list of managers.
     * Sends a notification message to other clients.
     *
     * @param socket the socket connected to the client
     */

    public ClientManager(Socket socket){

        try {
            this.socket = socket;
            this.bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.clientUsername = br.readLine();
            clientManagers.add(this);
            sendMessage("other guy is in chat");
        } catch (IOException e)
        {
            closeEverything(socket, br, bw);
        }


    }

    /**
     * Listens for incoming messages from the connected client and broadcasts them to other clients.
     * Runs continuously until the connection is closed or an error occurs.
     */

    @Override
    public void run() {
        String messageFromClient;
        while (socket.isConnected())
        {
            try {
               messageFromClient = br.readLine();
               sendMessage(messageFromClient);
            } catch (IOException e) {
                closeEverything(socket, br, bw);
                break;
            }
        }
    }

    /**
     * Sends a message to all connected clients except the sender.
     *
     * @param messageToSend the message to be sent to other clients
     */

    public void sendMessage(String messageToSend)
    {
        for (ClientManager clientManager : clientManagers)
        {
            try {
                if (!clientManager.clientUsername.equals(clientUsername))
                {
                    clientManager.bw.write(messageToSend);
                    clientManager.bw.newLine();
                    clientManager.bw.flush();
                }

            }
            catch (IOException e) {
                closeEverything(socket, br, bw);
            }
        }
    }

    /**
     * Removes this client manager from the global list and notifies others that the user has left.
     */

    public void removeClientManager()
    {
        clientManagers.remove(this);
        sendMessage("other guy left the chat");
    }

    /**
     * Closes the socket and I/O streams associated with this client and removes the client manager.
     *
     * @param socket the client's socket
     * @param br the BufferedReader for input from the client
     * @param bw the BufferedWriter for output to the client
     */

    public void closeEverything(Socket socket, BufferedReader br, BufferedWriter bw)
    {
        removeClientManager();
        try
        {
            if (br != null)
            {
                br.close();
            }
            if (bw != null)
            {
                bw.close();
            }
            if (socket != null)
            {
                socket.close();
            }
        }
        catch (IOException e )
        {
            e.printStackTrace();
        }
    }


}