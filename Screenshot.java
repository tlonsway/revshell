import java.awt.Robot;
import java.awt.image.*;
import java.awt.*;
public class Screenshot {
    public static BufferedImage takeScreenShot() throws Exception {
        BufferedImage img = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
        return img;
    }
}