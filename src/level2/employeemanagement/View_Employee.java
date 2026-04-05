package level2.employeemanagement;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import net.proteanit.sql.DbUtils;

public class View_Employee extends JFrame implements ActionListener {
    JTable table;
    JTableHeader header;
    Choice choiceEMP;
    JButton searchbtn, print, update, back;

    View_Employee(){

        getContentPane().setBackground(new Color(245,248,255));

        JLabel searchLabel = new JLabel("Search by employee ID");
        searchLabel.setBounds(20,20,180,25);
        searchLabel.setFont(new Font("Segoe UI", Font.BOLD,14));
        searchLabel.setForeground(new Color(40,40,40));
        add(searchLabel);

        choiceEMP = new Choice();
        choiceEMP.setBounds(180,20,170,25);
        add(choiceEMP);

        // Load employee IDs
        try{
            Conn c = new Conn();
            ResultSet resultSet = c.statement.executeQuery("select * from employee");
            while(resultSet.next()){
                choiceEMP.add(resultSet.getString("emp_id"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }

        // Table
        table = new JTable();
        table.setRowHeight(24);
        table.setFont(new Font("Segoe UI", Font.PLAIN,13));
        table.setGridColor(new Color(220,220,220));

        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.BOLD,14));
        header.setBackground(new Color(70,130,180));
        header.setForeground(Color.WHITE);

        try{
            Conn c = new Conn();
            ResultSet resultSet = c.statement.executeQuery("select * from employee");
            table.setModel(DbUtils.resultSetToTableModel(resultSet));
        }catch (Exception e){
            e.printStackTrace();
        }

        JScrollPane jp = new JScrollPane(table);
        jp.setBounds(20,110,850,430);
        jp.getViewport().setBackground(Color.WHITE);
        jp.setBorder(BorderFactory.createLineBorder(new Color(200,200,200)));
        add(jp);

        // Search Button
        searchbtn = new JButton("Search");
        searchbtn.setBounds(20,70,100,30);
        add(searchbtn);
        searchbtn.addActionListener(this);

        //Print Button
        print = new JButton("Print");
        print.setBounds(140,70,100,30);
        add(print);
        print.addActionListener(this);

        //Update Button
        update = new JButton("Update");
        update.setBounds(260,70,100,30);
        add(update);
        update.addActionListener(this);

        //Back Button
        back = new JButton("Back");
        back.setBounds(380,70,100,30);
        add(back);
        back.addActionListener(this);

        // Frame settings
        setSize(900,700);
        setLayout(null);
        setLocation(300,100);
        setVisible(true);
    }

    public void actionPerformed(java.awt.event.ActionEvent ae){

        if(ae.getSource() == searchbtn){
            try{
                String query = "select * from employee where emp_id = '" + choiceEMP.getSelectedItem() + "'";
                Conn c = new Conn();
                ResultSet resultSet = c.statement.executeQuery(query);
                table.setModel(DbUtils.resultSetToTableModel(resultSet));
            }catch(Exception e){
                e.printStackTrace();
            }
        }

        if(ae.getSource() == print){
            try{
                table.print();
            }catch(Exception e){
                e.printStackTrace();
            }
        }

        if(ae.getSource() == update){
            setVisible(false);
            new UpdateEmployee(choiceEMP.getSelectedItem());

        }

        if(ae.getSource() == back){
            setVisible(false);
            new Main_class();
        }
    }

    public static void main(String[] args){
        new View_Employee();
    }
}