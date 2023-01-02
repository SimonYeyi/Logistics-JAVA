package com.datu.logistics.exception;

public class ServiceException extends RuntimeException {
    private final String code;

    public ServiceException(String message) {
        this(null, message);
    }

    protected ServiceException(String code, String message) {
        this(code, message, null);
    }

    public ServiceException(String message, Throwable e) {
        this(null, message, e);
    }

    protected ServiceException(String code, String message, Throwable e) {
        super(message == null ? (e == null ? null : e.getMessage()) : message, e);
        this.code = code;
    }

    public final String getCode() {
        return code;
    }

    @Override
    public final String getMessage() {
        return super.getMessage();
    }

    @Override
    public final String getLocalizedMessage() {
        Throwable cause = getCause();
        return cause == null ? getMessage() : cause.toString();
    }

    @Override
    public String toString() {
        return "ServiceException{" +
                "code='" + getCode() + '\'' +
                "message='" + getMessage() + '\'' +
                "localizedMessage='" + getLocalizedMessage() + '\'' +
                '}';
    }
}
