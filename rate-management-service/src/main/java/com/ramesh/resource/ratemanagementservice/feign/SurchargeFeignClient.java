package com.ramesh.resource.ratemanagementservice.feign;

import com.ramesh.resource.ratemanagementservice.config.SurchargeFeignConfiguration;
import com.ramesh.resource.ratemanagementservice.dto.SurchargeResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Ramesh.Yaleru on 5/31/2021
 */

@FeignClient(value = "surcharge-service", url = "${surcharge.api.base.url}",
        configuration = SurchargeFeignConfiguration.class)
public interface SurchargeFeignClient {
    @RequestMapping(method = RequestMethod.GET, value = "/surcharge", produces = "application/json")
    SurchargeResponse fetchVATSurcharge(@PathVariable("ruleId") long ruleId)
            ;
}
