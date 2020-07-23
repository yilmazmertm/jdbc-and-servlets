package jdbcdemo;

import java.sql.*;

public class PreparedStatementDriver {
    public static void main(String[] args) {
        try {
            // get a connection
            String url = "jdbc:mysql://localhost:3306/demo?useSSL=false&serverTimezone=UTC";
            String user = "root";
            String password = "admin";


            Connection myConn = DriverManager.getConnection(url, user, password);
            // create a statement
            PreparedStatement myStmt = myConn.prepareStatement("select * from employees where salary >? and department=?");

            myStmt.setDouble(1, 200);
            myStmt.setString(2, "Legal");

            ResultSet myRs = myStmt.executeQuery();

            display(myRs);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void display(ResultSet myRs) throws SQLException {
        try {
            while (myRs.next()){
                System.out.println(myRs.getString("first_name") + " " + myRs.getString("last_name") + " " +
                        myRs.getString("department") + " " + myRs.getInt("salary"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
