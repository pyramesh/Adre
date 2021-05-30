package com.ramesh.resource.ratemanagementservice.exception;

/**
 * @author Ramesh.Yaleru on 5/28/2021
 */
public class ResourceNotFoundException extends RuntimeException{

    private String message;

    ResourceNotFoundException(){
        super();
    }
    public ResourceNotFoundException(String message){
        super(message);
    }
    public ResourceNotFoundException(Throwable cause){
        super(cause);
    }
    public ResourceNotFoundException(String message,Throwable cause){
        super(message,cause);
    }
}
