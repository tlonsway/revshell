import javax.swing.*;
import java.awt.*;
import java.io.*;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.image.BufferedImage;
import java.util.*;
public class window extends JPanel { 
    public window() {
        super();
        setLayout(null);
    }
    public void draw() {
        repaint();
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        //g.setColor(Color.BLUE);
        //g.fillRect(0,0,550,50);
        BufferedImage image;
        try {
            image = ImageIO.read(new File("img1.jpg"));
            g.drawImage(image,0,0,this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    } 
}