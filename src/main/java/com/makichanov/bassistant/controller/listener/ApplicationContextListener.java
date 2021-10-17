package com.makichanov.bassistant.controller.listener;

import com.makichanov.bassistant.exception.PoolException;
import com.makichanov.bassistant.model.pool.CustomConnectionPool;
import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebListener
public class ApplicationContextListener implements ServletContextListener {

    private static final Logger LOG = LogManager.getLogger();

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        /* This method is called when the servlet context is initialized(when the Web application is deployed). */
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        try {
            CustomConnectionPool.getInstance().destroyPool();
        } catch (PoolException e) {
            LOG.fatal("Error occurred when destroying connection pool.", e);
        }
    }
}
