package com.makichanov.bassistant.controller.upload;

import jakarta.servlet.http.HttpServletRequest;

public abstract class UploadCommand {

    public abstract String execute(HttpServletRequest request);

    protected String getFileExtension(String filename) {
        return filename.substring(filename.lastIndexOf("."));
    }
}
