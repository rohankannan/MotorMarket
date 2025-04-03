import java.io.*;
import java.net.*;
public class Server {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
// create socket on agreed-upon port...
        ServerSocket serverSocket = new ServerSocket(4242);
// wait for client to connect, get socket connection...
        Socket socket = serverSocket.accept();
// open output stream to client, flush send header, then input stream...
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        oos.flush(); // ensure data is sent to the client
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
// send object(s) to client...
        String s1 = "hello there";
        oos.writeObject(s1);
        oos.flush(); // ensure data is sent to the client
        System.out.printf("sent to client: %s\n", s1);
// read object(s) from client...
        String s2 = (String) ois.readObject();
        System.out.printf("received from client: %s\n", s2);
// close streams...
        oos.close();
        ois.close();
    }
} 12

