import java.net.*;
import java.io.*;
import java.util.*;

public class ChatServer{
    private ServerSocket server;
    private int num;
    static List userlist = new ArrayList();
    
    void listen() throws IOException{
        try{
            server = new ServerSocket(18080);
            System.out.println("Chatサーバをポート18080で起動しました．");
	    
            while(true){
                Socket socket = server.accept();
                System.out.println("クライアントが接続してきました．");
                ChatClientHandler handler = new ChatClientHandler(socket, num);
		
		userlist.add(num,handler);
		handler.reset(userlist);
		handler.start();
		this.num++;
	    } 
	    
        } catch(IOException e){
            e.printStackTrace();
        }
    }
    
    
    public static void main(String[] args) throws IOException{
	ChatServer server = new ChatServer();
        server.listen();
    }


}
    
