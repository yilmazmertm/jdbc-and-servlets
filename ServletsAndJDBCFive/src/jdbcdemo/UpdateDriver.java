package jdbcdemo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class UpdateDriver {
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
            String sql = "update employees set email='updatedvalue@gmail.com' where id=12";
            myStmt.executeUpdate(sql);
            System.out.println("Update Complete");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
