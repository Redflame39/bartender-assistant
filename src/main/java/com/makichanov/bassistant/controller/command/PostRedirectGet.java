package com.makichanov.bassistant.controller.command;

import com.makichanov.bassistant.controller.upload.UploadCommandType;
import jakarta.servlet.http.HttpServletRequest;

import java.util.*;

import static com.makichanov.bassistant.controller.command.CommandType.*;

public class PostRedirectGet {
    private static PostRedirectGet instance = new PostRedirectGet();
    private final EnumMap<CommandType, CommandType> redirectCommandMapper = new EnumMap<>(CommandType.class);
    private final EnumMap<UploadCommandType, CommandType> uploadRedirectCommandMapper = new EnumMap<>(UploadCommandType.class);

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
        redirectCommandMapper.put(EDIT_USER_ROLE, PROFILE);

        uploadRedirectCommandMapper.put(UploadCommandType.COCKTAIL_IMAGE, SHOW_COCKTAIL);
        uploadRedirectCommandMapper.put(UploadCommandType.USER_IMAGE, PROFILE);
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

    public String defineUploadRedirectPath(String commandName, HttpServletRequest request, CommandResult commandResult) {
        UploadCommandType commandType = UploadCommandType.defineUploadType(commandName);
        CommandType redirectCommand = uploadRedirectCommandMapper.get(commandType);
        return request.getContextPath() +
                "/controller" +
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
