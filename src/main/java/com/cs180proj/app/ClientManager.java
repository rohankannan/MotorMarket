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

    public void removeClientManager()
    {
        clientManagers.remove(this);
        sendMessage("other guy left the chat");
    }

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





























