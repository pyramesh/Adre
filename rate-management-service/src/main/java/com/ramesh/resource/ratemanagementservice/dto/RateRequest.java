package com.ramesh.resource.ratemanagementservice.dto;

import com.ramesh.resource.ratemanagementservice.validator.NotNullField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * @author Ramesh.Yaleru on 5/30/2021
 */
@Data@AllArgsConstructor
@NoArgsConstructor
@Builder
@NotNullField.List({
        @NotNullField(fieldName = "rateEffectiveDate", message = "rate EffectiveDate can not be null or empty."),
        @NotNullField(fieldName = "rateEffectiveDate", message = "rate ExpirationDate can not be null or empty."),
        @NotNullField(fieldName = "amount", message = "amount can not be null or empty."),

})
public class RateRequest implements Serializable {
    private String rateDescription;
    private LocalDate rateEffectiveDate;
    private LocalDate rateExpirationDate;
    private Integer amount;
}
