package com.datu.logistics.feign;

import com.datu.logistics.exception.ExceptionInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

import feign.codec.ErrorDecoder;

/**
 * Created by Simon on 2019/12/18.
 */
@Configuration
public class FeignDecoderConfiguration {

    @Bean
    public ErrorDecoder feignErrorDecoder(ObjectMapper objectMapper) {
        return (methodKey, response) -> {
            Exception exception;
            try {
                ExceptionInfo exceptionInfo = objectMapper.readValue(response.body().asInputStream(), ExceptionInfo.class);
                exception = exceptionInfo.restoreException();
            } catch (IOException e) {
                exception = new ErrorDecoder.Default().decode(methodKey, response);
            }
            return exception;
        };
    }
}
