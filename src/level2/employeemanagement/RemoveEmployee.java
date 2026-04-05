package level2.employeemanagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class RemoveEmployee extends JFrame implements ActionListener {

    Choice choiceEMP;
    JLabel nameLabel, phoneLabel, emailLabel;
    JButton delete, back;

    RemoveEmployee(){

        setLayout(null);
        getContentPane().setBackground(new Color(245,248,255));

        JLabel heading = new JLabel("Remove Employee");
        heading.setBounds(185, 20, 300, 30);
        heading.setFont(new Font("Segoe UI", Font.BOLD, 28));
        heading.setForeground(new Color(25,25,112));
        add(heading);

        Font labelFont = new Font("Segoe UI", Font.BOLD, 15);

        JLabel empid = new JLabel("Employee ID");
        empid.setBounds(50,80,120,30);
        empid.setFont(labelFont);
        add(empid);

        choiceEMP = new Choice();
        choiceEMP.setBounds(200,80,180,30);
        add(choiceEMP);

        try{
            Conn c = new Conn();
            ResultSet rs = c.statement.executeQuery("select * from employee");
            while(rs.next()){
                choiceEMP.add(rs.getString("emp_id"));
            }
        } catch(Exception e){
            e.printStackTrace();
        }

        JLabel name = new JLabel("Name:");
        name.setBounds(50,140,100,30);
        name.setFont(labelFont);
        add(name);

        nameLabel = new JLabel();
        nameLabel.setBounds(200,140,250,30);
        nameLabel.setFont(new Font("Segoe UI", Font.PLAIN,14));
        nameLabel.setBorder(BorderFactory.createLineBorder(new Color(200,200,200)));
        add(nameLabel);

        JLabel phone = new JLabel("Phone:");
        phone.setBounds(50,180,100,30);
        phone.setFont(labelFont);
        add(phone);

        phoneLabel = new JLabel();
        phoneLabel.setBounds(200,180,250,30);
        phoneLabel.setFont(new Font("Segoe UI", Font.PLAIN,14));
        phoneLabel.setBorder(BorderFactory.createLineBorder(new Color(200,200,200)));
        add(phoneLabel);

        JLabel email = new JLabel("Email:");
        email.setBounds(50,220,100,30);
        email.setFont(labelFont);
        add(email);

        emailLabel = new JLabel();
        emailLabel.setBounds(200,220,250,30);
        emailLabel.setFont(new Font("Segoe UI", Font.PLAIN,14));
        emailLabel.setBorder(BorderFactory.createLineBorder(new Color(200,200,200)));
        add(emailLabel);

        // Load details when ID is selected
        choiceEMP.addItemListener(e -> loadEmployeeDetails());

        delete = new JButton("Delete");
        delete.setBounds(150,300,120,40);
        delete.addActionListener(this);
        add(delete);

        back = new JButton("Back");
        back.setBounds(300,300,120,40);
        back.addActionListener(this);
        add(back);

        // Initial load
        loadEmployeeDetails();

        setSize(600,450);
        setLocation(400,150);
        setVisible(true);
    }

    private void loadEmployeeDetails(){
        try{
            Conn c = new Conn();
            ResultSet rs = c.statement.executeQuery(
                    "select * from employee where emp_id='" + choiceEMP.getSelectedItem() + "'"
            );

            if(rs.next()){
                nameLabel.setText(rs.getString("name"));
                phoneLabel.setText(rs.getString("phone"));
                emailLabel.setText(rs.getString("email"));
            }

        } catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == delete){
            try{
                Conn c = new Conn();
                String query = "delete from employee where emp_id='" + choiceEMP.getSelectedItem() + "'";
                c.statement.executeUpdate(query);

                JOptionPane.showMessageDialog(null, "Employee Deleted Successfully");

                setVisible(false);
                new Main_class();

            } catch(Exception ex){
                ex.printStackTrace();
            }
        }

        if(e.getSource() == back){
            setVisible(false);
            new Main_class();
        }
    }

    public static void main(String[] args){
        new RemoveEmployee();
    }
}