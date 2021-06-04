package com.datu.logistics.exception;

public class LogisticsException extends RuntimeException {
    private final String code;

    public LogisticsException(String message) {
        this(message, null);
    }

    public LogisticsException(Throwable e) {
        this(null, e);
    }

    public LogisticsException(String message, Throwable e) {
        this(null, message, e);
    }

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
