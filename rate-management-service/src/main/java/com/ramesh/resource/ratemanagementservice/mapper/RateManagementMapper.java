package com.ramesh.resource.ratemanagementservice.mapper;

import com.ramesh.resource.ratemanagementservice.dto.RateRequest;
import com.ramesh.resource.ratemanagementservice.dto.RateResponse;
import com.ramesh.resource.ratemanagementservice.dto.UpdateRateRequest;
import com.ramesh.resource.ratemanagementservice.model.Rate;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.ReportingPolicy;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

/**
 * @author Ramesh.Yaleru on 5/28/2021
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, nullValuePropertyMappingStrategy = IGNORE, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface RateManagementMapper {
    Rate map(RateRequest request);

    void map(UpdateRateRequest request, @MappingTarget Rate rate);
    RateResponse map(Rate rate);
}
