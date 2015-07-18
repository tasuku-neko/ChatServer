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
  
 public void run(){	
        try{
            open();
	    while(true) {
		String message = receive();
		String[] commands = message.split(" ");//メッセージ分割
		if(commands[0].equals("help")) {//ヘルプなら
		    help(commands);//ヘルプコマンド
		}	
		else send("please input command. If you do not understand, please run help command.");
	    }
        } catch(IOException e){
            e.printStackTrace();
        } finally{
            close();
        }
    }

    /*ヘルプメソッド*/
    private void help(String[] commands) throws IOException{
	if(commands.length == 1){
	    send("help name whoami bye users post");
	}
	else if(commands.length == 2){
	    if(commands[1].equals("help")){
		send("help [コマンド名]");
	    }
	    
	}
    } 
    
   /** クライアントとのデータのやり取りを行うストリームを開くメソッド．**/
  void open() throws IOException{
	  InetAddress address = socket.getInetAddress();
	  System.out.println(address);
	
    InputStream socketIn = socket.getInputStream();
    OutputStream socketOut = socket.getOutputStream();
    in = new BufferedReader(new InputStreamReader(socketIn)); 
    out = new BufferedWriter(new OutputStreamWriter(socketOut));
  }
  
   /**クライアントからデータを受け取るメソッド．**/
  String receive() throws IOException{
        String line = in.readLine();
        return line;
  }
  
   /**クライアントへデータを送るメソッド．**/
  void send(String message) throws IOException{
	out.write(message);
	out.write("\r\n");
	out.flush();
  }
  
   /**クライアントとの接続を閉じるメソッド．**/
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

