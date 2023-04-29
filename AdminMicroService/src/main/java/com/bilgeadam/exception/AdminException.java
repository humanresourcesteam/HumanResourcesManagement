package com.bilgeadam.exception;

import lombok.Getter;

@Getter
public class AdminException extends RuntimeException{

    private final EErrorType errorType;

    public AdminException(EErrorType errorType){
        super(errorType.getMessage());
        this.errorType = errorType;
    }

    public AdminException(EErrorType errorType, String message){
        super(message);
        this.errorType = errorType;
    }
}
