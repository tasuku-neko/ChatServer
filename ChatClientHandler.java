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
		else if(commands[0].equals("name")){
		    name(commands,map);
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
	    else if(commands[1].equals("name")){
		send("name [ユーザ名]");
	     }
	    
	}
    }
    private void name(String[] commands,Map map) throws IOException{ 
	int Branch = 0;
	if(commands.length == 1){
	    send("name [ユーザ名]");
	}	
	else if(commands.length == 2){
	    for(Iterator i = map.entrySet().iterator(); i.hasNext(); ){ 
		Map.Entry entry = (Map.Entry)i.next();
		String value = entry.getValue().toString();
		if(commands[1].equals(value))
		    Branch=1;
	    }
	    if(Branch==0){		    
		map.remove(num);
		this.name = commands[1];	   
		map.put(this.num,name);
		send("名前を変更しました： " + commands[1]);
	    }
	    else if(Branch==1){send("この名前は既に使用されています");}
	}
	else if(commands.length >=3){
	    send("名前に'スペース'を用いることは出来ません");
	}
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

