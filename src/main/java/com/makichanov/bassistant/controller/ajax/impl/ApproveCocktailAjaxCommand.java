package com.makichanov.bassistant.controller.ajax.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.makichanov.bassistant.controller.ajax.AjaxCommand;
import com.makichanov.bassistant.controller.command.CommandResult;
import com.makichanov.bassistant.controller.command.JspManager;
import com.makichanov.bassistant.controller.command.PagePath;
import com.makichanov.bassistant.controller.command.RequestParameter;
import com.makichanov.bassistant.exception.ServiceException;
import com.makichanov.bassistant.model.service.CocktailService;
import com.makichanov.bassistant.model.service.impl.CocktailServiceImpl;
import com.makichanov.bassistant.model.util.validator.ParameterValidator;
import com.makichanov.bassistant.model.util.validator.impl.ParameterValidatorImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ApproveCocktailAjaxCommand implements AjaxCommand {

    private static final Logger LOG = LogManager.getLogger();
    private static final String EMPTY_RESULT = "{}";

    @Override
    public String execute(HttpServletRequest request) {
        String idParam = request.getParameter(RequestParameter.ID);
        ParameterValidator validator = ParameterValidatorImpl.getInstance();
        if (validator.validatePositiveInt(idParam)) {
            int id = Integer.parseInt(idParam);
            CocktailService service = CocktailServiceImpl.getInstance();
            boolean updated;
            try {
                updated = service.updateApprovedStatus(id, true);
            } catch (ServiceException e) {
                LOG.error("Failed to update activation status for cocktail " + id, e);
                return EMPTY_RESULT;
            }
            ObjectMapper mapper = new ObjectMapper();
            try {
                return mapper.writeValueAsString(updated);
            } catch (JsonProcessingException e) {
                LOG.error("Failed to serialize updated status " + id, e);
                return EMPTY_RESULT;
            }
        } else {
            return EMPTY_RESULT;
        }
    }
}
