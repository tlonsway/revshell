import java.net.*;
import java.io.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.nio.*;
import java.awt.Robot;
import java.awt.Rectangle;
import java.awt.Toolkit;
public class ClientScreenSender implements Runnable {
    Socket s;
    PrintStream ps;
    OutputStream os;
    BufferedReader din;
    public ClientScreenSender(String ip, int port) {
        try {
            s = new Socket(ip.substring(1),port);
            ps = new PrintStream(s.getOutputStream());
            os = s.getOutputStream();
            din = new BufferedReader(new InputStreamReader(s.getInputStream()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void run() {
        while(true) {
            try {
                sendImage(grabScreen());
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
            try {
                Thread.sleep(500);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public void sendImage(BufferedImage img) throws IOException {
        ps.println("1");
        try {
            Thread.sleep(10);
        } catch (Exception e) {
        }
        //ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        //ImageIO.write(img, "jpg", byteArrayOutputStream);
        //byte[] size = ByteBuffer.allocate(4).putInt(byteArrayOutputStream.size()).array();
        //os.write(size);
        //os.write(byteArrayOutputStream.toByteArray());
        //os.flush();
        ImageIO.write(img,"jpg",os);
        try {
            Thread.sleep(10);
        } catch (Exception e) {
        }
        ps.println("2");
    }
    public BufferedImage grabScreen() {
        try {
            Robot robot = new Robot();
            Rectangle rectangle = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
            BufferedImage screencap = robot.createScreenCapture(rectangle);
            return screencap;
        } catch (Exception e) {
            return null;
        }
    }
}