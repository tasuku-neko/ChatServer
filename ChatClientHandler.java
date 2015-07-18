import java.io.*;
import java.net.*;
import java.util.*;


public class ChatClientHandler extends Thread{
	private Socket socket;
	private BufferedReader in;
	private BufferedWriter out;
	private String name;
	private int num;
	private int usernum;
	static Map map = new TreeMap();
	static List userlist = new ArrayList();
  
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
  void send(String message) throws IOException{
	out.write(message);
	out.write("\r\n");
	out.flush();
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
 void reset(List list){
     userlist.clear();
     userlist.addAll(list);
 }
}

