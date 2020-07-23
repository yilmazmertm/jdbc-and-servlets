package jdbcdemo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ListingDriver {
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
            ResultSet myRs = myStmt.executeQuery("select * from employees");
            // process the result set
            while (myRs.next()){
                System.out.println(myRs.getString("first_name") + " " + myRs.getString("last_name") + " " +
                        myRs.getString("department") + " " + myRs.getInt("salary"));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
