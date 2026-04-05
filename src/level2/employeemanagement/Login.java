package level2.employeemanagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.sql.ResultSet;

public class Login extends JFrame implements ActionListener {

    JTextField tusername;
    JPasswordField tpassword;
    JButton login, back;

    Login(){

        setLayout(null);

        // Load background image
        URL url = ClassLoader.getSystemResource("icons/pexelslogin.jpg");

        if (url != null) {
            ImageIcon i1 = new ImageIcon(url);
            Image i2 = i1.getImage().getScaledInstance(600, 300, Image.SCALE_SMOOTH);
            ImageIcon i3 = new ImageIcon(i2);

            JLabel img = new JLabel(i3);
            img.setBounds(0, 0, 600, 300);
            setContentPane(img);
        } else {
            System.out.println("Image not found!");
        }

        JLabel username = new JLabel("Username");
        username.setBounds(40, 20, 100, 30);
        add(username);

        tusername = new JTextField();
        tusername.setBounds(150, 20, 150, 30);
        add(tusername);

        JLabel password = new JLabel("Password");
        password.setBounds(40, 70, 100, 30);
        add(password);

        tpassword = new JPasswordField();
        tpassword.setBounds(150, 70, 150, 30);
        add(tpassword);

        login = new JButton("LOGIN");
        login.setBounds(150, 140, 150, 30);
        login.addActionListener(this);
        add(login);

        back = new JButton("BACK");
        back.setBounds(150, 180, 150, 30);
        back.addActionListener(this);
        add(back);

        setSize(600, 300);
        setLocation(450, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == login){
            try{
                String username = tusername.getText();
                String password = new String(tpassword.getPassword()); // FIXED


                Conn conn = new Conn();

                // consistent variable name
                String query = "SELECT * FROM login WHERE username = '"
                        + username + "' AND password = '"
                        + password + "'";

                // declare ResultSet variable
                ResultSet rs = conn.statement.executeQuery(query);

                if(rs.next()){
                    JOptionPane.showMessageDialog(null, "Login Successful");
                    setVisible(false);
                    new Main_class();
                } else {
                    JOptionPane.showMessageDialog(null,"Invalid username or password");
                }

            } catch (Exception E){
                E.printStackTrace();
            }

        } else if(e.getSource() == back){
            System.exit(0);
        }
    }

    public static void main(String[] args){
        new Login();
    }
}