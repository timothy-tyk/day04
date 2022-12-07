import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
  public static void main(String[] args) {
    int PORT = 12345;

    try{
      // initialize client socket
      Socket cs = new Socket("localhost", PORT);
      System.out.println("Start Typing here >>");
      // output stream -> Buffered output stream -> send as Data Output Stream
      OutputStream os = cs.getOutputStream();
      BufferedOutputStream bos = new BufferedOutputStream(os);
      DataOutputStream dos = new DataOutputStream(bos);

      // To send a continuous stream of messages from the console
      Scanner inputSc = new Scanner(System.in);
      String line;

      while((line=inputSc.nextLine())!=null){
        if(line.equalsIgnoreCase("close")){
          dos.writeUTF("close"); // what is writeUTF? why do we write "close"?
          dos.flush();
          System.out.println("Exit from shell");
          break;
        }
        dos.writeUTF(line);
        dos.flush();
        System.out.println("Message sent to server: "+line);
      }
      //Cleanup
      cs.close(); //Closes the socket
      inputSc.close();
    }catch(IOException e){
      System.err.println("IO Error");
    }
    

  }
}
