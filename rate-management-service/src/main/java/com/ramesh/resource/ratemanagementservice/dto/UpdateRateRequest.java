package com.ramesh.resource.ratemanagementservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * @author Ramesh.Yaleru on 5/28/2021
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRateRequest {
    private String rateDescription;
    private LocalDate rateEffectiveDate;
    private LocalDate rateExpirationDate;
    private Integer amount;
}
