package com.ramesh.resource.ratemanagementservice.exception;

/**
 * @author Ramesh.Yaleru on 5/28/2021
 */
public class BusinessException  extends RuntimeException{

    private String message;

    BusinessException(){
    super();
    }
    public BusinessException(String message){
        super(message);
    }
    public BusinessException(Throwable cause){
        super(cause);
    }
    public BusinessException(String message,Throwable cause){
        super(message,cause);
    }
}
