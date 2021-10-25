package com.makichanov.bassistant.controller.upload;

import static com.makichanov.bassistant.controller.command.AccessLevel.*;

public enum UploadCommandType {

    COCKTAIL_IMAGE("img\\cocktails\\", LEVEL_USER),
    USER_IMAGE("img\\users\\", LEVEL_USER),
    DEFAULT("default\\", LEVEL_USER);

    private String uploadSubdirectory;
    private int accessLevel;

    UploadCommandType(String uploadSubdirectory, int accessLevel) {
        this.uploadSubdirectory = uploadSubdirectory;
        this.accessLevel = accessLevel;
    }

    public static UploadCommandType defineUploadType(String commandName) {
        if (commandName == null) {
            return DEFAULT;
        }
        UploadCommandType commandType;
        try {
            commandType = UploadCommandType.valueOf(commandName.toUpperCase());
        } catch (IllegalArgumentException e) {
            commandType = DEFAULT;
        }
        return commandType;
    }

    public int getAccessLevel() {
        return accessLevel;
    }

    public String getUploadSubdirectory() {
        return uploadSubdirectory;
    }

}
