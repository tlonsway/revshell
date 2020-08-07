import javax.swing.*;
import java.awt.*;
import java.io.*;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.image.BufferedImage;
import java.util.*;
public class StreamWindow extends JPanel {
    Image img;
    public StreamWindow() {
        super();
        setLayout(null);
    }
    public void setImage(BufferedImage newimg) {
        img=resizeImg(newimg);
        draw();
    }
    public void draw() {
        repaint();
    }
    public void paintComponent(Graphics g) {
        if (img!=null) {
            g.drawImage(img,0,0,this);
        }
    }
    private Image resizeImg(BufferedImage imgin) {
        //return imgin.getScaledInstance(1920,1080, Image.SCALE_DEFAULT);
        try {
            return imgin.getScaledInstance(960, 540, Image.SCALE_SMOOTH);
        } catch (NullPointerException npe) {
            return null;
        }
    }
}