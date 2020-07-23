package jdbcdemo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DeleteDriver {
    public static void main(String[] args) {
        try {
            // get a connection
            String url = "jdbc:mysql://localhost:3306/demo?useSSL=false&serverTimezone=UTC";
            String user = "root";
            String password = "admin";

            Connection myConn = DriverManager.getConnection(url, user, password);
            // create a statement
            Statement myStmt = myConn.createStatement();
            // execute sql
            String sql = "delete from employees where id=11";
            myStmt.executeUpdate(sql);
            System.out.println("Deleting is  Complete");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
