package co.istad.postgresql.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConfig {
    private static Connection conn;

    // get connection object for next step
    public static Connection getConn() {
        return conn;
    }

    // init
    public static void init() {
        if (conn == null) {
            // Step 1. Load Driver of PostgreSQL
            try {
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

            // Step 2: Define connection URL
            String url = "jdbc:postgresql://localhost:5432/first-db-demo";
            Properties info = new Properties();
            info.put("user", "postgres");
            info.put("password", "Win8464");

            try {
                conn = DriverManager.getConnection(url, info);
            } catch (SQLException e) {
                System.out.println("SQL error: " + e.getMessage());
                throw new RuntimeException(e);
            }
        }

    }
}
