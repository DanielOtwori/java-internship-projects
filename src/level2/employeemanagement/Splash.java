package level2.employeemanagement;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class Splash extends JFrame {

    Splash() {

        setLayout(null);

        URL url = ClassLoader.getSystemResource("icons/adhm.png");

        if (url == null) {
            System.out.println("Image not found!");
        } else {
            ImageIcon i1 = new ImageIcon(url);
            Image i2 = i1.getImage().getScaledInstance(1170, 650, Image.SCALE_SMOOTH);
            ImageIcon i3 = new ImageIcon(i2);

            JLabel image = new JLabel(i3);
            image.setBounds(0, 0, 1170, 650);
            add(image);
        }

        setSize(1170, 650);
        setLocation(100, 50);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        try{
            Thread.sleep(5000);
            setVisible(false);
            new Login();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Splash();
    }
}