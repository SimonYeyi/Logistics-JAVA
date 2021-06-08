package com.datu.logistics.comm.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Slf4j
public class LoggingInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if ("openfeign".equals(request.getHeader("Client"))) return true;
        if (!(handler instanceof HandlerMethod)) return true;
        StringBuilder sb = new StringBuilder();
        HandlerMethod h = (HandlerMethod) handler;
        Map<String, Object> parameters = getRequestParameters(request);
        sb.append("Request :\n")
                .append("URL     : ").append(request.getRequestURL()).append("  ").append(request.getMethod()).append("\n")
                .append("Queries : ").append(new ObjectMapper().writeValueAsString(parameters)).append("\n")
                .append("BodySize: ").append(request.getInputStream().available()).append("\n")
                .append("Method  : ").append(h.getBean().getClass().getName()).append(".").append(h.getMethod().getName()).append("\n")
                .append("-------------------------------------------------------------------------------------------------------------");
        log.info(sb.toString());
        return true;
    }

    private Map<String, Object> getRequestParameters(HttpServletRequest request) {
        Enumeration<?> em = request.getParameterNames();
        Map<String, Object> parameters = new HashMap<>();
        while (em.hasMoreElements()) {
            String name = (String) em.nextElement();
            String[] parameterValues = request.getParameterValues(name);
            Object value;
            if (parameterValues.length == 1) {
                value = parameterValues[0];
            } else {
                value = parameterValues;
            }
            parameters.put(name, value);
        }
        return parameters;
    }
}
