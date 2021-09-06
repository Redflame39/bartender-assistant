package com.makichanov.bassistant.model.pool;

import com.makichanov.bassistant.exception.PoolException;
import com.makichanov.bassistant.util.manager.PropertyManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static com.makichanov.bassistant.util.manager.ApplicationProperty.*;

public class ConnectionFactory {

    private static final Logger LOG = LogManager.getLogger();

    private static final String DATABASE_URL;
    private static final String USERNAME;
    private static final String PASSWORD;

    static {
        PropertyManager manager = new PropertyManager();
        DATABASE_URL = manager.getProperty(DB_URL);
        USERNAME = manager.getProperty(DB_USERNAME);
        PASSWORD = manager.getProperty(DB_PASSWORD);
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
        } catch (SQLException e) {
            LOG.fatal("Unable to register driver for database connection.", e);
            throw new ExceptionInInitializerError("Unable to register driver for database connection.");
        }
    }

    private ConnectionFactory() {}

    public static Connection getConnection() throws PoolException {
        try {
            return DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            throw new PoolException("Failed to get connection from database", e);
        }
    }
}
