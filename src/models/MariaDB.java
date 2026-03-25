package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MariaDB {
    private static final String URL = "jdbc:mariadb://localhost:3306/rent";
    private static final String USER = "root";
    private static final String PASSWORD = "4k3l4rr3";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
