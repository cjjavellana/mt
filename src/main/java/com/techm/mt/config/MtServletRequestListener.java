package com.techm.mt.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by cjavellana on 13/9/15.
 */
public class MtServletRequestListener implements ServletRequestListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(MtServletRequestListener.class);

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {

    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        HttpServletRequest request = (HttpServletRequest) sre.getServletRequest();
        LOGGER.debug("Path: {}; Servername: {}", request.getServletPath(), request.getServerName());
    }
}
