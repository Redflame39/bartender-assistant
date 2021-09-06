package com.makichanov.bassistant.controller.listener;

import com.makichanov.bassistant.util.manager.ApplicationProperty;
import com.makichanov.bassistant.util.manager.PropertyManager;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

import java.util.concurrent.TimeUnit;

public class SessionListenerImpl implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        HttpSession session = se.getSession();
        PropertyManager manager = new PropertyManager();
        String lifetimeProperty = manager.getProperty(ApplicationProperty.SESSION_LIFETIME_MINUTES);
        int sessionLifetimeMinutes = Integer.parseInt(lifetimeProperty);
        int aliveTimeSeconds = (int) TimeUnit.MINUTES.toSeconds(sessionLifetimeMinutes);
        session.setMaxInactiveInterval(aliveTimeSeconds);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        HttpSessionListener.super.sessionDestroyed(se);
    }
}
