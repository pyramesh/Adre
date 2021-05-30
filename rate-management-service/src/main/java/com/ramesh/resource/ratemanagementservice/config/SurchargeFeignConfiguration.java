package com.ramesh.resource.ratemanagementservice.config;

import feign.Logger;
import feign.RequestInterceptor;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

/**
 * @author Ramesh.Yaleru on 5/31/2021
 */
@EnableFeignClients
public class SurchargeFeignConfiguration {

    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
           // requestTemplate.header("Authorization", authCode);
        };
    }
    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.BASIC;
    }
}
