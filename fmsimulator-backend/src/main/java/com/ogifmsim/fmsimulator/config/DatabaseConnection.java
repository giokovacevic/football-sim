package com.ogifmsim.fmsimulator.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/db_fmsim";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";

    private static Connection connection = null;

    private DatabaseConnection() { }

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println(" Connected");
            } catch (SQLException sqlError) {
                System.err.println(" Error in: DatabaseConnection.getConnection(): " + sqlError.getMessage());
                throw sqlError;
            }
        }
        return connection;
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                connection = null;
                System.out.println(" Disconnected");
            } catch (SQLException sqlError) {
                System.err.println(" Error in: DatabaseConnection.closeConnection(): " + sqlError.getMessage());
            }
        }
    }
}
