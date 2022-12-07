import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class FileFromClient {
  public static void main(String[] args) {
    int PORT = 12345;
    try{
      // Typical way to setup a socket and send data
      Socket cs = new Socket("localhost", PORT);
      OutputStream os = cs.getOutputStream();
      BufferedOutputStream bos = new BufferedOutputStream(os);
      DataOutputStream dos = new DataOutputStream(bos);

      // Read a file
      FileReader fr = new FileReader("src/input.txt");
      BufferedReader bfr = new BufferedReader(fr);
      String line;

      //while not null
      while((line = bfr.readLine())!=null){
        dos.writeUTF(line);
        dos.flush(); //flush to send out
      }
      //once it has reached the end, send out EOF message to end the reading on the server side
      dos.writeUTF("EOF");
      // sends a UTF to stop the reading
      dos.close();
    }catch(IOException e){
      System.err.println("IO Error "+e.getMessage());
    }
  }
}
