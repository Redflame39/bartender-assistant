package com.makichanov.bartenderassistant.pool;

import com.makichanov.bartenderassistant.exception.PoolException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/bartender_assistant";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "aSyNhn@*";

    private ConnectionFactory() {}

    public static Connection getConnection() throws PoolException {
        try {
            return DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            throw new PoolException("Failed to get connection from database", e);
        }
    }
}
