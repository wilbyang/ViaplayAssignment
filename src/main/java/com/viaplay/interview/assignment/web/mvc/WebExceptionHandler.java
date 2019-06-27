package com.viaplay.interview.assignment.web.mvc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Author: boyang, created on Jun
 */
@ControllerAdvice
@ResponseBody
public class WebExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(WebExceptionHandler.class);

    //Todo: need to handle more known exception cases here

    @ExceptionHandler
    public ResultBean unknownException(Exception e) {
        log.error("there is an error", e);
        // needs to interact with the monitoring system, send out email, etc.
        return ResultBean.error(-99, "something not quite well...");
    }
}
