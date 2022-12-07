import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class FileToServer {
  public static void main(String[] args) {
    int PORT = 12345;
    
    try{
      // Typical way to set up a socket server and input stream
      ServerSocket serv = new ServerSocket(PORT);
      // Server ACCEPTS the socket from the connecting client and creates the Socket object
      Socket socket = serv.accept();
      InputStream is = socket.getInputStream(); //input stream gotten fromt he socket object itself
      BufferedInputStream bis = new BufferedInputStream(is);
      DataInputStream dis = new DataInputStream(bis);

      //To read from the file, we need loop to make it read til the end
      String line = dis.readUTF(); // read the first line
      while(!line.equalsIgnoreCase("EOF") && line!=null){
        try {
          System.out.println("Got: "+line);
          line = dis.readUTF();
        } catch (EOFException e) {
          // TODO: handle exception
          System.out.println("---Reached End Of File---");
        }
      }
      //close the socket
      socket.close();
    }catch(IOException e){
      e.printStackTrace();
    }
  }
}
