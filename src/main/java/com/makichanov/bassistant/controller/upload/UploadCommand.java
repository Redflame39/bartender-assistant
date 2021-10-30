package com.makichanov.bassistant.controller.upload;

import com.makichanov.bassistant.controller.command.CommandResult;
import jakarta.servlet.http.HttpServletRequest;

/**
 * The interface Upload command implements by all upload command classes.
 */
public interface UploadCommand {

    /**
     * Executes upload command.
     *
     * @param request  request object
     * @param fileId   id of loaded file
     * @param filename name of loaded file
     * @return command result, which contains page to load and a routing type
     */
    CommandResult execute(HttpServletRequest request, int fileId, String filename);

}
