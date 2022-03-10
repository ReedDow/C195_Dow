package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String protocol = "jdbc";
    private static final String vendorName = ":mysql:";
    private static final String ipAddress = "//wgudb.ucertify.com:3306/";
    private static final String dbName = "WJ11YP1";

    private static final String jdbcURL = protocol + vendorName + ipAddress + dbName + "?connectionTimeZone=SERVER";

    private static final String MYSQLJBCDriver = "com.mysql.jdbc.Driver";
    private static final String Password = "4838893713219213";


    private static final String username = "U11YP1";
    private static Connection conn = null;

    public static Connection startConnection(){
        try{
            Class.forName(MYSQLJBCDriver);
            conn = DriverManager.getConnection(jdbcURL, username, Password);

            System.out.println("DB Connection Successful");
        } catch(SQLException e){
            e.printStackTrace();
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
