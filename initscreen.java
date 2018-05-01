import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.imageio.*;
import java.awt.Image;
import java.awt.image.BufferedImage;
public class initscreen {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Windows Authentication Service");
        frame.setVisible(true);
        frame.setSize(467,358);
        window win = new window();
        frame.add(win);
        win.setVisible(true);
    }
}