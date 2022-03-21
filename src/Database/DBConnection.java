package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String protocol = "jdbc";
    private static final String vendorName = ":mysql:";
    private static final String ipAddress = "//wgudb.ucertify.com:3306/";
    private static final String dbName = "client_schedule";

    private static final String jdbcURL = protocol + vendorName + ipAddress + dbName + "?connectionTimeZone=SERVER";

    private static final String MYSQLJBCDriver = "com.mysql.cj.jdbc.Driver";
    private static final String password = "Passw0rd!";


    private static final String username = "sqlUser";
    private static Connection conn = null;

    public static Connection startConnection(){
        try{
            Class.forName(MYSQLJBCDriver);
            conn = DriverManager.getConnection(jdbcURL, username, password);

            System.out.println("DB Connection Successful");
        } catch(SQLException e){
            e.printStackTrace();
            System.out.println("Unable to connect to DB");
            System.out.println("SQL State: " + e.getSQLState());
        } catch(ClassNotFoundException e){
            e.printStackTrace();
        }
        return conn;
    }

    public static Connection getConnection(){
        return conn;
    }

    public static void closeConnection(){
        try{
            conn.close();
            System.out.println("DB Connection Closed");
        } catch (Exception e){

        }
    }
}
