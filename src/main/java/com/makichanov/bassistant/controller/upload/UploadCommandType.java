package com.makichanov.bassistant.controller.upload;

public enum UploadCommandType {

    COCKTAIL_IMAGE("img\\cocktails\\"),
    USER_IMAGE("img\\users\\"),
    DEFAULT("default/");

    private String uploadSubdirectory;

    UploadCommandType(String uploadSubdirectory) {
        this.uploadSubdirectory = uploadSubdirectory;
    }

    public String getUploadSubdirectory() {
        return uploadSubdirectory;
    }

}
