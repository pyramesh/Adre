package com.ramesh.resource.ratemanagementservice.rateservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class RateServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(RateServiceApplication.class, args);
    }

}
