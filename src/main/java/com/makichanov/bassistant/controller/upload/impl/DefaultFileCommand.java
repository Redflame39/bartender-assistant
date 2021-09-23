package com.makichanov.bassistant.controller.upload.impl;

import com.makichanov.bassistant.controller.upload.UploadCommand;
import jakarta.servlet.http.HttpServletRequest;

public class DefaultFileCommand extends UploadCommand {

    @Override
    public String execute(HttpServletRequest request, int id, String filename) {
        return null;
    }
}
