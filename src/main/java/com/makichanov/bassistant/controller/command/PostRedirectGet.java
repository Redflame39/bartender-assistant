package com.makichanov.bassistant.controller.command;

import jakarta.servlet.http.HttpServletRequest;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

import static com.makichanov.bassistant.controller.command.CommandType.*;

public class PostRedirectGet {
    private static PostRedirectGet instance = new PostRedirectGet();
    private final EnumMap<CommandType, CommandType> redirectCommandMapper = new EnumMap<>(CommandType.class);

    private PostRedirectGet() {
        redirectCommandMapper.put(POST_REVIEW, SHOW_COCKTAIL);
        redirectCommandMapper.put(AUTHENTICATE, HOME);
        redirectCommandMapper.put(CREATE_COCKTAIL, COCKTAIL_IMAGE);
        redirectCommandMapper.put(CREATE_ACCOUNT, HOME);
        redirectCommandMapper.put(DELETE_REVIEW, SHOW_COCKTAIL);
        redirectCommandMapper.put(SAVE_UPDATED_COCKTAIL, SHOW_COCKTAIL);
        redirectCommandMapper.put(DELETE_COCKTAIL, COCKTAILS);
        redirectCommandMapper.put(LOGOUT, HOME);
        redirectCommandMapper.put(SAVE_UPDATED_PROFILE, PROFILE);
    }

    public static PostRedirectGet getInstance() {
        return instance;
    }

    public String defineRedirectPath(String commandName, HttpServletRequest request, CommandResult commandResult) {
        CommandType commandType = CommandType.getCommandType(commandName);
        CommandType redirectCommand = redirectCommandMapper.get(commandType);
        return request.getContextPath() +
                request.getServletPath() +
                "?" +
                RequestParameter.COMMAND +
                "=" +
                redirectCommand.toString().toLowerCase() +
                appendRequestParameters(commandResult);

    }

    private String appendRequestParameters(CommandResult commandResult) {
        Map<String, String> redirectParameters = commandResult.getParameterMap();
        StringBuilder queryString = new StringBuilder("&");
        redirectParameters.forEach((k, v) -> queryString.append(k)
                .append("=")
                .append(v));
        return "&".equals(queryString.toString()) ? "" : queryString.toString();
    }
}
