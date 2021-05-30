package com.ramesh.resource.ratemanagementservice.exception;

import lombok.Builder;

import java.io.Serializable;

/**
 * @author Ramesh.Yaleru on 5/28/2021
 */

@Builder
public class ErrorMessage implements Serializable {
    private String errorCode;
    private String messageEn;
    private String messageAr;

    public ErrorMessage(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.messageEn = errorMessage;
    }

    public ErrorMessage(String errorCode, String errorMessageEn, String errorMessageAr) {
        this.errorCode = errorCode;
        this.messageEn = errorMessageEn;
        this.messageAr = errorMessageAr;
    }
}
