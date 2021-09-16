package com.makichanov.bassistant.model.pool;

import com.makichanov.bassistant.exception.PoolException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import static com.makichanov.bassistant.util.manager.PropertyManager.*;

public class ConnectionFactory {

    private static final Logger LOG = LogManager.getLogger();

    private static final String DATABASE_PROPERTIES = "/properties/database.properties";
    private static final String DATABASE_URL;
    private static final String USERNAME;
    private static final String PASSWORD;

    static {
        Properties databaseProperties = new Properties();
        InputStream propertiesFile = ConnectionFactory.class.getResourceAsStream(DATABASE_PROPERTIES);
        try {
            databaseProperties.load(propertiesFile);
        } catch (IOException e) {
            LOG.fatal("Failed to load database properties.", e);
            throw new ExceptionInInitializerError("Failed to load database properties.");
        }
        DATABASE_URL = databaseProperties.getProperty("db_url");
        USERNAME = databaseProperties.getProperty("db_username");
        PASSWORD = databaseProperties.getProperty("db_password");
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
