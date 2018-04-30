import java.net.*;
import java.io.*;
import cs1.*;
public class Server implements Runnable {
    ServerSocket ss;
    Socket s;
    BufferedReader din;
    DataOutputStream dout;
    PrintStream ps;
    OutputStream os;
    public static void main(String[] args) throws Exception {
        new Server();
    }
    public Server() {
        try {
            ss = new ServerSocket(32323);
            run();
        } catch (IOException e) {
            e.printStackTrace();
        }       
    }
    public void run() {
        try {
            s = ss.accept();
            ps = new PrintStream(s.getOutputStream());
            os = s.getOutputStream();
            din = new BufferedReader(new InputStreamReader(s.getInputStream()));
            System.out.println("connection from " + s.getInetAddress());
            ps.println("echo %username%");
            String uname = din.readLine();
            while(true) {
                System.out.print(uname + ">");
                String in = Keyboard.readString();
                ps.println(in);
                String line = din.readLine();
                while(!line.equals("{}{}{}")) {
                    System.out.println(line);
                    line=din.readLine();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
 }