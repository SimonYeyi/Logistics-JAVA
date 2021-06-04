package com.datu.logistics.exception;

import lombok.Data;

@Data
public class ExceptionValue {
    private String code;
    private String message;
    private String details;

    public ExceptionValue(LogisticsException e) {
        this.code = e.getCode();
        this.message = e.getMessage();
        this.details = e.getLocalizedMessage();
    }
}
