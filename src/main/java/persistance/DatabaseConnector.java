package persistance;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {
    private static final String DB_URL = "mysql:// localhost:3308/todo?serverTimezone=Europe/Berlin";
    private static final String DB_USER = "root";
    private static final String DB_PW = "PROPl4n3t";

    private static Connection con;


    public static void connect() {
        try {
            con = DriverManager.getConnection("jdbc:"+ DB_URL, DB_USER, DB_PW);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static Connection getConnection(){
        return con;
    }

}
