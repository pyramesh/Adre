package com.ramesh.resource.ratemanagementservice.controller;

import com.ramesh.resource.ratemanagementservice.dto.RateResponse;
import com.ramesh.resource.ratemanagementservice.service.RateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Ramesh.Yaleru on 5/30/2021
 */
@RestController
@Slf4j
public class RateQueryController {

    @Autowired
    private RateService rateService;

    @GetMapping("/rates/{id}")
    @PreAuthorize("#oauth2.hasScope('normal')")
    //@CircuitBreaker(name = RATE_SERVICE, fallbackMethod = "rateServiceFallBack")
    //@Retry(name = RATE_SERVICE, fallbackMethod = "fallBackRetry")
    public ResponseEntity<RateResponse> getRateById(@PathVariable("id") Long id) throws InterruptedException {
        log.info("in createOrder");
        RateResponse response = rateService.getById(id);
        return new ResponseEntity<RateResponse>(response, HttpStatus.OK);
    }
}
