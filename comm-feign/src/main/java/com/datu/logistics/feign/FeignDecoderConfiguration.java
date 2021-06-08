package com.datu.logistics.feign;

import com.datu.logistics.exception.ExceptionValue;
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
                ExceptionValue exceptionValue = objectMapper.readValue(response.body().asInputStream(), ExceptionValue.class);
                exception = exceptionValue.restoreException();
            } catch (IOException e) {
                exception = new ErrorDecoder.Default().decode(methodKey, response);
            }
            return exception;
        };
    }
}
