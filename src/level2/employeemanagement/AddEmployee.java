package level2.employeemanagement;

import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class AddEmployee extends JFrame implements ActionListener {

    Random ram=new Random();
    int number=ram.nextInt(999999);

    JTextField tname, tfname, taddress, tphone, temail, tsalary, tdesignation ,tID;
    JLabel empid,tempid;
    JDateChooser tdob;
    JComboBox Boxeducation;
    JButton add, back;

    AddEmployee(){

        setLayout(null);

        getContentPane().setBackground(new Color(240,248,255)); // improved background

        JLabel heading = new JLabel("Add Employee Details");
        heading.setBounds(250,30,500,40);
        heading.setFont(new Font("Segoe UI", Font.BOLD,30));
        heading.setForeground(new Color(25,25,112));
        add(heading);

        Font labelFont = new Font("Segoe UI", Font.BOLD,16);
        Color fieldColor = Color.WHITE;

        // Name Label and Text Field
        JLabel name = new JLabel("Name");
        name.setBounds(50,100,150,30);
        name.setFont(labelFont);
        add(name);

        tname = new JTextField();
        tname.setBounds(200,100,180,30);
        tname.setBackground(fieldColor);
        tname.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        add(tname);

        //DOB Label and Date Chooser
        JLabel dob = new JLabel("Date Of Birth");
        dob.setBounds(50,150,150,30);
        dob.setFont(labelFont);
        add(dob);

        tdob = new JDateChooser();
        tdob.setBounds(200,150,180,30);
        tdob.setBackground(fieldColor);
        add(tdob);

        //Salary label and text field
        JLabel salary = new JLabel("Salary");
        salary.setBounds(450,150,150,30);
        salary.setFont(labelFont);
        add(salary);

        tsalary = new JTextField();
        tsalary.setBounds(600,150,180,30);
        tsalary.setBackground(fieldColor);
        tsalary.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        add(tsalary);

        //Address Label and Text Field
        JLabel address = new JLabel("Address");
        address.setBounds(50,200,150,30);
        address.setFont(labelFont);
        add(address);

        taddress = new JTextField();
        taddress.setBounds(200,200,180,30);
        taddress.setBackground(fieldColor);
        taddress.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        add(taddress);

        // Next of Kin Label and Text Field
        JLabel fname = new JLabel("Next of Kin");
        fname.setBounds(450,100,150,30);
        fname.setFont(labelFont);
        add(fname);

        tfname = new JTextField();
        tfname.setBounds(600,100,180,30);
        tfname.setBackground(fieldColor);
        tfname.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        add(tfname);

        //Email Label and Text Field
        JLabel email = new JLabel("Email Address");
        email.setBounds(50,250,150,30);
        email.setFont(labelFont);
        add(email);

        temail = new JTextField();
        temail.setBounds(200,250,180,30);
        temail.setBackground(fieldColor);
        temail.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        add(temail);

        //Phone number label and Text Field
        JLabel phone = new JLabel("Phone Number");
        phone.setBounds(450,200,150,30);
        phone.setFont(labelFont);
        add(phone);

        tphone = new JTextField();
        tphone.setBounds(600,200,180,30);
        tphone.setBackground(fieldColor);
        tphone.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        add(tphone);

        //Education Label and Dropdown
        JLabel education = new JLabel("Highest Education");
        education.setBounds(450,250,150,30);
        education.setFont(labelFont);
        add(education);

        String items[]={"BBA", "B.Tech", "BCA", "BA", "BSc", "B.COM","MBA","MCA","MA","MTech","MSC","PHD"};
        Boxeducation=new JComboBox(items);
        Boxeducation.setBackground(fieldColor);
        Boxeducation.setBounds(600,250,180,30);
        add(Boxeducation);

        //ID Label and Text Field for National ID
        JLabel ID = new JLabel("ID Number");
        ID.setBounds(50,300,150,30);
        ID.setFont(labelFont);
        add(ID);

        tID = new JTextField();
        tID.setBounds(200,300,180,30);
        tID.setBackground(fieldColor);
        tID.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        add(tID);

        //Employment ID Label and ID generator
        empid = new JLabel("Employee ID");
        empid.setBounds(450,300,150,30);
        empid.setFont(labelFont);
        add(empid);

        tempid = new JLabel(""+number);
        tempid.setBounds(600,300,180,30);
        tempid.setFont(new Font("Segoe UI",Font.BOLD,20));
        tempid.setForeground(new Color(220,20,60));
        add(tempid);

        //Designation Label and Text field
        JLabel designation= new JLabel("Designation");
        designation.setBounds(50,350,150,30);
        designation.setFont(labelFont);
        add(designation);

        tdesignation = new JTextField();
        tdesignation.setBounds(200,350,180,30);
        tdesignation.setBackground(fieldColor);
        tdesignation.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        add(tdesignation);

        //Add Button
        add = new JButton("Add");
        add.setBounds(250,420,120,40);
        add.setBackground(Color.BLACK); // unchanged
        add.setForeground(Color.WHITE); // unchanged
        add.setFont(new Font("SAN_SERIF",Font.BOLD,15)); // unchanged
        add.addActionListener(this);
        add(add);

        //Back Button
        back = new JButton("Back");
        back.setBounds(450,420,120,40);
        back.setBackground(Color.BLACK); // unchanged
        back.setForeground(Color.WHITE); // unchanged
        back.setFont(new Font("SAN_SERIF",Font.BOLD,15)); // unchanged
        back.addActionListener(this);
        add(back);

        // FRAME SETTINGS
        setSize(900,650);
        setLocation(300,50);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

    }

    //Action listener
    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == add){
            try{

                Conn c=new Conn();
                String name = tname.getText();
                String fname = tfname.getText();
                String dob = ((JTextField) tdob.getDateEditor().getUiComponent()).getText();
                String salary = tsalary.getText();
                String address = taddress.getText();
                String phone = tphone.getText();
                String email = temail.getText();
                String education = (String) Boxeducation.getSelectedItem();
                String id = tID.getText();
                String designation = tdesignation.getText();
                String empID=tempid.getText();

                String query = "INSERT INTO employee VALUES ('"
                        + empID + "','"
                        + name + "','"
                        + fname + "','"
                        + dob + "','"
                        + salary + "','"
                        + address + "','"
                        + phone + "','"
                        + email + "','"
                        + education + "','"
                        + id + "','"
                        + designation + "')";

                c.statement.executeUpdate(query);

                JOptionPane.showMessageDialog(null, "Employee Added Successfully");

                setVisible(false);
                new Main_class();

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
            }

        } else if(e.getSource() == back){
            setVisible(false);
            new Main_class();
        }
    }

    public static void main(String[] args){
        new AddEmployee();
    }
}