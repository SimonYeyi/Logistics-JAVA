package com.datu.logistics.comm.security;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

public class AuthenticationHandlerInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String client = request.getHeader("Client");
        if ("openfeign".equals(client)) return true;
        if (request.getMethod().equalsIgnoreCase("OPTIONS")) return true;
        if (!(handler instanceof HandlerMethod)) return true;
        Method method = ((HandlerMethod) handler).getMethod();
        if (method.isAnnotationPresent(SkipAuthentication.class)) return true;
        String token = request.getHeader("Authentication");
        JwtUtils.verifyToken(token);
        return true;
    }
}
