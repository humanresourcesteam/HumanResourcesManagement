package com.bilgeadam.exception;

import lombok.Getter;

@Getter
public class ManagerException extends RuntimeException{

    private final EErrorType errorType;

    public ManagerException(EErrorType errorType){
        super(errorType.getMessage());
        this.errorType = errorType;
    }
    public ManagerException(EErrorType errorType, String message){
        super(message);
        this.errorType = errorType;
    }
}
