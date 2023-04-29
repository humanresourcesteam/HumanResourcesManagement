package com.bilgeadam.exception;

import lombok.Getter;

@Getter
public class UserException extends RuntimeException{

    private final EErrorType errorType;

    public UserException(EErrorType errorType){
        super(errorType.getMessage());
        this.errorType = errorType;
    }

    public UserException(EErrorType errorType, String message){
        super(message);
        this.errorType = errorType;
    }
}
