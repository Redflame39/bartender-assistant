package com.makichanov.bassistant.controller.util.prg;

import com.makichanov.bassistant.controller.command.CommandResult;
import com.makichanov.bassistant.controller.command.CommandType;
import com.makichanov.bassistant.controller.command.RequestParameter;
import jakarta.servlet.http.HttpServletRequest;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

import static com.makichanov.bassistant.controller.command.CommandType.*;

public class PostRedirectGet {
    private static PostRedirectGet instance;
    private static AtomicBoolean isInstanceCreated = new AtomicBoolean(false);
    private static ReentrantLock instanceLock = new ReentrantLock(true);
    private final EnumMap<CommandType, CommandType> redirectCommandMapper = new EnumMap<>(CommandType.class);

    private PostRedirectGet() {
        redirectCommandMapper.put(POST_REVIEW, SHOW_COCKTAIL);
        redirectCommandMapper.put(AUTHENTICATE, HOME);
        redirectCommandMapper.put(CREATE_COCKTAIL, COCKTAIL_IMAGE);
        redirectCommandMapper.put(CREATE_ACCOUNT, HOME);
        redirectCommandMapper.put(DELETE_REVIEW, SHOW_COCKTAIL);
        redirectCommandMapper.put(SAVE_UPDATED_COCKTAIL, SHOW_COCKTAIL);
        redirectCommandMapper.put(DELETE_COCKTAIL, COCKTAILS);
    }

    public static PostRedirectGet getInstance() {
        if (!isInstanceCreated.get()) {
            instanceLock.lock();
            try {
                if (instance == null) {
                    instance = new PostRedirectGet();
                    isInstanceCreated.set(true);
                }
            } finally {
                instanceLock.unlock();
            }
        }
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
