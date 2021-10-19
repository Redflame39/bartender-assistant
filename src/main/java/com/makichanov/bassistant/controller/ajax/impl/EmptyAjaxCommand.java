package com.makichanov.bassistant.controller.ajax.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.makichanov.bassistant.controller.ajax.AjaxCommand;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EmptyAjaxCommand implements AjaxCommand {

    private static final Logger LOG = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request) {
        int status = 404;
        ObjectMapper mapper = new ObjectMapper();
        String json;
        try {
            json = mapper.writeValueAsString(status);
        } catch (JsonProcessingException e) {
            LOG.error("Serialization of 404 status code response failed", e);
            return e.toString();
        }
        return json;
    }
}
