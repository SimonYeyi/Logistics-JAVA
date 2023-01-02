package com.datu.logistics.feign;

import org.springframework.boot.autoconfigure.web.servlet.WebMvcRegistrations;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

/**
 * Created by Simon on 2019/12/20.
 */
@Configuration
public class FeignWebMvcRegistrations implements WebMvcRegistrations {

    private final RequestMappingHandlerMapping requestMappingHandlerMapping = new FeignRequestMappingHandlerMapping();

    @Override
    public RequestMappingHandlerMapping getRequestMappingHandlerMapping() {
        return requestMappingHandlerMapping;
    }

    private static class FeignRequestMappingHandlerMapping extends RequestMappingHandlerMapping {
        @Override
        protected boolean isHandler(Class<?> beanType) {
            if (!AnnotatedElementUtils.hasAnnotation(beanType, FeignClient.class)) {
                return super.isHandler(beanType);
            } else {
                return AnnotatedElementUtils.hasAnnotation(beanType, RestController.class);
            }
        }
    }
}