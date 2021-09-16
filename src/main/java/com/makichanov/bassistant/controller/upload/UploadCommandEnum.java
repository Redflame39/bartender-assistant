package com.makichanov.bassistant.controller.upload;

public enum UploadCommandEnum {

    COCKTAIL_IMAGE("img/cocktails/"),
    USER_IMAGE("img/users/"),
    DEFAULT("default/");

    private  String uploadSubdirectory;

    private UploadCommandEnum(String uploadSubdirectory) {
        this.uploadSubdirectory = uploadSubdirectory;
    }

    public String getUploadSubdirectory() {
        return uploadSubdirectory;
    }

}
