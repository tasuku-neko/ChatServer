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
  void close(){
        if(in != null){
            try{ in.close(); } catch(IOException e){ }
        }
        if(out != null){
            try{ out.close(); } catch(IOException e){ }
        }
        if(socket != null){
            try{ socket.close(); } catch(IOException e){ }
        }
  }
}

