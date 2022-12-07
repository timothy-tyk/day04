import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
  public static void main(String[] args) {
    System.out.println("SOCKET SERVER: ");
    int PORT = 12345;

    try{
      ServerSocket serv = new ServerSocket(PORT);

      //get the socket object
      Socket socket = serv.accept();
      // New connection from a client - Accepting it from the client, will be blocked until client data arrives

      InputStream is = socket.getInputStream();
      BufferedInputStream bis = new BufferedInputStream(is);
      DataInputStream dis = new DataInputStream(bis);

      String fromClient = dis.readUTF();
      while(!fromClient.equalsIgnoreCase("exit") && fromClient!=null){
        //process the message
        System.out.println("Message from Client: "+fromClient);
        //keep reading
        fromClient = dis.readUTF(); //why?
      }
      System.out.println("Closing socket...");
      socket.close();
      serv.close();
    }
    catch(IOException e){
      System.err.println("IO Error: "+e.getMessage());
    }
  }
}
