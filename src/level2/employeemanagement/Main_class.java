package level2.employeemanagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main_class extends JFrame {

    Main_class(){

        setTitle("Employee Management System");
        setSize(1120,630);
        setLocation(250,100);
        setLayout(null);

        // Background Image
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/pexels-filipe.jpg"));
        Image i2 = i1.getImage().getScaledInstance(1120,630, Image.SCALE_SMOOTH);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel background = new JLabel(i3);
        background.setBounds(0,0,1120,630);
        add(background);

        // Overlay Panel (for better readability)
        JPanel panel = new JPanel();
        panel.setBounds(250,120,600,350);
        panel.setBackground(new Color(0,0,0,150)); // semi-transparent black
        panel.setLayout(null);
        background.add(panel);

        // Heading
        JLabel heading = new JLabel("Employee Management System", JLabel.CENTER);
        heading.setBounds(50,30,500,40);
        heading.setFont(new Font("Segoe UI", Font.BOLD,28));
        heading.setForeground(Color.WHITE);
        panel.add(heading);

        // Button style helper
        Font btnFont = new Font("Segoe UI", Font.BOLD,16);
        Color btnColor = new Color(30, 144, 255);

        // Add Employee Button
        JButton add = new JButton("Add Employee");
        add.setBounds(200,100,200,40);
        add.setFont(btnFont);
        add.setBackground(btnColor);
        add.setForeground(Color.WHITE);
        add.setFocusPainted(false);
        add.addActionListener(e -> {
            new AddEmployee();
            setVisible(false);
        });
        panel.add(add);

        // View Employee Button
        JButton view = new JButton("View Employees");
        view.setBounds(200,160,200,40);
        view.setFont(btnFont);
        view.setBackground(btnColor);
        view.setForeground(Color.WHITE);
        view.setFocusPainted(false);
        view.addActionListener(e -> {
            new View_Employee();
            setVisible(false);
        });
        panel.add(view);

        // Remove Employee Button
        JButton rem = new JButton("Remove Employee");
        rem.setBounds(200,220,200,40);
        rem.setFont(btnFont);
        rem.setBackground(btnColor);
        rem.setForeground(Color.WHITE);
        rem.setFocusPainted(false);
        rem.addActionListener(e -> {
            new RemoveEmployee();
            setVisible(false);
        });
        panel.add(rem);

        //small footer label
        JLabel footer = new JLabel("Adamara Employee Management System", JLabel.CENTER);
        footer.setBounds(150,300,300,30);
        footer.setForeground(Color.LIGHT_GRAY);
        panel.add(footer);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args){
        new Main_class();
    }
}