package com.datu.logistics.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;

@Slf4j
@RestControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionInfo> handle(Exception e, HttpServletResponse response) {
        if (response.getStatus() != HttpStatus.UNAUTHORIZED.value()) log.error("", e);
        ResponseEntity.BodyBuilder bodyBuilder;
        if (response.getStatus() == HttpStatus.OK.value()) {
            bodyBuilder = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            bodyBuilder = ResponseEntity.status(response.getStatus());
        }
        return bodyBuilder.body(new ExceptionInfo(e));
    }
}
