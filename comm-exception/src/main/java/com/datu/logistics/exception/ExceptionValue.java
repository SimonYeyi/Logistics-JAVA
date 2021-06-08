package com.datu.logistics.exception;

import lombok.Data;

import java.lang.reflect.InvocationTargetException;

@Data
public class ExceptionValue {
    private String code;
    private String message;
    private String details;
    private String exceptionName;

    private ExceptionValue() {
        //json反序列化构造
    }

    public ExceptionValue(Exception e) {
        LogisticsException convert = e instanceof LogisticsException ? (LogisticsException) e : new LogisticsException(null, null, e);
        this.code = convert.getCode();
        this.message = convert.getMessage();
        this.details = convert.getLocalizedMessage();
        this.exceptionName = e.getClass().getName();
    }

    public Exception restoreException() {
        Exception exception;
        try {
            Class<?> exClass = Class.forName(exceptionName);
            if (exClass.isAssignableFrom(LogisticsException.class)) {
                exception = (Exception) exClass.getDeclaredConstructor(String.class, String.class, Throwable.class).newInstance(code, message, null);
            } else {
                try {
                    exception = (Exception) exClass.getDeclaredConstructor(String.class).newInstance(message);
                } catch (NoSuchMethodException | InvocationTargetException e) {
                    exception = ((Exception) exClass.getDeclaredConstructor().newInstance());
                }
            }
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
            exception = new RuntimeException(e);
        }
        return exception;
    }
}
