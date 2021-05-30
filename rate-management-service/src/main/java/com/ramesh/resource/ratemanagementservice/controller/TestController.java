package com.ramesh.resource.ratemanagementservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Ramesh.Yaleru on 5/30/2021
 */
@RestController
public class TestController {

    @GetMapping("/testME")
    public String testME(){
        return "testme !";
    }
}
