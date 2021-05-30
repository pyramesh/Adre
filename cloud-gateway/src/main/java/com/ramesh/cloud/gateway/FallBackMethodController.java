package com.ramesh.cloud.gateway;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallBackMethodController {

    @GetMapping("/rateServiceFallBack")
    public String rateServiceFallBackMethod() {
        return "Rate Service is taking longer than Expected." +
                " Please try again later";
    }

}
