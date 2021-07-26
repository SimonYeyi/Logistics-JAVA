package com.datu.logistics.comm.web;

import com.datu.logistics.comm.security.AuthenticationHandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class WebMvcConfigAdapter implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoggingInterceptor());
        registry.addInterceptor(new AuthenticationHandlerInterceptor())
                .excludePathPatterns("/error")
                .excludePathPatterns("/*/api-docs/**")
                .excludePathPatterns("/swagger-ui/**")
                .excludePathPatterns("/swagger-resources/**");
    }
}
