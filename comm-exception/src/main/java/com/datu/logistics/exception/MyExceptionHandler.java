package com.datu.logistics.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class MyExceptionHandler {

    @ResponseStatus
    @ExceptionHandler(Exception.class)
    public ExceptionValue handle(Exception e) {
        log.error("", e);
        return new ExceptionValue(e);
    }
}
