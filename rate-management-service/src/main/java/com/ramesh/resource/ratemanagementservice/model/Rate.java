package com.ramesh.resource.ratemanagementservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

/**
 * @author Ramesh.Yaleru on 5/30/2021
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Rate {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long rateId;

    private String rateDescription;

    private LocalDate rateEffectiveDate;

    private LocalDate rateExpirationDate;

    private Integer amount;


}
