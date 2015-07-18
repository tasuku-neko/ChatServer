import java.io.*;
import java.net.*;
import java.util.*;


public class ChatClientHandler extends Thread{
  
  public ChatClientHandler(Socket socket,int num){
        this.socket = socket;
        InetAddress address = socket.getInetAddress();
        System.out.println(address);
	this.num=num;
	name ="undefined" + this.num;
	map.put(this.num,name);
	
  }

  void open() throws IOException{
	  InetAddress address = socket.getInetAddress();
	  System.out.println(address);
	
    InputStream socketIn = socket.getInputStream();
    OutputStream socketOut = socket.getOutputStream();
    in = new BufferedReader(new InputStreamReader(socketIn)); 
    out = new BufferedWriter(new OutputStreamWriter(socketOut));
  }
  String receive() throws IOException{
        String line = in.readLine();
        return line;
  }
}

