import java.io.*;
import java.net.*;
public class SessionListener implements Runnable {
    ServerSocket ss;
    Socket s;
    Server serv;
    public SessionListener(Server ser) {
        serv=ser;
        try {
            ss = new ServerSocket(43434);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void run() {
        while(true) {
            try {
                s = ss.accept();
                serv.addSocket(s);
            } catch (Exception e) {
                e.printStackTrace();
            }            
        }
    }
}