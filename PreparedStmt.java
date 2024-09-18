import java.sql.*;
import java.util.Scanner;

public class PreparedStmt {
    public static void main(String[] args) {
        String DB_URL = "jdbc:oracle:thin:@localhost:1521:xe";
        String USER = "SYSTEM";
        String PASSWORD = "bca5c";

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");

            Connection con = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            if (con != null) {
                System.out.println("Connected to Oracle DB");
            } else {
                System.out.println("Failed to make connection");
                return;
            }

            // Prepare SQL to insert into Department table
            String insertSqlDepartment = "INSERT INTO Department (Did, Dname) VALUES (?, ?)";
            PreparedStatement pstmtDepartment = con.prepareStatement(insertSqlDepartment);

            // Prepare SQL to insert into Employee table
            String insertSqlEmployee = "INSERT INTO Employee (Eid, Ename, SALARY, ADDRESS, Did) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pstmtEmployee = con.prepareStatement(insertSqlEmployee);

            Scanner scan = new Scanner(System.in);

            // Step 1: Enter Department Details
            System.out.println("Enter Department details (type Q to quit entering department details):");
            while (true) {
                System.out.println("Do you want to continue entering department details? (Y/N or type Q to quit)");
                String input = scan.next();
                if (input.equalsIgnoreCase("Q")) {
                    break;
                }

                System.out.println("Enter Department id (numeric):");
                int Did = scan.nextInt();

                System.out.println("Enter Department name:");
                String Dname = scan.next();

                // Insert department data
                pstmtDepartment.setInt(1, Did);
                pstmtDepartment.setString(2, Dname);
                int deptResult = pstmtDepartment.executeUpdate();
                System.out.println("Department record inserted, rows affected: " + deptResult);

                pstmtDepartment.clearParameters(); // Clear parameters for re-use
            }

            // Step 2: Enter Employee Details
            System.out.println("\nNow, enter Employee details (type Q to quit entering employee details):");
            while (true) {
                System.out.println("Do you want to continue entering employee details? (Y/N or type Q to quit)");
                String input = scan.next();
                if (input.equalsIgnoreCase("Q")) {
                    break;
                }

                System.out.println("Enter Employee id (numeric):");
                int Eid = scan.nextInt();

                System.out.println("Enter Employee name:");
                String Ename = scan.next();

                System.out.println("Enter Employee salary (numeric):");
                float Esal = scan.nextFloat();

                System.out.println("Enter Employee address:");
                String Eadd = scan.next();

                System.out.println("Enter Department id (numeric, should already exist):");
                int Did = scan.nextInt();

                // Insert employee data
                pstmtEmployee.setInt(1, Eid);
                pstmtEmployee.setString(2, Ename);
                pstmtEmployee.setFloat(3, Esal);
                pstmtEmployee.setString(4, Eadd);
                pstmtEmployee.setInt(5, Did);

                int empResult = pstmtEmployee.executeUpdate();
                System.out.println("Employee record inserted, rows affected: " + empResult);

                pstmtEmployee.clearParameters(); // Clear parameters for re-use
            }

            // Step 3: Close resources
            pstmtDepartment.close();
            pstmtEmployee.close();
            con.close();
            scan.close();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
