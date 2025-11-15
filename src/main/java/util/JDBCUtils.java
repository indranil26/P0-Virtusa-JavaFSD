package util;
import java.sql.*;

public class JDBCUtils {
    static String url = "jdbc:mysql://localhost:3306/social_media";
    static String user = "root";
    static String pass = "12345678";
    static {
        try { Class.forName("com.mysql.cj.jdbc.Driver"); } catch (Exception e) { e.printStackTrace(); }
    }
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, pass);
    }
}
