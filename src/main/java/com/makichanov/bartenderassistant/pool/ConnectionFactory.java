package com.makichanov.bartenderassistant.pool;

import com.makichanov.bartenderassistant.exception.PoolException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {

    private static final Logger LOG = LogManager.getLogger();

    private static final String PATH_TO_DATABASE_PROPERTIES = "/database.properties";
    private static final String DATABASE_URL;
    private static final String USERNAME;
    private static final String PASSWORD;

    static {
        File databasePropertiesFile = new File(PATH_TO_DATABASE_PROPERTIES);
        Properties databaseProperties = new Properties();
        try {
            databaseProperties.load(new FileReader(databasePropertiesFile));
        } catch (IOException e) {
            LOG.fatal("Failed to load database properties", e);
            throw new ExceptionInInitializerError("Failed to load database properties");
        }
        DATABASE_URL = databaseProperties.getProperty("databaseUrl");
        USERNAME = databaseProperties.getProperty("username");
        PASSWORD = databaseProperties.getProperty("password");
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
