package jdbcdemo;

import java.sql.*;
import java.util.Scanner;

public class TransactionDriver {
    public static void main(String[] args) throws SQLException {
        Connection myConn = null;
        Statement myStmt = null;
        try {
            // get a connection
            String url = "jdbc:mysql://localhost:3306/demo?useSSL=false&serverTimezone=UTC";
            String user = "root";
            String password = "admin";

            myConn = DriverManager.getConnection(url, user, password);
            myConn.setAutoCommit(false);
            System.out.println("Salaries BEFORE\n");
            showSalaries(myConn, "HR");
            showSalaries(myConn, "Engineering");

            myStmt = myConn.createStatement();
            myStmt.executeUpdate("update employees set salary= 3000000 where department= 'ENGINEERING'");

            System.out.println("Transaction steps are ready.");

            boolean user_permisson = askUser();
            if (user_permisson){
                myConn.commit();
                System.out.println("TransactionCommitted");
            }else{
                myConn.rollback();
                System.out.println("Transaction has not committed :(");
            }

            System.out.println("Salaries AFTER\n");
            showSalaries(myConn, "HR");
            showSalaries(myConn, "Engineering");

        }
        catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeWithConn(myConn, myStmt, null);
        }
    }

    private static boolean askUser() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Is it okay to save?  yes/no: ");
        String input = scanner.nextLine();

        scanner.close();

        return input.equalsIgnoreCase("yes");
    }

    private static void showSalaries(Connection myConn, String theDepartment) throws SQLException {
        PreparedStatement myStmt = null;
        ResultSet myRs = null;

        try {
            myStmt = myConn.prepareStatement("select * from employees where department=?");
            myStmt.setString(1, theDepartment);

            myRs = myStmt.executeQuery();
            display(myRs);
        }
        catch (Exception e){
            e.printStackTrace();
        }finally {
            close(myStmt, myRs);
        }
    }

    private static void close(Statement myStmt, ResultSet myRs) throws SQLException{
        closeWithConn(null, myStmt, myRs);
    }
    private static void closeWithConn(Connection myConn, Statement myStmt, ResultSet myRs) throws SQLException{
        if (myRs != null) {
            myRs.close();
        }
        if (myConn != null){
            myConn.close();
        }
        if (myStmt != null){
            myStmt.close();
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
