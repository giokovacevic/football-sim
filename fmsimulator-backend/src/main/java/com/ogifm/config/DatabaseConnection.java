package com.ogifm.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/test";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";

    private static Connection connection = null;

    private DatabaseConnection() {

    }

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println(" Connected");
            } catch (SQLException e) {
                System.out.println(" Error: DatabaseConnection.getConnection()");
            }
        }
        return connection;
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println(" Disconnected");
            } catch (Exception e) {
                System.out.println(" Error: DatabaseConnection.closeConnection()");
            }
        }
    }
}
