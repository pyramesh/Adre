package com.ramesh.resource.ratemanagementservice.service;

import com.ramesh.resource.ratemanagementservice.dto.RateRequest;
import com.ramesh.resource.ratemanagementservice.dto.RateResponse;
import com.ramesh.resource.ratemanagementservice.dto.UpdateRateRequest;
import com.ramesh.resource.ratemanagementservice.exception.BusinessException;
import com.ramesh.resource.ratemanagementservice.exception.ResourceNotFoundException;
import com.ramesh.resource.ratemanagementservice.mapper.RateManagementMapper;
import com.ramesh.resource.ratemanagementservice.model.Rate;
import com.ramesh.resource.ratemanagementservice.repository.RateRepository;
import com.ramesh.resource.ratemanagementservice.dto.RateResponse;
import com.ramesh.resource.ratemanagementservice.mapper.RateManagementMapper;
import com.ramesh.resource.ratemanagementservice.model.Rate;
import com.ramesh.resource.ratemanagementservice.repository.RateRepository;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Objects;

/**
 * @author Ramesh.Yaleru on 5/30/2021
 */
@Service
@Slf4j
public class RateServiceImpl implements RateService{
    private RateManagementMapper rateManagementMapper = Mappers.getMapper(RateManagementMapper.class);;

    @Autowired
   private RateRepository repository;

    @Override
    @Transactional(timeout = 10)
    public RateResponse getById(Long id) throws InterruptedException {
        log.info("start RateServiceImpl:: getById for id ={}", id);
        RateResponse rateResponse= null;
        try {
            Thread.sleep(10000);
        } catch (Exception e) {
            throw e;
        }
        //SurchargeApiResponse surchargeApiResponse = surchargeFeignClient.getVatSurcharge();
        Rate rate = repository.findById(id).orElse(null);
        rateResponse =rateManagementMapper.map(rate);
        if (Objects.isNull(rate)) {
            throw new ResourceNotFoundException("RateId not found in RMS");
        }
        log.info("end RateServiceImpl:: getById for id ={}", id);
        return rateResponse;
    }

    @Override
    public RateResponse save(RateRequest request){
        Rate rate=repository.save(rateManagementMapper.map(request));
        return rateManagementMapper.map(rate);
    }

    @Override
    public RateResponse update(UpdateRateRequest request, Long id) {
        log.info("start RateServiceImpl:: update for id ={}", id);
        RateResponse rateResponse = null;
        Rate rate = repository.findById(id).orElse(null);
        if (Objects.nonNull(rate)) {
            rateManagementMapper.map(request, rate);
            rateResponse = rateManagementMapper.map(repository.save(rate));
        } else {
             throw new BusinessException("Internal server error. Please contact admin");
        }
        log.info("end RateServiceImpl:: update for id ={}", id);
        return rateResponse;
    }

    @Override
    public void deleteById(Long rateId) {
        log.info("start RateServiceImpl:: deleteById for id ={}", rateId);
        Rate rate = null;
        rate = repository.findById(rateId).orElse(null);
        if (Objects.isNull(rate)) {
            throw new ResourceNotFoundException("RateId not found in RMS");
        }
        try {
            repository.deleteById(rateId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException("something went wrong while deleting resource.");
        }
        log.info("end RateServiceImpl:: deleteById for id ={}", rateId);
    }

    @Override
    public Rate getByIdFallBack(Throwable throwable) {
        log.info("Circuit Breaker Pattern fallback method invoke");
        Rate rate = Rate.builder()
                .amount(123)
                .rateDescription("dummy")
                .rateId(1l)
                .rateEffectiveDate(LocalDate.now())
                .rateExpirationDate(LocalDate.now())
                .build();
        return rate;
    }
}
