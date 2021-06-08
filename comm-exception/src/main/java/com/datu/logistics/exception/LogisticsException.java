package com.datu.logistics.exception;

public class LogisticsException extends RuntimeException {
    private final String code;

    protected LogisticsException(String code, String message, Throwable e) {
        super(message == null ? (e == null ? null : e.getMessage()) : message, e);
        this.code = code == null ? "DEFAULT" : code;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String getLocalizedMessage() {
        Throwable cause = getCause();
        return cause == null ? null : cause.toString();
    }

    @Override
    public String toString() {
        return "LogisticsException{" +
                "code='" + code + '\'' +
                "message='" + getMessage() + '\'' +
                "localizedMessage='" + getLocalizedMessage() + '\'' +
                '}';
    }
}
