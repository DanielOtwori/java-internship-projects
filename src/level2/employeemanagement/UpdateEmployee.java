package level2.employeemanagement;

import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UpdateEmployee extends JFrame implements ActionListener {

    JTextField tname, tfname, taddress, tphone, temail, tsalary, tdesignation, tID;
    JDateChooser tdob;
    JComboBox Boxeducation;
    JButton update, back;
    String empID;

    UpdateEmployee(String empID){

        this.empID = empID;

        setLayout(null);
        getContentPane().setBackground(new Color(255,255,204));

        JLabel heading = new JLabel("Update Employee Details");
        heading.setBounds(250, 30, 500, 40);
        heading.setFont(new Font("serif", Font.BOLD, 28));
        add(heading);
        //Name Label and Text Field
        JLabel name = new JLabel("Name");
        name.setBounds(50,100,150,30);
        add(name);

        tname = new JTextField();
        tname.setBounds(200,100,180,30);
        add(tname);

        //Next of Kin Label and Text Field
        JLabel fname = new JLabel("Next of Kin");
        fname.setBounds(450,100,150,30);
        add(fname);

        tfname = new JTextField();
        tfname.setBounds(600,100,180,30);
        add(tfname);

        //DOB Label and Date Chooser
        JLabel dob = new JLabel("Date Of Birth");
        dob.setBounds(50,150,150,30);
        add(dob);

        tdob = new JDateChooser();
        tdob.setBounds(200,150,180,30);
        add(tdob);

        //Salary Label and Text Field
        JLabel salary = new JLabel("Salary");
        salary.setBounds(450,150,150,30);
        add(salary);

        tsalary = new JTextField();
        tsalary.setBounds(600,150,180,30);
        add(tsalary);

        //Address Label and Text Field
        JLabel address = new JLabel("Address");
        address.setBounds(50,200,150,30);
        add(address);

        taddress = new JTextField();
        taddress.setBounds(200,200,180,30);
        add(taddress);

        //Phone number Label and Text field
        JLabel phone = new JLabel("Phone Number");
        phone.setBounds(450,200,150,30);
        add(phone);

        tphone = new JTextField();
        tphone.setBounds(600,200,180,30);
        add(tphone);

        //Email addres Label and Text Field
        JLabel email = new JLabel("Email");
        email.setBounds(50,250,150,30);
        add(email);

        temail = new JTextField();
        temail.setBounds(200,250,180,30);
        add(temail);

        //Education Label and Dropdown
        JLabel education = new JLabel("Education");
        education.setBounds(450,250,150,30);
        add(education);

        String items[]={"BBA", "B.Tech", "BCA", "BA", "BSc", "B.COM","MBA","MCA","MA","MTech","MSC","PHD"};
        Boxeducation = new JComboBox(items);
        Boxeducation.setBounds(600,250,180,30);
        add(Boxeducation);

        //ID Number Label and Text field
        JLabel ID = new JLabel("ID Number");
        ID.setBounds(50,300,150,30);
        add(ID);

        tID = new JTextField();
        tID.setBounds(200,300,180,30);
        add(tID);

        //Designation Label and Text Field
        JLabel designation = new JLabel("Designation");
        designation.setBounds(450,300,150,30);
        add(designation);

        tdesignation = new JTextField();
        tdesignation.setBounds(600,300,180,30);
        add(tdesignation);

        //Update Button
        update = new JButton("Update");
        update.setBounds(250,400,120,40);
        update.addActionListener(this);
        add(update);

        //Back Button
        back = new JButton("Back");
        back.setBounds(450,400,120,40);
        back.addActionListener(this);
        add(back);

        // Loading of the existing employee data
        try{
            Conn c = new Conn();
            ResultSet rs = c.statement.executeQuery(
                    "select * from employee where emp_id = '" + empID + "'"
            );

            if(rs.next()){
                tname.setText(rs.getString("name"));
                tfname.setText(rs.getString("fname"));
                taddress.setText(rs.getString("address"));
                tphone.setText(rs.getString("phone"));
                temail.setText(rs.getString("email"));
                tsalary.setText(rs.getString("salary"));
                tdesignation.setText(rs.getString("designation"));
                tID.setText(rs.getString("id"));
                Boxeducation.setSelectedItem(rs.getString("education"));
            }

        } catch(Exception e){
            e.printStackTrace();
        }

        setSize(900,600);
        setLocation(300,100);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == update){
            try{
                Conn c = new Conn();

                Date date = tdob.getDate();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String dob = sdf.format(date);

                String query = "update employee set " +
                        "name='" + tname.getText() + "', " +
                        "fname='" + tfname.getText() + "', " +
                        "dob='" + dob + "', " +
                        "salary='" + tsalary.getText() + "', " +
                        "address='" + taddress.getText() + "', " +
                        "phone='" + tphone.getText() + "', " +
                        "email='" + temail.getText() + "', " +
                        "education='" + Boxeducation.getSelectedItem() + "', " +
                        "id='" + tID.getText() + "', " +
                        "designation='" + tdesignation.getText() + "' " +
                        "where emp_id='" + empID + "'";

                c.statement.executeUpdate(query);

                JOptionPane.showMessageDialog(null, "Employee Updated Successfully");
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
        new UpdateEmployee("1");
    }
}