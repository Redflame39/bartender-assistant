package com.makichanov.bassistant.model.pool;

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

/**
 * The type Custom connection pool.
 */
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

    /**
     * Gets instance.
     *
     * @return the instance
     */
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

    /**
     * Gets connection.
     *
     * @return the connection
     * @throws PoolException the pool exception
     */
    public Connection getConnection() throws PoolException {
        ProxyConnection connection = null;
        try {
            connection = freeConnections.take();
            givenAwayConnections.offer(connection);
        } catch (InterruptedException e) {
            LOG.error("Failed to get connection", e);
            throw new PoolException("Failed to get connection", e);
        }
        return connection;
    }

    /**
     * Release connection.
     *
     * @param connection the connection
     */
    public void releaseConnection(Connection connection) {
        if (connection.getClass() != ProxyConnection.class) {
            LOG.error("Connection pool method releaseConnection(Connection c) accepts only connection objects" +
                    " returned by itself");
            return;
        }
        boolean removed = givenAwayConnections.remove(connection);
        if (removed) {
            freeConnections.offer((ProxyConnection) connection);
        } else {
            LOG.warn("Cannot return connection to pool, it was returned earlier or doesn't belongs to this pool.");
        }
    }

    /**
     * Destroy pool.
     *
     * @throws PoolException the pool exception
     */
    public void destroyPool() throws PoolException {
        for (int i = 0; i < DEFAULT_POOL_SIZE; i++) {
            try {
                freeConnections.take().reallyClose();
            } catch (SQLException e) {
                LOG.error("Exception occurred while destroying connection pool.", e);
            } catch (InterruptedException e) {
                LOG.error("Exception occurred while destroying connection pool.", e);
                throw new PoolException("Failed to get connection", e);
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