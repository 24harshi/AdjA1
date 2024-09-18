import java.sql.*;
public class StatementProgram{
    public static void main(String[] args) {
        String DB_URL = "jdbc:oracle:thin:@localhost:1521:xe";
        String USER = "SYSTEM";
        String PASSWORD = "bca5c";
        //step:1 Register the driver class
        try{
        Class.forName("oracle.jdbc.driver.OracleDriver");
        //step:2 Open DB Connection
        Connection con = DriverManager.getConnection(DB_URL,USER,PASSWORD);
        if(con != null){
            System.out.println("Connected to oracle db");
        }else{
            System.out.println("Failed to make connection");
        }
         //step:3 Create Statement
         Statement stmt = con.createStatement();// nrml statement

        //step:4 Execute Statement
        String sqlD = "Create table Department(Did int primary key, Dname varchar(20))";
        String sqlE = "Create table Employee(Eid int primary key, Ename varchar(20), Salary float, Address varchar(20), Did int, foreign key (Did) references Department(Did))";
        //stmt.executeUpdate(sqlD);
        //stmt.executeUpdate(sqlE);
        System.out.println("Tables created successfully");
        String InsertSqlD = "Insert into Department values(1, 'AIML')";
        String InsertSqlE = "Insert into Employee values(101,'harshitha',50000.00, 'hyderabad',1)";

        //stmt.executeUpdate(InsertSqlD);
        //stmt.executeUpdate(InsertSqlE);

         String SelectSql = "select * from Employee";
         stmt.execute(SelectSql);
         int r =stmt.executeUpdate(SelectSql);
         stmt.executeQuery(SelectSql);
         System.out.println(r);

         String SelectSql1 = "select * from Department";
         stmt.execute(SelectSql1);
         int r1 =stmt.executeUpdate(SelectSql1);
        stmt.executeQuery(SelectSql1);
         System.out.println(r1);

        //step:5 Close Connection
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        } catch(Exception e){
            e.printStackTrace();
        }
        //step:5 Close Connection
    }
}
