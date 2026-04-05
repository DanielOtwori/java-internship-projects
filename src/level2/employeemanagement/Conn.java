package level2.employeemanagement;

import java.sql.*;

public class Conn {

    Connection c;
    Statement statement;

    public Conn() {
        try {
            // Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            c = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/employeemanagement",
                    "root",
                    "@Champion254"
            );

            statement = c.createStatement();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}