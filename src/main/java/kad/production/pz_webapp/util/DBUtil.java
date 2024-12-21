package kad.production.pz_webapp.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    private static final String JDBC_URL = "jdbc:mariadb://localhost:3306/pz_repository?useSSL=false";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASS = "";
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);
    }
}
