package com.makichanov.bassistant.controller.ajax.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.makichanov.bassistant.controller.ajax.AjaxCommand;
import com.makichanov.bassistant.controller.command.RequestParameter;
import com.makichanov.bassistant.controller.util.validator.ParameterValidator;
import com.makichanov.bassistant.controller.util.validator.impl.ParameterValidatorImpl;
import com.makichanov.bassistant.exception.ServiceException;
import com.makichanov.bassistant.model.entity.User;
import com.makichanov.bassistant.model.service.UserService;
import com.makichanov.bassistant.model.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class DefineEmailIsFreeAjaxCommand implements AjaxCommand {

    private static final Logger LOG = LogManager.getLogger();
    private static final String EMPTY_RESULT = "{}";

    @Override
    public String execute(HttpServletRequest request) {
        String email = request.getParameter(RequestParameter.EMAIL);
        ParameterValidator validator = ParameterValidatorImpl.getInstance();
        if (validator.validateEmail(email)) {
            UserService service = UserServiceImpl.getInstance();
            Optional<User> requestedUser;
            try {
                requestedUser = service.findByEmail(email);
            } catch (ServiceException e) {
                LOG.error("Failed to find user by email " + email, e);
                return EMPTY_RESULT;
            }
            try {
                ObjectMapper objectMapper = new ObjectMapper();

                Boolean isFree = requestedUser.isEmpty();
                return objectMapper.writeValueAsString(isFree);
            } catch (JsonProcessingException e) {
                return EMPTY_RESULT;
            }
        } else {
            return EMPTY_RESULT;
        }
    }
}
