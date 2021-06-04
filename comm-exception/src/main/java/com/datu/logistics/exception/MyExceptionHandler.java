package com.datu.logistics.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MyExceptionHandler {

    @ResponseStatus
    @ExceptionHandler(Exception.class)
    public ExceptionValue handle(Exception e) {
        if (e instanceof LogisticsException) {
            return new ExceptionValue((LogisticsException) e);
        } else {
            return new ExceptionValue(new LogisticsException(e));
        }
    }
}
