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

}

