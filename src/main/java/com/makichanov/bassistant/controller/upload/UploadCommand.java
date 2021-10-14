package com.makichanov.bassistant.controller.upload;

import jakarta.servlet.http.HttpServletRequest;

public abstract class UploadCommand {

    public abstract String execute(HttpServletRequest request, int fileId, String filename);

}
