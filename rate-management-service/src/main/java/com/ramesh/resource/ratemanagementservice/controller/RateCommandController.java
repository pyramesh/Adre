package com.ramesh.resource.ratemanagementservice.controller;

import com.ramesh.resource.ratemanagementservice.dto.*;
import com.ramesh.resource.ratemanagementservice.service.RateService;
import com.ramesh.resource.ratemanagementservice.dto.RateResponse;
import com.ramesh.resource.ratemanagementservice.service.RateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * @author Ramesh.Yaleru on 5/30/2021
 */
@RestController
@Slf4j
public class RateCommandController {

    @Autowired
    private RateService rateService;

    @PreAuthorize("#oauth2.hasScope('admin')")
    @PostMapping("/rates")
    public ResponseEntity<?> save(@RequestHeader HttpHeaders headers, @RequestBody RateRequest request){
        log.info("start RateController :: saveRate");
        RateResponse response = rateService.save(request);
        log.info("end RateController :: saveRate response ={}",response);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PreAuthorize("#oauth2.hasScope('admin')")
    @PutMapping("/rates/{id}")
    private ResponseEntity<RateResponse> updateRate(@PathVariable("id") Long id, @RequestBody UpdateRateRequest request) {
        log.info("start RateController :: updateRate for id ={}", id);
        RateResponse response = rateService.update(request, id);
        log.info("end RateController :: updateRate for id ={}", id);
        return new ResponseEntity<RateResponse>(response, HttpStatus.OK);
    }

    @PreAuthorize("#oauth2.hasScope('admin')")
    @DeleteMapping("/rates/{id}")
    //@CircuitBreaker(name = RATE_SERVICE, fallbackMethod = "rateDeleteServiceFallBack")
    //@Retry(name = RATE_SERVICE, fallbackMethod = "fallBackRetry")
    private ResponseEntity<?> deleteRate(@PathVariable("id") Long id) {
        log.info("start RateController :: deleteRate for id ={}", id);
        rateService.deleteById(id);
        log.info("end RateController :: deleteRate for id ={}", id);
        return new ResponseEntity<String>("Resource deleted Successfully", HttpStatus.ACCEPTED);
    }

    //TODO: fallback response has been hardcoded here for now. For can replace with actual response once align with business.
    public ResponseEntity<RateResponse> rateServiceFallBack(Exception e) {
        log.info("in getRateByIdFallback with exception ", e);
        FallBackResponse response = new FallBackResponse("Service is not responding. Please try again.");
        return new ResponseEntity<RateResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);

    }
    public ResponseEntity<String> rateDeleteServiceFallBack(Exception e) {
        log.info("in getRateByIdFallback with exception ", e);
        return new ResponseEntity<String>("Service is not responding. Please try again.", HttpStatus.INTERNAL_SERVER_ERROR);

    }
    public ResponseEntity<RateResponse> fallBackRetry(Exception e){
        FallBackResponse response = new FallBackResponse("Service is not responding. Hence retrying again.");
        return new ResponseEntity<RateResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);

    }
}
