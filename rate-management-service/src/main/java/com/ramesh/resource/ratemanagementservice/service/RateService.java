package com.ramesh.resource.ratemanagementservice.service;

import com.ramesh.resource.ratemanagementservice.dto.RateRequest;
import com.ramesh.resource.ratemanagementservice.dto.RateResponse;
import com.ramesh.resource.ratemanagementservice.dto.UpdateRateRequest;
import com.ramesh.resource.ratemanagementservice.model.Rate;

/**
 * @author Ramesh.Yaleru on 5/28/2021
 */
public interface RateService {

    RateResponse getById(Long id) throws InterruptedException;
    RateResponse save(RateRequest request);
    RateResponse update(UpdateRateRequest request, Long id);

    void deleteById(Long rateId);

    Rate getByIdFallBack(Throwable throwable);
}
