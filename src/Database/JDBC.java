package Database;

import java.sql.Connection;
import java.sql.DriverManager;


public abstract class JDBC {

    private static final String databaseName = "client_schedule";
    private static final String protocol = "jdbc";
    private static final String vendor = ":mysql:";
    private static final String location = "//localhost/";
    private static final String DB_URL = protocol + vendor + location + databaseName + "?ConnectionTimeZone = SERVER";
    private static final String driver = "com.mysql.cj.jdbc.Driver";
    private static final String username = "sqlUser";
    private static final String password = "Passw0rd!";
    public static Connection conn;

    public static void makeConnection() {
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(DB_URL, username, password);
            System.out.println("Connection Successful.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void closeConnection() {
        try {
            conn.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}