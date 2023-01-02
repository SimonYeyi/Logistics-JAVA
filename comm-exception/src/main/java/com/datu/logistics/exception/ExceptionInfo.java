package com.datu.logistics.exception;

import lombok.Data;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

@Data
public class ExceptionInfo {
    private String code;
    private String message;
    private String details;
    private String exceptionName;

    private ExceptionInfo() {
        //json反序列化构造
    }

    public ExceptionInfo(Exception e) {
        ServiceException serviceException = e instanceof ServiceException ? (ServiceException) e : new ServiceException(null, null, e);
        this.code = serviceException.getCode();
        this.message = serviceException.getMessage();
        this.details = serviceException.getLocalizedMessage();
        this.exceptionName = e.getClass().getName();
    }

    public Exception restoreException() {
        Exception exception;
        try {
            Class<?> exClass = Class.forName(exceptionName);
            try {
                Constructor<?> constructor = exClass.getDeclaredConstructor(String.class);
                constructor.setAccessible(true);
                exception = (Exception) constructor.newInstance(message);
            } catch (NoSuchMethodException | InvocationTargetException e) {
                exception = ((Exception) exClass.getDeclaredConstructor().newInstance());
            }
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
            exception = new RuntimeException(e);
        }
        return exception;
    }
}
