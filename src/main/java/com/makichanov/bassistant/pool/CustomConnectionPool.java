package com.makichanov.bassistant.pool;

import com.makichanov.bassistant.exception.PoolException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public class CustomConnectionPool {

    private static final Logger LOG = LogManager.getLogger();
    private static CustomConnectionPool instance;
    private static AtomicBoolean isInstanceCreated = new AtomicBoolean(false);
    private static ReentrantLock instanceLock = new ReentrantLock(true);
    private BlockingQueue<ProxyConnection> freeConnections;
    private BlockingQueue<ProxyConnection> givenAwayConnections;

    private static final int DEFAULT_POOL_SIZE = 4;

    private CustomConnectionPool() {
        freeConnections = new LinkedBlockingDeque<>(DEFAULT_POOL_SIZE);
        givenAwayConnections = new LinkedBlockingQueue<>();
        for (int i = 0; i < DEFAULT_POOL_SIZE; i++) {
            try {
                Connection connection = ConnectionFactory.getConnection();
                ProxyConnection proxyConnection = new ProxyConnection(connection);
                freeConnections.add(proxyConnection);
            } catch (PoolException e) {
                LOG.error("Connection was not created", e);
            }
        }
        if (freeConnections.isEmpty()) {
            LOG.fatal("Failed to create connection pool");
            throw new RuntimeException("Connection pool is empty");
        }
    }

    public static CustomConnectionPool getInstance() {
        if (!isInstanceCreated.get()) {
            instanceLock.lock();
            try {
                if (instance == null) {
                    instance = new CustomConnectionPool();
                    isInstanceCreated.set(true);
                }
            } finally {
                instanceLock.unlock();
            }
        }
        return instance;
    }

    public Connection getConnection() {
        ProxyConnection connection = null;
        try {
            connection = freeConnections.take();
            givenAwayConnections.offer(connection);
        } catch (InterruptedException e) {
            // TODO: 16.08.2021 catch
        }
        return connection;
    }

    public void releaseConnection(Connection connection) {
        if (connection.getClass() != ProxyConnection.class) {
            LOG.error("Connection pool method releaseConnection(Connection c) accepts only connection objects" +
                    " returned by itself");
        }
        givenAwayConnections.remove(connection);
        freeConnections.offer((ProxyConnection) connection);
    }

    public void destroyPool() { // FIXME: 11.08.2021 it doesn't work
        for (int i = 0; i < DEFAULT_POOL_SIZE; i++) {
            try {
                freeConnections.take().reallyClose();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        deregisterDrivers();
    }

    private void deregisterDrivers() {
        DriverManager.getDrivers()
                .asIterator()
                .forEachRemaining(driver -> {
                    try {
                        DriverManager.deregisterDriver(driver);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                });
    }
}