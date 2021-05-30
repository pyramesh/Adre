package com.ramesh.resource.ratemanagementservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * @author Ramesh.Yaleru on 5/30/2021
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RateResponse  implements Serializable {
    private Long rateId;
    private String rateDescription;
    private LocalDate rateEffectiveDate;
    private LocalDate rateExpirationDate;
    private Integer amount;
}