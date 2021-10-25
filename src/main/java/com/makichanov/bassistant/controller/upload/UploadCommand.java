package com.makichanov.bassistant.controller.upload;

import com.makichanov.bassistant.controller.command.CommandResult;
import jakarta.servlet.http.HttpServletRequest;

public interface UploadCommand {

    CommandResult execute(HttpServletRequest request, int fileId, String filename);

}
